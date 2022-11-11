# cus-shopping-car-service

## Postman Collections
> https://github.com/ShoppingCarProject/cus-shopping-car-service/blob/develop/src/main/resources/cus-collection.postman_collection.json
## how to start de project: 
>Download repository

`git clone https://github.com/ShoppingCarProject/cus-shopping-car-service.git `

> Open with your IDE ( I Worked the project with Spring tool suite)

`Instal XAMP or client for MySql en start a server OR use docker container and expose de port`

> Search the apllication.properties file in the spring boot project o modify yout configuration of MySql
> we only need de conection, becouse the framework is going to generate the database on his conection 
> and the database name would be "shoppingcar"
```
server.port=7001
spring.datasource.url=jdbc:mysql://{yourHost}:{YourPort}/shoppingcar?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
spring.datasource.username={Your USERNAME}
spring.datasource.password={Your PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

> Run your project and the project going to work, but wew need to have the service cus-security-service started!

## Dependencys: 
 - MySql
 - cus-security-service
> 	Desarrollo de API REST usando spring framework (Spring boot, security, jpa, rest, junits, etc), puede usar los >componentes que considere conveniente.
> Creación de las APIs.
>•	Endpoints Seguridad ✅
>•	Endpoints Cliente ✅
>•	Endpoints Productos ✅
>Esta api servirá como proxy hacia la api: https://fakestoreapi.com la cual servirá para obtener la información de >productos.
>
>•	Endpoints Ordenes ✅
>•	Endpoints Detalles ✅
>•	Endpoints Pago ✅
>•	Para este punto es simular el pago. ✅

## Arquitecture

![arquitectura de solucion](https://user-images.githubusercontent.com/58859695/201437514-2fa07df4-54cd-46b9-9a22-8a920f1c5def.png)


## Endpoints examples: 
## PRODUCTS
Add products to the shoppingcar
```
curl --location --request POST 'localhost:7001/productscar/6' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```

Get all products on my shopping car.
```
curl --location --request GET 'localhost:7001/productscar' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```
Count products on my shopping car
```
curl --location --request GET 'localhost:7001/productscar/count' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```
Delete product on my shopping car
```
curl --location --request DELETE 'localhost:7001/productscar/remove/7' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```
## CLIENTS
Get data of owner autentication

```
curl --location --request GET 'localhost:7001/client' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```

Getl Al users on database
```
curl --location --request GET 'localhost:7001/client/all' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```

## ORDERS

Whit this curl we are going to generate a new order with the products on my shopping car
```
curl --location --request POST 'localhost:7001/orders/generate' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```
Whit this endpont we are goint to see my orders
```
curl --location --request GET 'localhost:7001/orders/myorders' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```

with this andpoint we are goint to see orders by status cose example {1 = COMPLETE, 2 = IN PROCESS , 3 = CANCELED}
```
curl --location --request GET 'localhost:7001/orders/myorders/2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjIiLCJleHAiOjE2NjgyMTE5MDksImlhdCI6MTY2ODE5MzkwOX0.HW39Ivrn-gAzeK9tE3Fsw3ljqNq6duh-Y-48HBr0CT0y9dqWk5ZEzobio6l6PwCls26xBOn6h9f5WbaUowaZsA'
```
with this endpoint we are going to see a detail on shopping detail of my order {id}

```
curl --location --request GET 'localhost:7001/orders/detail/2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```
with this endpoint we are going remove a item on my shopping detail 
```
curl --location --request DELETE 'localhost:7001/shopping/remove/5' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjIiLCJleHAiOjE2NjgyMTE5MDksImlhdCI6MTY2ODE5MzkwOX0.HW39Ivrn-gAzeK9tE3Fsw3ljqNq6duh-Y-48HBr0CT0y9dqWk5ZEzobio6l6PwCls26xBOn6h9f5WbaUowaZsA'
```

## PAYMENTS
We are going to do a payment with this endpoint 

```
curl --location --request POST 'localhost:7001/payments/generate' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amountPaid": 336.0,
    "type": "full",
    "idOrder": 6
}'
```
we will get a amount due for doing a payment.

```
curl --location --request GET 'localhost:7001/payments/amountdue/6' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```

we are going to see my invoice payments in this endpoint
```
curl --location --request GET 'localhost:7001/payments' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjQiLCJleHAiOjE2NjgyMTc0MjgsImlhdCI6MTY2ODE5OTQyOH0.f23U4zZ5EiMo2--fQgJioVOHQhRUK92Qs9HeErZRt6iDR8TDFW4NugToSZ2VvmYa2Z3L3nZzroD8YjLYOkHecA'
```
