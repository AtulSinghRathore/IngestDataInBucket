POST http://localhost:8181/logger-application/api/logs/ingest

RequestBody: {
"Dto": [
{
"timestamp": 1645400400,
"log": "Log message 1"
}
]
}

GET  http://localhost:8181/logger-application/api/logs/query?start=1645400399&end=1708445916&text=message

