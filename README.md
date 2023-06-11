
# Spring Boot

Spring boot is an open-source tool that makes the development of microservices and web applications using Java-based frameworks easier. It uses Spring underneath.

## Why Spring Boot?

- Uses Spring underneath, makes it easier to use Spring.
- Spring Boot framework is used to create rest services with MVC.
- Auto configuration [reduce the hassle of manual configuration such as- JAR dependency, config setup, server installation].
- Helps to avoid dependency conflicts.
- Includes embedded http server (Tomcat, Jetty, Undertow ...). 

## Spring Initializr
* Create a starter spring boot app easily.
* Dependency can be selected by developers.
* Maven/ Gradle project create.
* Provides an embedded http server [no need to install server].

## Quick word on Maven
* Project management tool using which automatically libraries can be imported from online.
* It can manage a project's build, reporting and documentation.
* Reduce the hassle of downloading JARs and adding them to buildpath/classpath manually.

### Advantages of Maven
* Dependency Management (No more missing JARs)
* Building and Running your Project (No more build path / classpath issues)
* Standard directory structure

### Maven Key Concepts
* POM (Project Object Model) file pom.xml
    - Configuration file for your project
    - Located in the root of Maven project

* Project Coordinates (groupId, artifactId, version [GAV])
	- Project Coordinates uniquely identify a project
	- Group ID: Name of company, group, or organization. Convention is to use reverse domain name (com.milacodesonmac)
	- Artifact ID: Name for this project: myfirstapp
	- Version: A specific release version like: 1.0, 1.6, 2.0 … If project is under active development then: 1.0-SNAPSHOT
	- Example:
```
        <groupId>org.springframework</groupId>
  		<artifactId>spring-context</artifactId>
  		<version>6.0.0</version>
```
* Dependency Coordinates: Adding dependency- add groupId and artifactId. version is optional.

### Run Maven Project with Wrapper files
Run project with the following command:
```bash
    mvnw
```
If maven is not installed, this command installs maven automatically. If maven is installed previously, ignore the wrapper files mvnw, mvnw.cmd.

### Maven POM file (pom.xml)
Includes info entered while creating the project with spring initializr. We can also include dependencies, starters, plugins in the pom.xml file.

### Spring boot maven plugin
To package executable jar/war archive. Also to run the app easily.
```
./mvnw package
./mvnw spring-boot:run
```
## Spring boot starters
* A collection of maven dependencies grouped together
* Reduces the amount of Maven configuration 
	- For example, when building a Spring MVC app, we normally need - spring-webmvc, hibernate-validator, thymeleaf ... Spring Boot provides: spring-boot-starter-web which contains- spring-web, spring-webmvc, hibernate-validator, json, tomcat etc.	  
	- It saves the developers from having all the individual dependencies. Also makes sure you have the compatible versions
	- In Spring Initializr, simply select Web dependency
	- You automatically get spring-boot-starter-web in pom.xml
### Spring boot starter parent
* Spring Boot provides a "Starter Parent"
* This is a special starter that provides Maven defaults (Java version, Default compiler level, UTF-8 source encoding ...)
* Included in pom.xml when using initializr
* Includes a version which other starters inherit

## Spring boot devtools
* spring-boot-devtools automatically restarts your app when code is updated
* Simply add the dependency to your POM file

## Spring boot actuator
* Exposes endpoints to monitor and manage your application 
* Simply add the dependency to POM file
* REST endpoints are automatically added to application
* Endpoints are prefixed with: /actuator
* ..../actuator/health checks the status of your application (Normally used by monitoring apps to see if app is up or down)
* By default, only /health is exposed
* The /info endpoint can provide information about your application. To expose /info
```
File: src/main/resources/application.properties
```
	management.endpoints.web.exposure.include=health,info
	management.info.env.enabled=true
	info.app.name=My App
  	info.app.description=A first spring boot app!
  	info.app.version=1.0.0

