package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 9/30/21
 * @author chenglong
 * description : 最接近的三数之和
 *
 * 给定一个包括n个整数的数组nums和一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 *
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3<= nums[i]<= 10^3
 * -10^4<= target<= 10^4
 */
public class ThreeSumNear {

    public int threeSumClosest(int[] nums, int target) {
        //1，对数据排序
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        if (sum == target) {
            return sum;
        }
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                //查找最接近find的值
                int find = target - nums[i] - nums[j];
                int findIndex = findIndex(j + 1, nums, find);
                int tem = nums[i] + nums[j] + nums[findIndex];
                if (tem == target) {
                    return target;
                }
                if (Math.abs(tem - target) < Math.abs(sum - target)) {
                    sum = tem;
                }
            }
        }
        return sum;
    }

    //查找最接近target的index，从start位置开始
    private int findIndex(int start, int[] nums, int target) {
        int end = nums.length - 1;
        if (target <= nums[start]) {
            return start;
        }
        if (nums[end] <= target) {
            return end;
        }
        while (start < end) {
            int middle = (end + start) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] < target) {
                start = middle;
            } else {
                end = middle;
            }
            if (end - start == 1) {
                break;
            }
        }
        if (Math.abs(target - nums[start]) > Math.abs(target - nums[end])) {
            return end;
        }
        return start;
    }
}
