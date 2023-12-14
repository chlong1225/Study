package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/12/13
 * @author chenglong
 * description : 最大数
 *
 * 给定一组非负整数nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return "" + nums[0];
        }
        Integer[] dates = new Integer[n];
        for (int i = 0; i < n; i++) {
            dates[i] = nums[i];
        }
        Arrays.sort(dates, (o1, o2) -> {
            if (o1 == 0) {
                return o2;
            }
            if (o2 == 0) {
                //build1 =
                return -o1;
            }
            //比较o1+o2与o2+o1的大小
            long base1 = 10;
            long base2 = 10;
            while (base1 <= o2) {
                base1 *= 10;
            }
            while (base2 <= o1) {
                base2 *= 10;
            }
            long build1 = base1 * o1 + o2;
            long build2 = base2 * o2 + o1;
            if (build2 > build1) {
                return 1;
            }
            if (build2 == build1) {
                return 0;
            }
            return -1;
        });
        if (dates[0] == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(dates[i]);
        }
        return builder.toString();
    }
}
