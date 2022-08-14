const https = require('https')

exports.handler = (event, context, callback) => {
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
            console.log('callback')
            callback(null, `statusCode: ${response.statusCode}`)
        })
        request.write(data)
        request.end()
    } catch (err) {
        callback(err)
    }
};
