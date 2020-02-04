- spring boot can be used to make micro services in java 
- spring boot is neither an application server nor a web server
- we can add any starter package and create service. Most of configurations are done automatically  
  https://www.javatpoint.com/spring-boot-starters

### Features 
- Enable building production ready applications quickly
- Quick starter projects with auto configuration
    - WEB
    - JPA
- Embeded server -> Tomcat, jetty or Undertow
- production ready features
    - metrics and health checks
    - externalized configurations (PROD vs DEV)
- no code generations

## step 01
- go to https://start.spring.io/ and generate spring boiler plate
- need to add additional dependencies there Spring web
- import it as maven / gradle project
- run simple spring context from generated boiler plate

## step 02
- create Rest Controller @RestController annotation
- create Mappings with url there like below @GetMapping annotation. There are other mappings as well for post, delete ...

~~~
@RestController
public class BookController {
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return Collections.singletonList(new Book(1L, "Hath pana", "Kumarathung munidasa"));
    }
}
~~~
- this code segment provide simple example of web server and we can see this book json object by navigating to 'localhost:8080/books'

## step 03
- let's see what's going on under the rest service here (Auto configuration)
- @SpringBootApplication annotation indicate this as a spring context file
- It enables Auto configuration
- It enables component scan in this package and sub packages
- can see more details about auto configuration here -> https://www.springboottutorial.com/spring-boot-auto-configuration
- can see all auto configured thing and what not configured automatically by enabling logs 
``` logging.level.org.springframework= DEBUG ```


# Spring , Spring MVC, Spring boot
- Spring :most important concept of Spring are dependency injection and Inversion of Control (IOC) (
    defining beans, dependencies. loosely coupled application, easily unit testable)
- Spring MVC : decoupled way of developing web application. It comes with simple concept likes Dispatch servlet, Model and View Resolver
- Spring Boot : spring based applications need lot of configurations. It provides auto configurations and starters

## spring-boot-starter-web
- this will add ```spring-boot-starter-tomcat``` as a transitive dependency to start server
- there are several important transitive dependencies
    - spring-web
    - spring-webmvc
    - spring-boot-starter-json -> json support
- any starter have spring-boot-starter

## JPA
- JPA handles how ORM (Object Relation Mapping) done here
- can use with hibernate as well 

## what is HATEOAS ?
- Hypermedia as the Engine of Application State (HATEOAS) is a component of the REST application architecture that distinguishes it from other network application architectures. 
  With HATEOAS, a client interacts with a network application whose application servers provide information dynamically through hypermedia. 
- find out more info.....

## spring-boot-starter-actuator
- advanced monitoring tool
- we can see how beans works and most of under framework stuffs as well
- this contains lot of rest services and those are complaint with hall standards. Therefore we can add hal browser to browse 
through the date

~~~
  <dependency>
     <groupId>org.springframework.data</groupId>
     <artifactId>spring-data-rest-hal-browser</artifactId>
  </dependency>
~~~
- browse - http://localhost:8080/actuator . then u can see details
- can add ``` management.endpoints.web.exposure.include=* ``` to application.properties to see further details in above url
- browse - http://localhost:8080/ can see hal browser there. then we can add ``` /actuator``` there and see all the details there

## spring-boot-devtools
- we can add spring-boot-devtools to pom
- It support hot deploy. no need to restart server when changes are done