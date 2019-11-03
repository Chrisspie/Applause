# Applause

Application written in Java 8 and Spring Boot.

Compile and run application. In order to test, send POST requests to: localhost:8080/search
with JSON body including country and device arrays.

Example requests:</br>
POST localhost:8080/search</br>
```json{
	"country":["US"],
	"device":["ALL"]
}
```

</br>
POST localhost:8080/search
```json{
	"country":["US","JP"],
	"device":["iphone 4","iphone 5"]
}
```
</br>
POST localhost:8080/search
```json{
	"country":["US"]
}
```
