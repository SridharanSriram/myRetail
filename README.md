**CASE STUDY : myRetail RESTful service**

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

Build an application that performs the following actions: 
Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 

Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  

Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  

BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 


**SOLUTION:**

**Technology Stack:**

1. Spring Boot : https://spring.io/projects/spring-boot
2. Feign : https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
3. MongoDB : https://www.mongodb.com/what-is-mongodb
4. Gradle : https://gradle.org/
5. Spock Framework : http://spockframework.org/spock/docs/1.3/all_in_one.html
6. Advance Rest Client : https://github.com/advanced-rest-client

**Environment (Download/ Installation Steps):**
1. Java 1.8 (Download) : https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Intellij IDE Ultimate 2019.3
3. MongoDB : https://treehouse.github.io/installation-guides/windows/mongo-windows.html
4. Grade : https://gradle.org/install/

**Running Steps:**
1. Follow above steps and install.
2. Clone or download ZIP from git project from github (git@github.com:SridharanSriram/myRetail.git) and run gradle clean build command
3. Import project to IDE and run spring boot application. 

Mongo DB -

Run mongo DB from the command prompt - path : /MongoDB/Server/4.2/bin/mongo.exe
Create mongo db database with command - use myRetail

**Response (Result) :**

**Swagger documentation path: http://localhost:8080/swagger-ui.html**
![SwaggerAPI](https://user-images.githubusercontent.com/26497798/71950978-e2ed0780-319e-11ea-886a-2e99c50e8bee.jpg)

**Swagger GET Call:**
![Swagger-GetData](https://user-images.githubusercontent.com/26497798/71950979-e3859e00-319e-11ea-9747-b243d58f32d0.jpg)

**ARC GET Call: http://localhost:8080/products/{id}**
![Get_Postive_Result](https://user-images.githubusercontent.com/26497798/71950975-e2ed0780-319e-11ea-8f09-a00b1efbe0a4.jpg)

**ARC GET Call (Negative Result): http://localhost:8080/products/{id}**
![Get_Negative_Result](https://user-images.githubusercontent.com/26497798/71950974-e2547100-319e-11ea-8b4c-a3e50e6be2cb.jpg)

**ARC PUT Call:**
![Put_Data](https://user-images.githubusercontent.com/26497798/71950976-e2ed0780-319e-11ea-943d-83d621d02516.jpg)

**ARC POST Call:**
![Save_Data](https://user-images.githubusercontent.com/26497798/71950977-e2ed0780-319e-11ea-9a2f-7944a725e1b0.jpg)

**Run Spock Test**
Spock test can be run from intellij by right clicking on the project - test package and select run.
