# Store's warehouse management application

This app emulates store's warehouse simple business logic (adding, searching, updating, deleting, buying and decommissioning products)

## Tech Stack

**Client:** Java 11+, Spring Boot, Spring Data, Spring Security, Hibernate, Liquibase, Apache Maven, Swagger, MapStruct

**Database:** PostgreSQL 13+


## Run Locally

The project uses Docker, so initially you have to build and run the image using docker-compose.

Go to the 'docker' directory:
```bash
  cd docker
```

Then, use:

```bash
  docker-compose up
```
App will be available at 
`localhost:8080`, if you use Docker Desktop

Or at `192.168.99.100:8080`, if you use Docker Toolbox
with Linux VM 

## Authentication
The authentication is done via the Authorization header by providing an access-token. All mentioned body parameters are required.
#### Registration


`POST /api/auth/register`

Body:
```bash
{
  "username": "username",
  "password": "password"
}
```
Response:
```bash
registered
```
#### Authorization

`POST /api/auth/login`

Body:
```bash
{
  "username": "username",
  "password": "password"
}
```
Response:
```bash
{
    "username": "username",
    "token": "<token>"
}
```

The token must be passed in the "Bearer_<<token>>" format to Headers -> Authorization.

Also, you can change token lifetime (durability) in `application.yaml`file, default value == 3600000 mills == 1 hour.
## Api Reference - in progress
### For Items
#### Add new item

`POST /api/item/`

Body (example):
```bash
{
  "name": "name",
  "price": 300000,
  "quantity": 10,
  "category": "IT",
  "shopname": "shop_name",
  "status": "ONSTORAGE"
}
```
Response:
```bash
registered
```
#### Get all items

`GET /api/item/`

Response (example):
```bash
{
  {
    "name": "name",
    "price": 300000,
    "quantity": 10,
    "category": "IT",
    "shopname": "shop_name",
    "status": "ONSTORAGE"
  }
  {
    "name": "name2",
    "price": 400000,
    "quantity": 15,
    "category": "IT",
    "shopname": "shop_name2",
    "status": "BOUGHT"
  }
}
```
#### Get item by Id

`GET /api/item/{id}`

Response (example):
```bash
{
  "name": "name",
  "price": 300000,
  "quantity": 10,
  "category": "IT",
  "shopname": "shop_name",
  "status": "ONSTORAGE"
}
```
#### Delete item by Id

`DELETE /api/item/{id}`

Response:
```bash
deleted
```
#### Buy item by Id

`POST /api/item/{id}`

Response (example):
```bash
{
  "name": "name",
  "price": 300000,
  "quantity": 10,
  "category": "IT",
  "shopname": "shop_name",
  "status": "BOUGHT"
}
```
#### Debite all items

`POST /api/item/debite`

Response (example):
```bash
debited
```
### For Shops

#### Add new shop

`POST /api/shop/`

Body (example):
```bash
{
  "name": "name",
  "address": 300000
}
```
Response :
```bash
added
```
#### Get all shops

`GET /api/shop/`

Response (example):
```bash
{
  {
  "name": "name",
  "address": "Pushkin st. kolotushkin's house"
  }
  {
  "name": "name2",
  "address": "address2"
  }
}
```
#### Get shop by Id

`GET /api/shop/{id}`

Response (example):
```bash
{
  "name": "name",
  "address": "address"
}
```
#### Delete shop by Id

`DELETE /api/shop/{id}`

Response:
```bash
deleted
```