* To expose all actuator endpoints: 
```
management.endpoints.web.exposure.include=*
```
## Spring boot actuator - Security
* You may NOT want to expose all of this information
* Spring Security will prompt for login (username: user (by default), password generated in console log)
* You can override default user name and generated password
```
File: src/main/resources/application.properties
```
	spring.security.user.name=mila
	spring.security.user.password=cat
* You can customize Spring Security for Spring Boot Actuator. Use a database for roles, encrypted passwords etc ...
* Excluding endpoints: e.g., To exclude /health,
```
File: src/main/resources/application.properties
```
	management.endpoints.web.exposure.exclude=health

## Running from the Command-Line
* Two options for running the app
	- Option 1: Use java -jar
	```
	java -jar myfirstapp.jar
	```
	- Option 2: Use Spring Boot Maven plugin
	```
    mvnw spring-boot:run
	```
### Spring Boot - Custom Application Properties
* Your app to be configurable ... (no hard-coding of values) 
* Read app configuration from a properties file
* Define custom properties in application.properties 
* Inject properties into Spring Boot application using @Value
```
	# Define custom properties
	coach.name=Mila
	team.name=The Teaching Club
```

* For example,
```
	@RestController
	public class FirstRestController {
	// inject properties for: coach.name and team.name
	@Value("${coach.name}")
 	private String coachName;
 	@Value("${team.name}")
 	private String teamName;
... }
```
### Spring boot properties
* Web properties example:
```bash
 # HTTP server port
 server.port=7070 --> will run on 7070 port
 # Context path of the application
 server.servlet.context-path=/my-app --> app endpoints will be prefixed with my-app
 # Default HTTP session time out
 server.servlet.session.timeout=15m
```
* Core properties example [TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF]:
```
 # Log levels severity mapping
 logging.level.org.springframework=DEBUG
 logging.level.org.hibernate=TRACE
 logging.level.com.luv2code=INFO
 # Log file name
 logging.file=my-crazy-stuff.log
```
* Actuator Properties example:
```
 # Endpoints to include by name or wildcard
 management.endpoints.web.exposure.include=*
 # Endpoints to exclude by name or wildcard
 management.endpoints.web.exposure.exclude=beans,mapping
 # Base path for actuator endpoints
 management.endpoints.web.base-path=/actuator
```
* Data properties example:
```
 # JDBC URL of the database
 spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
 # Login username of the database
 spring.datasource.username=scott
 # Login password of the database
 spring.datasource.password=tiger
```
## Inversion of Control
* A design principle that enables classes to be loosely coupled, making them simpler to test and manage. 
* Outsources the construction and management of objects.
* Transfers the control of objects or portions of a program to a container or framework. 
### Dependency Injection (DI)
* A pattern used to establish IoC where the control being inverted is setting an object's dependencies.
* The client transfers the responsibility of providing its dependencies to another object.
* For example in traditional programming an object dependency is created by the following way where we need to instantiate an implementation of the Item interface within the Store class itself,
```
    public class Store {
        private Item item;
 
        public Store() {
            item = new ItemImpl1();    
        }
    }
```
* By using DI, the example can be rewritten without specifying the implementation of the Item that is desired.
```
    public class Store {
        private Item item;
	
        public Store(Item item) {
            this.item = item;
        }
    }
```
### The Spring IoC Container

* IoC container is a feature of frameworks that use IoC. 
* The Spring container is responsible for instantiating, configuring and assembling objects known as *beans*, as well as managing their life cycles.
* Primary functions of Spring Container-
    - Create and manage objects *(Inversion of Control)*
    - Inject object dependencies *(Dependency Injection)*

#### Configuring Spring Container
* XML configuration file (not recommended)
* Java Annotations
* Java Source Code

### Injection Type
* There are multiple types of injection with Spring.
* The two recommended types of injection-
    - Constructor Injection
    - Setter Injection
