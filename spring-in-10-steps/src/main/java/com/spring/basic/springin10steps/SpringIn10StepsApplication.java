package com.spring.basic.springin10steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringIn10StepsApplication {

    public static void main(String[] args) {

        //no need to create instances. spring manage those
//        BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new BubbleSort());
        ApplicationContext applicationContext = SpringApplication.run(SpringIn10StepsApplication.class, args);
        BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);

        int result = binarySearchImpl.binarySearch(new int[]{5, 35, 6, 12}, 3);
        System.out.println(result);
    }

}
