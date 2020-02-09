# REST Web services
- Rest services are Resource oriented 
- Each resources have URIs
- Try this with simple social application 

## Resource

#### Resources and URI Mappings
- Retrieve all Users - GET /users
- Create a User - POST /users
- Retrieve one User - GET /users/{id} -> /users/1
- Delete a User - DELETE /users/{id} -> /users/1

- Retrieve all posts for a User - GET /users/{id}/posts
- Create a posts for a User - POST /users/{id}/posts
- Retrieve details of a post - GET /users/{id}/posts/{post_id}

- ** default no argument constructor, getters, setters are required for resource classes
- in good practices we need to provide created. can use ResponseEntity to provide that

~~~
    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
~~~

#### path parameters and query parameters
- ```GET /users/{id}/``` this id is path parameter
~~~
    @GetMapping(path = "/hello-world-bean/path-variable/{value}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String value) {
        return new HelloWorldBean(String.format(" Hello : %s", value));
    }
~~~
- 

## RestController
- need to add @RestController annotation to class
- then we can add method with ```@RequestMapping(method = RequestMethod.GET, path = "/hello-world")``` annotation\
- we can use specific mapping as well without @RequestMapping ```@GetMapping(path = "/hello-world")```
~~~
@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
}
~~~
- ```@ControllerAdvice``` - annotation share that controller through all controllers
- ```extends ResponseEntityExceptionHandler``` - to provide standard exception handler

## validation
- we can add ```@Valid ``` annotation with parameter and we can add validation annotations to resource file like (@size(min=2), @past)

~~~
 @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @Past
    private Date birthDay;
~~~

## Auto config doing lots of background stuffs
- can see all auto configuration by enabling debug in application
- dispatcher servlet also auto configured 
- and other error related pages also auto configured
~~~
DispatcherServletAutoConfiguration.DispatcherServletRegistrationConfiguration#dispatcherServletRegistration matched:
      - @ConditionalOnBean (names: dispatcherServlet types: org.springframework.web.servlet.DispatcherServlet; SearchStrategy: all) found bean 'dispatcherServlet' (OnBeanCondition)
~~~
- HttpMessageConverter also auto configured. It will convert that bean data to json format with Jackson2ObjectMapper
~~~
 HttpMessageConvertersAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.converter.HttpMessageConverter' (OnClassCondition)
      - NoneNestedConditions 0 matched 1 did not; NestedCondition on HttpMessageConvertersAutoConfiguration.NotReactiveWebApplicationCondition.ReactiveWebApplication did not find reactive web application classes (HttpMessageConvertersAutoConfiguration.NotReactiveWebApplicationCondition)
~~~
- dispatcher servlet handling all the request here.
- it contains all the mapping there and it will direct to right controller and  those request to relevant points
 
## HATEOAS - Hypermedia As The Engine Of the Application State
- need to add dependency first
- can provide useful link related to response with response
~~~
   EntityModel<User> resource = new EntityModel<>(user);
   WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
   resource.add(linkTo.withRel("all-users"));
~~~
-can see output like
~~~
{
    "id": 1,
    "name": "Adam",
    "birthDay": "2020-02-09T13:15:23.084+0000",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}
~~~

### Internationalization
- configuration
    - LocaleResolver
        - Default Locale - Locale.US
    - ResourceBundleMessageSource 
    
- usage
    - Autowire MessageSource
    - @RequestHeader(value = "Accept-Language", required = false) Locale locale
    - messageSource.getMessage("helloWorld.message",null, locale)
    
~~~
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message ",null, locale);
    }    
~~~
- need to pass locale with headers, otherwise it take default locale
- we can remove header argument and add it as below
~~~
  @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
~~~
- we can add ```spring.messages.basename=messages``` this to application.properties and we can remove below method 
~~~
    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
~~~

### content negotiation
- in current implementation, all request support json type content. we can try this out by adding custom header in postman
    - change ```Accept``` to Application/xml (current json) , then it wont work
- we can add xml support using below dependency
~~~
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>
~~~
- then we can get xml input and output there

### documentation using swagger
- add below dependencies

~~~
    <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger2</artifactId>
       <version>2.6.1</version>
    </dependency>
    <dependency>
       <groupId>io.springfox</groupId>
       <artifactId>springfox-swagger-ui</artifactId>
       <version>2.6.1</version>
    </dependency>
~~~
- add SwaggerConfig file and see all the configuration there
- can see documentation in both places 
    - http://localhost:8080/v2/api-docs
    - http://localhost:8080/swagger-ui.html
