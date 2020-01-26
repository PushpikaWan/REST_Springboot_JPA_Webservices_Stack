- main understanding points
    - loose coupling
    - dependency injection
    
 - annotations -  ```@Component```, ``` @Autowired ```, ``` @Primary ```, ``` @SpringBootApplication ```
    

## step 01
- go to https://start.spring.io/ and generate spring boiler plate
- import it as maven / gradle project
- run simple spring context from generated boiler plate

## step 02
- in this example we going to search through binary search tree
    - inside that we can use any sorting algorithms like bubble sort, quick sort, selection sort , etc..
    - then when we use algorithm inside binary search it cannot be changed. tightly coupled
  
## step 03
-  we can resolve this tight coupling using interface and implementation
-  Then we can pass algorithm to binary search through constructor
 

## step 04
- beans (instance of the class object)
- we can create beans and wire it
- need to provide three things
    - what are the beans
    - what are the dependencies for the bean
    - where to search for beans
    
- 01. what are the beans - need to add @Component annotation to class mark as a bean (bubble sort, quick sort, binary search impl)
- 02. what are the dependencies for the bean -  need to add @Autowired annotation to dependent field 
- 03. where to search for beans - need to add package and sub package to search spring boot come with that @SpringBootApplication
annotation. It says to search package and sub packages inside this

- then we do not need to create instances and manage those
- SpringApplication manage all of these

~~~
        //no need to create instances. spring manage those
//        BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new BubbleSort());
        ApplicationContext applicationContext = SpringApplication.run(SpringIn10StepsApplication.class, args);
        BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

        int result = binarySearchImpl.binarySearch(new int[]{5, 35, 6, 12}, 3);
~~~

## step 05 
- this is to understanding what's going on background
- can put ``` logging.level.org.springframework = debug ``` inside application.properties to enable debug of spring 
    - 01. searching for class (component search) which has @component 
    - 02. try to create beans when it try to create binary search algo it needs bubble sort therefore it create bubble bean first. 
    like wise it create dependencies and create beans
    - 03. @autowire beans through constructor or setter injection

## step 06
- If there are two or more same type classes are marked as @component, then it cannot resolve.
    - we can use @primary annotation to prioratized one

### step 07
- There are three ways to @Autowired
    - 01. Constructor injection 
    - 02. Setter injection
    - 03. Field injection
- recommend to inject all mandatory dependencies through constructor
- in now a days Field injection is preferred, due to large number of fields are not good as constructor  


### Heavily used Spring Projects
- Spring boot
- Spring cloud - build cloud native app
- Spring Data - consistent data access (sql / nosql)
- Spring Batch
- Spring Security
- Spring HATEOAS
- Spring Integration
- Spring mobile , Spring Android

### spring cons
- good testing support (unit test, integration....)
- no plumbing codes (no unnecessary exception handling..)
- flexible architecture (MVC,.. )
- uptodate
