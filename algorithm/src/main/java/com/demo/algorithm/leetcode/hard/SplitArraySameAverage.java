package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/11/14.
 * description : 数组的均值分割
 *
 * 给定你一个整数数组nums
 * 我们要将nums数组中的每个元素移动到A数组或者B数组中，使得A数组和B数组不为空，并且average(A)==average(B)。
 * 如果可以完成则返回true，否则返回false。
 * 注意：对于数组arr, average(arr)是arr的所有元素的和除以arr长度。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7,8]
 * 输出: true
 * 解释: 我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
 *
 * 示例 2:
 * 输入: nums = [3,1]
 * 输出: false
 *
 * 提示:
 * 1 <= nums.length <= 30
 * 0 <= nums[i] <= 10^4
 */
public class SplitArraySameAverage {

    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        //1，特殊场景，数组中只有一个元素时无法分割
        if (n == 1) {
            return false;
        }
        //2，所有元素求和并*n
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            nums[i] *= n;
        }
        //3，所有的元素-sum并按照正负分组
        List<Integer> dates1 = new ArrayList<>();
        List<Integer> dates2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = nums[i] - sum;
            if (cur == 0) {
                return true;
            }
            if (cur > 0) {
                dates1.add(cur);
            } else {
                dates2.add(-cur);
            }
        }
        if (dates1.size() <= 1 || dates2.size() <= 1) {
            return false;
        }
        //4，从dates1中取部分值+dates中部分值和为0，但不是全部
        int total = 0;
        for (int i = 0; i < dates1.size(); i++) {
            total += dates1.get(i);
        }
        Set<Integer> sums = new HashSet<>();
        Set<Integer> nexts = new HashSet<>();
        sums.add(dates1.get(0));
        for (int i = 1; i < dates1.size(); i++) {
            nexts.add(dates1.get(i));
            nexts.addAll(sums);
            for (int key : sums) {
                int tem = key + dates1.get(i);
                nexts.add(tem);
            }
            sums.clear();
            sums.addAll(nexts);
            nexts.clear();
        }
        //此时获取到所有的dates1组合
        Set<Integer> sums2 = new HashSet<>();
        Set<Integer> nexts2 = new HashSet<>();
        if (sums.contains(dates2.get(0))) {
            return true;
        }
        sums2.add(dates2.get(0));
        for (int i = 1; i < dates2.size(); i++) {
            nexts2.addAll(sums2);
            if (sums.contains(dates2.get(i))) {
                return true;
            }
            nexts2.add(dates2.get(i));
            for (int key : sums2) {
                int tem = key + dates2.get(i);
                if (tem != total && sums.contains(tem)) {
                    return true;
                }
                nexts2.add(tem);
            }
            sums2.clear();
            sums2.addAll(nexts2);
            nexts2.clear();
        }
        return false;
    }

}
