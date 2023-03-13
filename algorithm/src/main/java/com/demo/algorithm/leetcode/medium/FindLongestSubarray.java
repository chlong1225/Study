package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/3/13
 * @author chenglong
 * description : 面试题17.05.  字母与数字
 *
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 *
 * 示例 1:
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 *
 * 示例 2:
 * 输入: ["A","A"]
 * 输出: []
 *
 * 提示：
 * array.length <= 100000
 */
public class FindLongestSubarray {

    public String[] findLongestSubarray(String[] array) {
        if (array == null || array.length < 2) {
            return new String[]{};
        }
        int length = array.length;
        //1，数据转换，字母代表1，数字代表-1。字母与数字相同，则当前区间和为0
        int[] sums = new int[length];
        for (int i = 0; i < length; i++) {
            if (array[i].charAt(0) >= '0' && array[i].charAt(0) <= '9') {
                //当前位置为数字
                sums[i] = -1;
            } else {
                sums[i] = 1;
            }
        }
        //2，计算前缀和
        for (int i = 1; i < length; i++) {
            sums[i] = sums[i] + sums[i - 1];
        }
        //3，区间i～j只和2为0等价于0～i之和与0～j之和相同
        int minIndex = Integer.MAX_VALUE;
        int maxCount = 0;
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < length; i++) {
            //0~i之和。左右都是闭区间
            int cur = sums[i];
            if (cur == 0) {
                //此时[0~i]区间满足
                if (i + 1 >= maxCount) {
                    maxCount = i + 1;
                    minIndex = 0;
                }
            }else{
                if (marks.containsKey(cur)) {
                    int index = marks.get(cur);
                    int count = i - index;
                    if (count > maxCount) {
                        maxCount = count;
                        minIndex = index + 1;
                    } else if (count == maxCount) {
                        if (index < minIndex) {
                            minIndex = index + 1;
                        }
                    }
                } else {
                    marks.put(cur, i);
                }
            }
        }
        String[] result = new String[maxCount];
        for (int i = 0; i < maxCount; i++) {
            result[i] = array[minIndex + i];
        }
        return result;
    }
}
