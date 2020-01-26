package com.spring.basic.springin10steps;

import com.spring.basic.springin10steps.sorting.algos.SortingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pushpika on 1/26/20.
 */
@Component
public class BinarySearchImpl {

    @Autowired
    private SortingAlgorithm sortingAlgorithm;

    public BinarySearchImpl(SortingAlgorithm sortingAlgorithm) {
        super();
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public int binarySearch(int [] numbers, int numberToSearch){
        //sort array
        int[] sortedNumbers = sortingAlgorithm.sort(numbers);

        //search through array
        return 3;
    }
}
