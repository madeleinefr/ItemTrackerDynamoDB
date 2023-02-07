# Explanations to the Spring annotations used in the App:

**@SpringBootApplication:**
- It enables the auto-configuration of Spring Boot, a component scan which scans all the components or services and other config files included in the base and sub-packages of the package where the application is located, and allows to import additional configuration classes or to define extra configurations 
- It is basically the equivalent for using @Configuration, @EnableConfiguration and @ComponentScan in just one annotation

**Stereotype Annotations:**
- It indicates to Spring that it should create objects for the classes annotated with stereotype annotations
- @Component, @Controller, @Service, @Repository are Steoreotype annotations

***@Component:***
- It is the top-class of all Steoreotypes
- It is a class-level annotation and indicates that the class is a Spring component/bean
- It tags the Java class as a component so that Spring can add it into the application context which is the place in RAM where Spring saves objects and configuration

***@Controller:***
- It indicates the class as a Spring controller
- It identifies controllers for Spring MVC or Spring WebFlux
- It manages the communication between frontend and backend
- As such it is responsible for handling user requests and return the appropriate response to those requests
- It is mostly used with REST Web Services

***@Service:*** 
- It indicates that a Java class performs some service like executing the business logic.
- It is as specialised form of the @Component annotation which should only be used in the service layer.

***@Repository:***
- It is used on all the Java classes which are responsible for the direct access to the database tables, and as such are dealing with CRUD operations

**@Autowired:**
- It is a bean factory annotation, also called Spring Bean Autowiring
- Spring looks into its objects and takes the object(=bean) and initiates the field at runtime
- @Autowired annotation can be used on Setters, Constructors and Properties

**@RestController:**
- It is not a stereotype annotation, but a web bind annotation
- It is a specialized version of the controller.
- It already includes the @Controller and @ResponseBody annotations, so the implementation of the controller is simplified (e.g. you don't need to add @ResponseBody anymore)
- Every request handling method of the controller class automatically serializes return objects into HttpResponse
- The data which we get while using the @RestController is in JSON format

**@RequestMapping**
- This annotation is used to map web requests onto specific handler classes and methods and make web resources addressable
- The value parameter is used to specify the request URI path on controller class name
- Use @GetMapping, @PutMapping, @PostMapping annotations on methods to handle different types of incoming HTTP request methods

**@CrossOrigin**
- This annotation enables cross-origin resource sharing on all handler methods of this class.
- By default, it allows all origins, all headers and the HTTP methods specified in the @RequestMapping

**@ComponentScan**
- This annotation is used to specify the packages that we want to be scanned
- Without an argument it tells Spring to scan the current package and all of its sub-packages 
- The base package to be scanned can be specified in brackets

**@SpringBootTest**
- This annotation tells Spring Boot to look for a main configuration class(normally one with the @SpringBootApplication annotation) and use that to start a Spring application context
- We can specify that Spring Boot should start the server with a random port for testing by adding the following after the annotation: (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
- Specifying a random port is useful because like this we can avoid conflicts in test environments

