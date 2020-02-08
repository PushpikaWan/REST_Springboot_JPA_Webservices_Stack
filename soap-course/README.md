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
 - we need to create Endpoint java classes first
 - we need to add ```@Endpoint``` annotation to show it as endpoint to spring (add it to class)
 - we need to add other annotations like below code segment to narrow down request and response and add compatibility there
 
 @PayloadRoot(namespace = "http://com.spring.basic.soap.webservice", localPart = "GetCourseDetailsRequest")
 @ResponsePayload - convert java to xml back
 @RequestPayload 
 ~~~
 @Endpoint
 public class CourseDetailsEndpoint {
 
     @PayloadRoot(namespace = "http://com.spring.basic.soap.webservice", localPart = "GetCourseDetailsRequest")
     @ResponsePayload
     public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
         GetCourseDetailsResponse response = new GetCourseDetailsResponse();
         return response;
     }
 }
 ~~~
 
 - need to create configuration class for spring and add following data
 ~~~
 //enable spring web services
 @EnableWs
 //spring configuration
 @Configuration
 ~~~
 - need to define a servlet to handle all the requests and map simple URI to that
 - need to configure wsdl as well. can see all the necessary configuration in WebServiceConfig file
 - can see wsdl file here ```http://localhost:8080/ws/courses.wsdl``` can learn about structure by navigating through it below
~~~
<wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:sch="http://com.spring.basic.soap.webservice" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://com.spring.basic.soap.webservice/courses" targetNamespace="http://com.spring.basic.soap.webservice/courses">
<wsdl:types>
    <xs:schema xmlns:tns="http://com.spring.basic.soap.webservice" xmlns:xs="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified" targetNamespace="http://com.spring.basic.soap.webservice">
        <xs:element name="GetCourseDetailsRequest">
            <xs:complexType>
                <xs:sequence>
                    <xs:element name="id" type="xs:int"/>
                </xs:sequence>
            </xs:complexType>
        </xs:element>
        <xs:element name="GetCourseDetailsResponse">
            <xs:complexType>
                <xs:sequence>
                    <xs:element name="CourseDetails" type="tns:CourseDetails"/>
                </xs:sequence>
            </xs:complexType>
        </xs:element>
        <xs:element name="GetAllCourseDetailsRequest">
            <xs:complexType> </xs:complexType>
        </xs:element>
        <xs:element name="GetAllCourseDetailsResponse">
            <xs:complexType>
                <xs:sequence>
                    <xs:element maxOccurs="unbounded" name="CourseDetails" type="tns:CourseDetails"/>
                </xs:sequence>
            </xs:complexType>
        </xs:element>
        <xs:complexType name="CourseDetails">
            <xs:sequence>
                <xs:element name="id" type="xs:int"/>
                <xs:element name="name" type="xs:string"/>
                <xs:element name="description" type="xs:string"/>
            </xs:sequence>
        </xs:complexType>
    </xs:schema>
</wsdl:types>
<wsdl:message name="GetAllCourseDetailsResponse">
    <wsdl:part element="sch:GetAllCourseDetailsResponse" name="GetAllCourseDetailsResponse"> </wsdl:part>
</wsdl:message>
<wsdl:message name="GetAllCourseDetailsRequest">
    <wsdl:part element="sch:GetAllCourseDetailsRequest" name="GetAllCourseDetailsRequest"> </wsdl:part>
</wsdl:message>
<wsdl:message name="GetCourseDetailsRequest">
    <wsdl:part element="sch:GetCourseDetailsRequest" name="GetCourseDetailsRequest"> </wsdl:part>
</wsdl:message>
<wsdl:message name="GetCourseDetailsResponse">
    <wsdl:part element="sch:GetCourseDetailsResponse" name="GetCourseDetailsResponse"> </wsdl:part>
