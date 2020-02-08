package com.spring.basic.soap.webservice.soapcourse.soap.services;

import com.spring.basic.soap.webservice.soapcourse.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    public enum Status{
        SUCCESS, FALIURE;
    }

    private static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Java", "This is a Java teaching course");
        courses.add(course1);
        Course course2 = new Course(2, "Python", "This is a Python teaching course");
        courses.add(course2);
        Course course3 = new Course(3, "Angular", "This is an Angular teaching course");
        courses.add(course3);
        Course course4 = new Course(4, "spring", "This is a spring teaching course");
        courses.add(course4);

    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public List<Course> findAll() {
        return courses;
    }

    public Status deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                iterator.remove();
                return Status.SUCCESS;
            }
        }

        return Status.FALIURE;
    }
}
