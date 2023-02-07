# Explanations to the Spring annotations used in the App:

**@SpringBootApplication:**
- enables the auto-configuration of Spring Boot, a component scan which scans all the components or services and other config files included in the base and sub-packages of the package where the application is located, and allows to import additional configuration classes or to define extra configurations 
- it is basically the equivalent for using @Configuration, @EnableConfiguration and @ComponentScan in just one annotation

**Stereotype Annotations**
- indicate to Spring that it should create objects for the classes annotated with stereotype annotations
- @Component, @Controller, @Service, @Repository are Steoreotype annotations

***@Component***
- is the top-class of all steoreotypes
- it is a class-level annotation and indicates that the class is a Spring component/bean
- It tags the Java class as a component so that Spring can add it into the application context which is the place in RAM where Spring saves objects and configuration

***@Controller***
- indicates the class as a Spring controller
- Identifies controllers for Spring MVC or Spring WebFlux
- manages the communication between frontend and backend
- as such it is responsible for handling user requests and return the appropriate response to those requests
- mostly used with REST Web Services
- uses HTML

***@Service*** 
- indicates that a Java class performs some service like executing the business logic.
- It is as specialised form of the @Component annotation which should only be used in the service layer.


***@Repository***
- is used on all the Java classes which are responsible for the direct access to the database tables, and as such are dealing with CRUD operations

**@Autowired:**
- it is a beans factory annotation, also called Spring Bean Autowiring
- Spring looks into its objects and takes the object(=bean) and initiates the field at runtime
- @Autowired annotation can be used on Setters, Constructors and Properties

**@RestController:**
- not a stereotype annotation, but a web bind annotation
- The data which we get while using the @RestController is in JSON format
