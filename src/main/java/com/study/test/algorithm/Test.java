package com.study.test.algorithm;

import com.study.test.algorithm.impl.BubbleSort;
import com.study.test.algorithm.impl.InsertSort;
import com.study.test.algorithm.impl.QuickSort;
import com.study.test.algorithm.impl.SelectionSort;

public class Test {

    public static void main(String[] args) {
//        Sort sort = new BubbleSort();
//        Sort sort = new SelectionSort();
//        Sort sort = new QuickSort();
        Sort sort = new InsertSort();
        int[] arr = new int[]{5, 1, 7, 3, 9, 2, 6};
        arr = sort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
