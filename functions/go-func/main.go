package main

import (
	"context"
	"fmt"
	"encoding/json"
	"net/http"
	"bytes"

	"github.com/aws/aws-lambda-go/lambda"
)

func handler(ctx context.Context, b json.RawMessage) {
	var req map[string]interface{}
	json.Unmarshal([]byte(b), &req)
	fmt.Println("%#v", req)

	data, _ := json.Marshal(req)
	endpoint := "https://xxxx.execute-api.ap-northeast-1.amazonaws.com/Prod/"

	res, err := http.Post(endpoint, "application/json", bytes.NewBuffer(data))
        if err != nil {
		fmt.Println("[*] " + err.Error())
        }
    	defer res.Body.Close()

	fmt.Println("[*] " + res.Status)
}

func main() {
	lambda.Start(handler)
}
