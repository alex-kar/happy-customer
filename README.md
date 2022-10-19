## How to build
```
./mvnw clean package
```
## Hot to run
```
java -jar ./target/happy-customer-0.0.1-SNAPSHOT.jar
```
or compile and run using Maven plugin
```
./mvnw spring-boot:run
```

## Place an order and calculate reward points
Place an order
```
curl --request POST 'localhost:8080/v1/order' --header 'Content-Type: application/json' --data-raw '{
    "customerId": "1",
    "itemId": "2",
    "price": 100
}'
```
Calculate reward points for customer
```
curl --request GET 'localhost:8080/v1/reward/customer/1'
```
Calculate reward points total 
```
curl --request GET 'localhost:8080/v1/reward/total'
```
