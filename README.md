# RewardsProgramAssignment

## Build the project using below command
mvn clean install

# Run the application using below command
mvn spring-boot:run

# To invoke the REST API use the below curl command
curl -H "Content-Type: application/json" -X POST -d [{\"customerName\":\"soumya\",\"customerId\":123,\"transactionId\":1,\"transactionAmount\":123.45,\"transactionDate\":\"2021-08-15T10:58\"},{\"customerName\":\"soumya2\",\"customerId\":124,\"transactionId\":2,\"transactionAmount\":150,\"transactionDate\":\"2021-08-18T10:58\"}] http://localhost:8080/customerRewardPoints

# On sucessful invocation you should see the output from the above comand as below
![image](https://user-images.githubusercontent.com/88859831/129832727-176091f7-70ac-49ed-9acc-97bc84473968.png)

