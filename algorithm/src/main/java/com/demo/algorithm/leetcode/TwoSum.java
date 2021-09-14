package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/13/21
 * description : 两数之和 II - 输入有序数组
 *
 *  给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 *
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 *  
 * 提示：
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        if (numbers == null || numbers.length < 2 || numbers[0] > target / 2) {
            return result;
        }
        int length = numbers.length;
        for (int i = 0; i < length - 1; i++) {
            int diff = target - numbers[i];
            int index = findIndexByValue(numbers, i + 1, length - 1, diff);
            if (index != -1) {
                result[0] = i + 1;
                result[1] = index + 1;
                return result;
            }
        }
        return result;
    }

    //二分查找
    private int findIndexByValue(int[] numbers, int start, int end, int value) {
        if (value > numbers[end] || value < numbers[start]) {
            return -1;
        }
        if (value == numbers[start]) {
            return start;
        }
        if (value == numbers[end]) {
            return end;
        }
        while (start <= end) {
            int middle = (start + end) / 2;
            if (value == numbers[middle]) {
                return middle;
            } else if (value < numbers[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

}
