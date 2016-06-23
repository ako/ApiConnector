curl -v -X GET http://localhost:8080/api/scheduledevents/?name=event1

curl -v -X GET http://localhost:8080/api/user/andrej/Session

curl -v -X GET http://localhost:8080/api/user/andrej

curl -v -X POST -H "Content-Type: application/json" -d '{"Name":"Keyboard","EAN":"1234567890123","Description":"101 key bluetooth keyboard"}' http://localhost:8080/api/products/

curl -V -X GET http://localhost:8080/api/product/1234567890123