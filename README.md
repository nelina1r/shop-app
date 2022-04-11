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

The token must be passed in the "Bearer_<token>" format to Headers -> Authorization.

Also, you can change token lifetime (durability) in `application.yaml`file, default value == 3600000 mills == 1 hour.
## Api Reference - in progress

#### Add new item

`POST /api/item/`

Body:
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
