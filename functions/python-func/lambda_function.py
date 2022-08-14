import sys
import json
import requests


def lambda_handler(event, context):
    q = event.get("q")
    try:
        endpoint = "https://xxxx.execute-api.ap-northeast-1.amazonaws.com/Prod/"
        payload = {"q": q}
        r = requests.post(endpoint, json.dumps(payload))

        print("OK: status=", r.status_code)

    except Exception as e:
        sys.exit(0)
