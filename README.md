Assignment: URL Shortener

Simple service which convert a long URL(https://gist.github.com/FylmTM/e3c4e5f337a176e94d6dd51703068925") into a short URL(http://gaio/fXq3x)

Spring Boot 1.5.6 and H2 database

Two rest operation

Post -> localhost:8000/api/shortener body request {"url" : "https://gist.github.com/FylmTM/e3c4e5f337a176e94d6dd51703068925"} 
        response -> { "url": "http://gaio/fXq3x" }
        
Get -> localhost:8000/api/fXq3x if find the Original id will redirect
