# Info

Example of parsed input stream data:

```json
{
  "in": {
    "isBase64Encoded": true,
    "headers": {
      "content-length": "7",
      "x-amzn-tls-cipher-suite": "ECDHE-RSA-AES128-GCM-SHA256",
      "x-amzn-tls-version": "TLSv1.2",
      "x-amzn-trace-id": "Root=1-63e6081c-021a66142e3568f504cab971",
      "x-forwarded-proto": "https",
      "host": "d253wt6eviuv4q3crss53fij2y0txrbb.lambda-url.ap-southeast-1.on.aws",
      "x-forwarded-port": "443",
      "content-type": "application/x-www-form-urlencoded",
      "x-forwarded-for": "140.213.136.187",
      "user-agent": "curl/7.79.1",
      "accept": "*/*"
    },
    "rawPath": "/x/1",
    "requestContext": {
      "accountId": "anonymous",
      "timeEpoch": 1676019740909,
      "routeKey": "$default",
      "stage": "$default",
      "domainPrefix": "d253wt6eviuv4q3crss53fij2y0txrbb",
      "requestId": "a622416f-5a14-48fc-a801-1f0ad690bd92",
      "domainName": "d253wt6eviuv4q3crss53fij2y0txrbb.lambda-url.ap-southeast-1.on.aws",
      "http": {
        "path": "/x/1",
        "protocol": "HTTP/1.1",
        "method": "POST",
        "sourceIp": "140.213.136.187",
        "userAgent": "curl/7.79.1"
      },
      "time": "10/Feb/2023:09:02:20 +0000",
      "apiId": "d253wt6eviuv4q3crss53fij2y0txrbb"
    },
    "routeKey": "$default",
    "body": "eyJ4IjoxfQ==",
    "rawQueryString": "",
    "version": "2.0"
  },
  "message": "OK",
  "status": 200
}
```
