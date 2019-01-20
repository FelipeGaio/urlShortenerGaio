Simple service which convert a long URL(https://gist.github.com/FylmTM/e3c4e5f337a176e94d6dd51703068925") into a short URL(http://gaio/fXq3x)

Spring Boot 1.5.6 and H2 database all post operation are saved

# Two rest operation

Post -> localhost:8000/api/shortener body request {"url" : "https://gist.github.com/FylmTM/e3c4e5f337a176e94d6dd51703068925"} 
        response -> { "url": "http://gaio/fXq3x" }
        
Get -> localhost:8000/api/fXq3x redirect to the original url

## Minimal Spring Boot sample app.

# Requirements
For building and running the application you need:

JDK 1.8
Maven 3
Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the de.codecentric.springbootsample.Application class from your IDE.

# Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run
