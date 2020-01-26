- spring boot can be used to make micro services in java 
- spring boot is neither an application server nor a web server

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
- Spring :most important concept of Spring are dependency injection and Inversion of Control (IOC)
- Spring MVC : decoupled way of developing web application. It comes with simple concept likes Dispatch servlet, Model and View Resolver
- Spring Boot : spring based applications need lot of configurations. It provides auto configurations and starters