</wsdl:message>
<wsdl:portType name="CoursePort">
    <wsdl:operation name="GetAllCourseDetails">
        <wsdl:input message="tns:GetAllCourseDetailsRequest" name="GetAllCourseDetailsRequest"> </wsdl:input>
        <wsdl:output message="tns:GetAllCourseDetailsResponse" name="GetAllCourseDetailsResponse"> </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCourseDetails">
        <wsdl:input message="tns:GetCourseDetailsRequest" name="GetCourseDetailsRequest"> </wsdl:input>
        <wsdl:output message="tns:GetCourseDetailsResponse" name="GetCourseDetailsResponse"> </wsdl:output>
    </wsdl:operation>
</wsdl:portType>
<wsdl:binding name="CoursePortSoap11" type="tns:CoursePort">
    <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
    <wsdl:operation name="GetAllCourseDetails">
        <soap:operation soapAction=""/>
        <wsdl:input name="GetAllCourseDetailsRequest">
            <soap:body use="literal"/>
        </wsdl:input>
        <wsdl:output name="GetAllCourseDetailsResponse">
            <soap:body use="literal"/>
        </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCourseDetails">
        <soap:operation soapAction=""/>
        <wsdl:input name="GetCourseDetailsRequest">
            <soap:body use="literal"/>
        </wsdl:input>
        <wsdl:output name="GetCourseDetailsResponse">
            <soap:body use="literal"/>
        </wsdl:output>
    </wsdl:operation>
</wsdl:binding>
<wsdl:service name="CoursePortService">
    <wsdl:port binding="tns:CoursePortSoap11" name="CoursePortSoap11">
        <soap:address location="http://localhost:8080/ws"/>
    </wsdl:port>
</wsdl:service>
</wsdl:definitions>
~~~

 
 ## step 04 - setup SOAP web service client (Chrome plugin - Wizdler)
 - we can define custom errors, exceptions as well and it will provide more descriptive error with soap-fault payload
 - ```@SoapFault(faultCode = FaultCode.CLIENT)``` annotation can be used with exception class to provide to show 
 where the exception comes from
 - we can provide custom code as well to be more descriptive
 ~~~
 @SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://com.spring.basic.soap.webservice}001_COURSE_NOT_FOUND")
 ~~~
 
 ## step 05 - Security with WS-Security
 - XWSS (XML and Web Service Security)
 - need to add below dependencies 
 
 ~~~
 <dependency>
 			<groupId>org.springframework.ws</groupId>
 			<artifactId>spring-ws-security</artifactId>
 			<exclusions>
 				<exclusion>
 					<groupId>org.springframework.security</groupId>
 					<artifactId>spring-security-core</artifactId>
 				</exclusion>
 			</exclusions>
 		</dependency>
 		<dependency>
 			<groupId>com.sun.xml.wss</groupId>
 			<artifactId>xws-security</artifactId>
 			<version>3.0</version>
 			<exclusions>
 				<exclusion>
 					<groupId>javax.xml.crypto</groupId>
 					<artifactId>xmldsig</artifactId>
 				</exclusion>
 			</exclusions>
 		</dependency>
 		<dependency>
 			<groupId>javax.activation</groupId>
 			<artifactId>activation</artifactId>
 			<version>1.1.1</version>
 		</dependency>
 ~~~
 - we need to add xwsSecurityInterceptor to intercept all requests
    - need to create callback handler
    - need to create security policy
    - other than that we need to extends config class with ```WsConfigurerAdapter``` and override addInterceptor(..) method as well
    
   ~~~
       @Override
       public void addInterceptors(List<EndpointInterceptor> interceptors) {
           interceptors.add(securityInterceptor());
       }
   ~~~
 
 
 ### Useful Links
 XML Schema - http://edutechwiki.unige.ch/en/XML_Schema_tutorial_-_Basics
 WSDL URl - http://localhost:8080/ws/courses.wsdl
 Spring Web Services - http://projects.spring.io/spring-ws/