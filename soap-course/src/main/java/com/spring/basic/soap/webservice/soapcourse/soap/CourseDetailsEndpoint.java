package com.spring.basic.soap.webservice.soapcourse.soap;


import com.spring.basic.soap.webservice.soapcourse.soap.bean.Course;
import com.spring.basic.soap.webservice.soapcourse.soap.exceptions.CourseNotFoundException;
import com.spring.basic.soap.webservice.soapcourse.soap.services.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import webservice.soap.basic.spring.com.*;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService courseDetailsService;

    @PayloadRoot(namespace = "http://com.spring.basic.soap.webservice", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        Course course = courseDetailsService.findById(request.getId());
        if(course == null){
            throw new CourseNotFoundException("Invalid course id : "+request.getId());
        }
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    @PayloadRoot(namespace = "http://com.spring.basic.soap.webservice", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        List<Course> courses = courseDetailsService.findAll();
        for (Course course : courses) {
            response.getCourseDetails().add(mapCourse(course));
        }
        return response;
    }

    @PayloadRoot(namespace = "http://com.spring.basic.soap.webservice", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(courseDetailsService.deleteById(request.getId())));
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        return status == CourseDetailsService.Status.SUCCESS ? Status.SUCCESS : Status.FAILURE;
    }
}
