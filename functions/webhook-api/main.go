package main

import (
	"fmt"
        "encoding/json"

	"github.com/aws/aws-lambda-go/events"
	"github.com/aws/aws-lambda-go/lambda"
)

func handler(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	var res map[string]interface{}
	json.Unmarshal([]byte(request.Body), &res)

	return events.APIGatewayProxyResponse{
		Body:       fmt.Sprintf("Hello, %s", res["q"]),
		StatusCode: 200,
	}, nil
}

func main() {
	lambda.Start(handler)
}
