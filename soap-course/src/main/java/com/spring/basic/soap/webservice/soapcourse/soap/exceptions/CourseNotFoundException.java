package com.spring.basic.soap.webservice.soapcourse.soap.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://com.spring.basic.soap.webservice}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String s) {
        super(s);
    }
}
