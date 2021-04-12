package com.study.test.algorithm.impl;

import com.study.test.algorithm.Sort;

/**
 * 插入排序
 * 基本思想：每一步将一个待排序的数据插入到前面已经排好序的有序序列中，直到插完所有元素为止。
 * 第一步：将数组分为左右两部分，左边是有序的，右边是无序的。
 * 第二步：每次从右边取出一个元素在左边进行比较，找出比自身大的元素。
 * 第三步：寻找过程中，将比自己大的元素往后移。
 * 第四步：如果右边取出的元素与左边的某个元素相等，则插入到左边相等元素的后面。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class InsertSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为1的左边只有下标为0的一个元素，默认为有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 记录插入的位置
            int j = i;
            // 在左边有序序列中中逐个比较寻找比自己大的元素
            while (j > 0 && tmp < arr[j - 1]) {
                // 寻找过程中，每次将比当前元素大的往后移
                arr[j] = arr[j - 1];
                j--;
            }
            // 找到比自己大的元素的位置，将要插入的数据放入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }
}
