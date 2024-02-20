Save & Get  logs to S3

Write a program in any language of your choice, with following features. 
Write http api server to receive batch of logs from clients via http post request, 
each batch contain multiple logs, with each log having timestamp, log text in json array format

Example:

POST /ingest
[
   {“time”:1685426738,”log”:”lorem1 lipsm”},
   {“time”:1685426739,”log”:”lorem2 lipsm”},
   {“time”:1685426740,”log”:”lorem3 lipsm”}
]


POST /ingest
[
   {“time”:1685426737,”log”:”lorem1 lipsm”},
   {“time”:1685426738,”log”:”lorem2 lipsm”},
   {“time”:1685426749,”log”:”lorem3 lipsm”}
]

- client can call api concurrently
- there is no guarantee of order of logs across api calls 

Sort these logs by timestamp and save them in S3 (access key and secret key will be given), write in such manner so later query can easily made with filters by timestamps (save in s3://mw-code-tester/YOUR_NAME/*)

Write a search api, which will have time-range params, and search text param, so search log in s3 as per given time range and text
Make sure its memory efficient and lesser s3 bandwidth used
	
Example:
	GET /query?start=1685426738&end=1685426739&text=lorem1
	Results:
	[
		{“time”:1685426738,”log”:”lorem1 lipsm”}
]

		Example:
		GET /query?start=1685426738&end=1685426739&text=lorem
	Results:
	[
		{“time”:1685426738,”log”:”lorem1 lipsm”},
  	           {“time”:1685426739,”log”:”lorem2 lipsm”}
]
	
S3 Bucket Name: mw-code-tester
Region: ap-south-1
Key: AKIAVQHKED5NDIVEAKU6
Secret: afbcsihazzSNq6BRMY9s91Iuh5nPxrLyaRxJqvT5










Buffer & Flush to logs files
Write a program in any language of your choice with following features
There is situation where we can’t write small small entries to file one by one but only in blocks or big chunks
For data which we can get from api call made from client, thus whatever data you get from api buffer them in memory (till 100mb) then write to file while
Data will be in json format like {“id”:23,”message”:”lorem lipsum”}
All data must be written in one single file
All jsons must be separated by new line

Write a application, deploy, setup ssl, setup monitoring 

Write a small application in any language
Deploy the application on a plain linux server using kind / minikube 
Expose the application to outside world by exposing k8s service
Make the application accessible via https://
Monitor application performance with prometheus metrics
Visualize the performance metrics using Grafana
