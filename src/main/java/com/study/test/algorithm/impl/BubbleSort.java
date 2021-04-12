package com.study.test.algorithm.impl;

import com.study.test.algorithm.Sort;

/**
 * 冒泡排序
 * 比较两个相邻的元素，如果第一个比第二个大吗，就交换它们
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class BubbleSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //如果未发生交换，则表明数组已经处于有序状态，排序已经完成
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                //大于号：从小到大排序
                //小于号：从大到小排序
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }
}
