# Web Market API

## A web market API project, based on e-commerce platform patterns. Backend with API Rest, JWT token access and SQL queries. 

###### Technologies

 - Java 1.8
 - Git 2.9.0
 - Maven project
 - Spring boot 2.3.1

**Production**
 - MySQL 15.1 
 
**Deployment**
 - Heroku
 - Heroku MySQL

**Test**
 - H2 database
 - JUnit
 
###### How to clone the project
	git clone https://github.com/llaet/ErrorLog-API

###### How to run the project with Maven
	cd WebMarket-Backend
  	mvn run
	
###### What is an API?
 - [What is? - en](https://www.redhat.com/en/topics/api/what-are-application-programming-interfaces)

###### Project based on orginal version from Nélio Alves
[Spring Boot, Hibernate, REST, Ionic, JWT, S3, MySQL, MongoDB](https://www.udemy.com/course/spring-boot-ionic/)

###### HTTP verbs to API requests:
	Access token		    | POST  | /login - body content type: application/json
        Refresh access token	    | POST  | /auth/refresh_token - authorization: Bearer
	New User password	    | POST  | /auth/forgot - body content type: application/json
    	Post new order		    | POST  | /pedidos - body content type: application/json
    	Post new customer	    | POST  | /clientes - body content type: application/json
  	Post new category	    | POST  | /categorias - body content type: application/json
  	Update a customer           | PUT   | /clientes - body content type: application/json | path variable: Integer ID
  	Update a category           | PUT   | /categorias - body content type: application/json | path variable: Integer ID
  	Delete customer by ID       | DELETE| /clientes/{id} - path variable: Integer ID
  	Delete category by ID	    | DELETE| /categorias/{id} - path variable: Integer ID
	Get product by ID           | GET   | /produtos/{id} - path variable: Integer ID
  	Get customer by ID	    | GET   | /clientes/{id} - path variable: Integer ID
  	Get order by ID		    | GET   | /pedidos/{id} - path variable: Integer ID
  	Get category by ID	    | GET   | /categorias/{id} - path variable: Integer ID
  	Get all products            | GET   | /produtos - query param's: nome, categorias, page, linesPerPage, orderBy, direction
  	Get all customers pageable  | GET   | /clientes/paginar - query param's: page, linesPerPage, orderBy, direction
  	Get all categories pageable | GET   | /categorias/paginar - query param's: page, linesPerPage, orderBy, direction
  	Get all customers           | GET   | /clientes
	Get all categories          | GET   | /categorias

###### About me:
  - [Linkedin](https://www.linkedin.com/in/lucas-laet-b47452187/)
  - :e-mail: lucas.laetlira@gmail.com

###### Feel free to contribute in this project!
