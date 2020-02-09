package com.spring.basic.rest.webservice.restcourse.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-get")
    public String helloWorldGet() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean(" This is hello world bean");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{value}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String value) {
        return new HelloWorldBean(String.format(" Hello : %s", value));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

}
