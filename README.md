# Applause

Application written in Java 8 and Spring Boot.

Compile and run application. In order to test, send POST requests to: localhost:8080/search
with JSON body including country and device arrays.

Example requests:</br>
```json
POST localhost:8080/search

{
	"country":["US"],
	"device":["ALL"]
}
```

```json
POST localhost:8080/search

{
	"country":["US","JP"],
	"device":["iphone 4","iphone 5"]
}
```

```json
POST localhost:8080/search

{
	"country":["US"]
}
```

author: Krzysztof Pietrowicz
