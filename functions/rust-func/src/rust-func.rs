use lambda_runtime::{handler_fn, Context, Error};
use serde_json::{json, Value};
use std::collections::HashMap;

// エントリポイント
#[tokio::main]
async fn main() -> Result<(), Error> {
    let func = handler_fn(my_handler);
    lambda_runtime::run(func).await?;
    Ok(())
}

async fn my_handler(event: Value, _: Context) -> Result<Value, Error> {
    let q = event["q"].as_str().unwrap_or("undefined");

    let mut map = HashMap::new();
    map.insert("q", q);

    let client = reqwest::Client::new();
    let resp = client.post("https://xxxx.execute-api.ap-northeast-1.amazonaws.com/Prod/")
        .json(&map)
        .send()
        .await?;

    println!("status code: {}", resp.status());

    Ok(json!({ "message": format!("Hello, {}!", q) }))
}
