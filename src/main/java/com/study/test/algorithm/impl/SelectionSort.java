package com.study.test.algorithm.impl;

import com.study.test.algorithm.Sort;

/**
 * 选择排序
 * 步骤一：找到最小（大）的元素，放在数组中的起始位置
 * 步骤二：再从剩下的元素中继续寻找最小（大） 的元素，放在已排序部分的末尾
 * 步骤三：重复步骤二，直至所有元素有序
 */
public class SelectionSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            if (max != i) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }
        return arr;
    }
}
