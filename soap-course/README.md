# SOAP Web services
- in spring, typically it uses contract first approach (define format of the request and response before development)
- 

## step 01 - Service Definition (WSDL -> XML , XSD)
 - we need to create XML and we generate XML using XSD - XML Schema Definition
 
 ### XML, XSD
 - XML has namespace to avoid clashes. It'll make uniqueness.
 ~~~
 < xmlns="http://myspring.whateverthingwewant">
 ~~~
- we can add validation to XSD 
-  targetNamespace in XSD - will use to create XML with that xmlns

- in xml we need to add xsi:schemaLocation= "<name space> <xsd file>"

~~~
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://com.spring.basic.soap.webservice course-details.xsd"
~~~

 
 
 ## step 02 - XML java Binding (JAXB - Java API for XML Binding)
 - JAXB generate java files using XSD
 - we need to add plugin for that
 ~~~
 		<plugin>
 				<groupId>org.codehaus.mojo</groupId>
 				<artifactId>jaxb2-maven-plugin</artifactId>
 				<version>1.6</version>
 				<executions>
 					<execution>
 						<id>xjc</id>
 						<goals>
 							<goal>xjc</goal>
 						</goals>
 					</execution>
 				</executions>
 				<configuration>
 				    <schemaDirectory>${project.basedir}/src/main/resources</schemaDirectory>
 				    <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
                     <clearOutputDir>false</clearOutputDir>
 				</configuration>
 			</plugin>
 ~~~
 - then run mvn clean install to generate all java files according to xsd
 
 
 ## step 03 - Framework configuration (Endpoints, WSDL-Generation)
 - this will generate WSDL, Endpoints according to our definitions
 
 ## step 04 - setup SOAP web service client (Chrome plugin - Wizdler)
 - 
 
 
 ### Useful Links
 XML Schema - http://edutechwiki.unige.ch/en/XML_Schema_tutorial_-_Basics
 WSDL URl - http://localhost:8080/ws/courses.wsdl
 Spring Web Services - http://projects.spring.io/spring-ws/