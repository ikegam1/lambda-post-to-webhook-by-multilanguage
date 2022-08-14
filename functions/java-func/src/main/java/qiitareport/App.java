package qiitareport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.PrintStream;
import java.net.HttpURLConnection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Map<String,String>, String>{
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public String handleRequest(Map<String,String> event, Context context)
    {
        LambdaLogger logger = context.getLogger();

        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());

        String statusCode;
        try {
            statusCode = postWebhook(event.get("q"));
            logger.log("\n statusCode: " + statusCode + "\n");
        } catch (IOException e) {
            System.out.println(e);
            return "IOException";
        }

        return statusCode;
    }

    public String postWebhook(String query) throws IOException{

        String postdata =
        "{" +
            " \"q\": \"" +
            query + "\"" +
        "}";

        String  apiURL ="https://xxxx.execute-api.ap-northeast-1.amazonaws.com/Prod/";
        URL url = new URL(apiURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        conn.connect();

        //HttpURLConnectionからOutputStreamを取得し、json文字列を書き込む
        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(postdata);
        ps.close();


        //正常終了時はHttpStatusCode 200が返ってくる
        Integer statusCode = Integer.valueOf(conn.getResponseCode());
        if (statusCode != 200) {
            throw new IOException();
        }

        return statusCode.toString();
    }
}
