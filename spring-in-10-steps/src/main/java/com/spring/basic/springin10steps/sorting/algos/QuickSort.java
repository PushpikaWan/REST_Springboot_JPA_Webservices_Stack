package com.spring.basic.springin10steps.sorting.algos;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class QuickSort implements SortingAlgorithm{

    @Override
    public int[] sort(int[] numbers){
        //logic for sort
        return numbers;
    }
}
