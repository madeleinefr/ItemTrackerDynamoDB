# Explanations to the Spring annotations used in the App:

##@SpringBootApplication:
    - enables the auto-configuration of Spring Boot, a componen scan which scans the sub-packages of the package where the application is located, and allows to import additional configuration classes or to define extra configurations
    - it is basically the equivalent for using @Configuration, @EnableConfiguration and @ComponentScan in just one annotation

##Stereotype Annotations
    - indicate to Spring that it should create objects for the classes annotated with stereotype annotations
    - @Component, @Controller, @Service, @Repository are Steoreotype annotations

###@Component
    - is the top-class of all steoreotypes
    - it is a class-levele annotation and indicates that the class is a Spring component/bean
    - It tags the Java class as a component so that Spring can add it into the application context
      which is the place in RAM where Spring saves objects and configuration

###@Controller
    - indicates the class as a Spring controller
    - Identifies controllers for Spring MVC or Spring WebFlux
    - manages the communication between frontend and backend
    - as such are responsible for handling user requests and return the appropriate response to those requests
    - mostly used with REST Web Services
    - uses HTML

###@Service 
    - indicates that a Java class performs some service like executing the business logic.
    - It is as specialised form of the @Component annotation which should only be used in the service layer.


##@Repository
    - is used on all the Java classes which are responsible for the direct access to the database tables,
      and as such are dealing with CRUD operations

##@Autowired:
    - beans factory annotation
    - looks into its objects and takes the object and initiates the field

##@RestController:
    - not a stereotyp annotation, but a web bind annotation
    - uses Json
