package com.study.test.algorithm.impl;

import com.study.test.algorithm.Sort;

/**
 * 快速排序
 */
public class QuickSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 分区思想：
     * 步骤一：先将大的和小的各放一边
     * 步骤二：取中间基准点，将左边部分再次重复步骤一
     * 步骤三：将基准点的右边部分重复步骤一
     */
    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //计算出基准点，先将右边放部分进行排序后，再对左边进行排序
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
