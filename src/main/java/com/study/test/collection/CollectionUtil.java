package com.study.test.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * @author PengG
 * @date 2021/1/25 16:11
 */
public class CollectionUtil {
    public static void main(String[] args) {
        CollectionUtil.sortByOtherCollect();
    }

    /**
     * 集合对比排序
     */
    public static void sortByOtherCollect() {
        //将sortList中的集合按照formList排序
        List<Integer> formList = Arrays.asList(2,6,3,1,5,4);
        List<Integer> sortList = Arrays.asList(8,1,7,2,3,4,5,6);
        List<Integer> list = sortList.stream().sorted((o1, o2) -> {
            int o1Index = formList.indexOf(o1);
            int o2Index = formList.indexOf(o2);
            if (o1Index == -1) {
                return 1;
            }
            if (o2Index == -1) {
                return -1;
            }
            return o1Index - o2Index;
        }).collect(Collectors.toList());
        System.out.println(list);
    }
}
