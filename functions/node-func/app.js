const https = require('https')

exports.handler = (event, context) => {
    try {
        const data = JSON.stringify({
            q: event["q"],
        })

        const options = {
           method: "POST",
           headers: {
               "Content-Type": "application/json",
           },
        }

        const url = 'https://xxxx.execute-api.ap-northeast-1.amazonaws.com/Prod/'
        const request = https.request(url, options, response => {
            console.log(`statusCode: ${response.statusCode}`)
        })
        request.write(data)
        request.end()

        return {"statusCode": 200}

    } catch (err) {
        console.log(err)
        return err
    }
};
