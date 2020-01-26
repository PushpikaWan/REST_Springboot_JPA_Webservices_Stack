# REST_Springboot_JPA_Webservices_Stack
This contains basic concepts and combination of rest, spring boot, JPA and web services. 

## SOAP-based vs REST-styled (REpresantational Stater Traansfer) 
- soap is a protocolo to build web serivce and rest define architecture
- soap can use XML to pass data by request and response
- rest can use any of XML , JSON or other formats with HTTP
- soap strucutre - <SOAP-ENV: envelope>.<SOAP-ENV: headers> <SOAP-ENV: body> ......
- soap use WSDL(Web Service Defenition Language) to define services
- rest use Swagger, WADL or other defenition languages to define services
- SOAP can use HTTP or MQ but Rest need to use HTTTP
- Rest use HTTP method heavily (GET,POST, PUT, DELETE, PATCH)
- Rest is resource oriented. need to have good knowledge about resources to make it better
   - each resources have an URI (Uniform Resource Identifier)
   
 ------ main diff -----
 - Restrictions vs Architectural approach
 - WSDL vd Swagger, WADL...
 - HTTP, MQ vs HTTP only
    

#### Web Service Defenition Language
- defines endpoints, all opertations,request structure and response structure


## what is Spring -
- Simply put, the Spring framework provides comprehensive infrastructure support for developing Java applications.
- It's packed with some nice features like Dependency Injection and out of the box modules like:
    - Spring JDBC
    - Spring MVC
    - Spring Security
    - Spring AOP
    - Spring ORM
    - Spring Test
These modules can drastically reduce the development time of an application.

## What Is Spring Boot?
- Spring Boot is basically an extension of the Spring framework which eliminated the boilerplate configurations required for setting up a Spring application.
- It takes an opinionated view of the Spring platform which paved the way for a faster and more efficient development eco-system.
Here are just a few of the features in Spring Boot:
    - Opinionated ‘starter' dependencies to simplify build and application configuration
    - Embedded server to avoid complexity in application deployment
    - Metrics, Helth check, and externalized configuration
    - Automatic config for Spring functionality – whenever possible

<< soruce - https://www.baeldung.com/spring-vs-spring-boot >>
