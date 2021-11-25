package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/24.
 * description : 分割等和子集
 *
 * 给你一个 只包含正整数的非空数组nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class SplitSubset {

    public boolean canPartition(int[] nums) {
        int length = nums.length;
        //1,特殊场景只有一个数据时无法拆分
        if (length == 1) {
            return false;
        }
        //2,求和并找最大值
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            sum += nums[i];
        }
        //3,再次判断特殊场景
        if ((sum & 1) == 1) {
            //奇数无法拆分
            return false;
        }
        sum = sum >> 1;
        if (max > sum) {
            //最大值超过一半,剩余的值组合也无法分割
            return false;
        }
        if (max == sum) {
            //刚好直接分成两个子集
            return true;
        }
        /**
         * 4，使用动态规划构建二维表,交替打表判断当前值是否可以被求和。
         * i：上次与本次交替计算，所以i =2
         * j：代表对应求和值。j最大sum。长度定义：sum+1
         * 每个数字对应两种场景：添加，不添加
         */
        boolean[][] marks = new boolean[2][sum + 1];
        //4.1,初始化默认都为false。求和为0的可以都不选为true
        marks[0][0] = true;
        marks[1][0] = true;
        marks[0][nums[0]] = true;
        for (int i = 1; i < length; i++) {
            int num = nums[i];
            for (int j = 1; j < sum + 1; j++) {
                marks[i & 1][j] = marks[(i - 1) & 1][j];
                if (j >= num) {
                    marks[i & 1][j] = marks[i & 1][j] || marks[(i - 1) & 1][j - num];
                }
            }
        }
        return marks[(length - 1) & 1][sum];
    }

    public boolean canPartition2(int[] nums) {
        int length = nums.length;
        //1,特殊场景只有一个数据时无法拆分
        if (length == 1) {
            return false;
        }
        //2,求和并找最大值
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            sum += nums[i];
        }
        //3,再次判断特殊场景
        if ((sum & 1) == 1) {
            //奇数无法拆分
            return false;
        }
        sum = sum >> 1;
        if (max > sum) {
            //最大值超过一半,剩余的值组合也无法分割
            return false;
        }
        if (max == sum) {
            //刚好直接分成两个子集
            return true;
        }
        /**
         * 4，优化为一维数组
         * i：代表对应求和值。j最大sum。长度定义：sum+1
         * 每个数字对应两种场景：添加，不添加
         */
        boolean[] marks = new boolean[sum + 1];
        //4.1,初始化默认都为false。求和为0的可以都不选为true
        marks[0] = true;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            for (int j = sum; j >= num; j--) {
                marks[j] = marks[j] || marks[j - num];
                if (j == sum) {
                    if (marks[j]) {
                        return true;
                    }
                    if (i == length - 1) {
                        return marks[j];
                    }
                }
            }
        }
        return marks[sum];
    }
}
