package com.study.test.algorithm.impl;

import com.study.test.algorithm.Sort;

/**
 * 选择排序
 * 步骤一：找到最小（大）的元素，放在数组中的起始位置
 * 步骤二：再从剩下的元素中继续寻找最小（大） 的元素，放在已排序部分的末尾
 * 步骤三：重复步骤二，直至所有元素有序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class SelectionSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //初始最大元素的下标
            int max = i;
            //找出是否还存在更大的值
            for (int j = i + 1; j < arr.length; j++) {
                //大于号：从大到小排序
                //小于号：从小到大排序
                if (arr[j] > arr[max]) {
                    //存在更大的值，替换记录的下标
                    max = j;
                }
            }
            //下标发生改变，说明找到更大的值，进行替换
            if (max != i) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }
        return arr;
    }
}
