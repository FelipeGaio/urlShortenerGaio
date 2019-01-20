Assignment: URL Shortener

Simple service which convert a long URL(https://www.google.com/search?ei=B9VEXJeKCuGy5OUPhf-C0As&q=cat&oq=cat&gs_l=psy-ab.3..0i67j0j0i67j0i131j0l6.640058.640466..641059...0.0..0.141.374.0j3......0....1..gws-wiz.......0i71.ASlFf5piTbg) into a short URL(http://gaio/fXq3x)

Spring Boot 1.5.6 and H2 database

Two rest operation

Post -> localhost:8000/api/shortener body request {"url" : "https://gist.github.com/FylmTM/e3c4e5f337a176e94d6dd51703068925"} 
        response -> { "url": "http://gaio/fXq3x" }
        
Get -> localhost:8000/api/fXq3x if find the Original id will redirect
