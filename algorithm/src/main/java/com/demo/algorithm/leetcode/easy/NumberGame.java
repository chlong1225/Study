package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 2023/12/29
 * @author chenglong
 * description : 最小数字游戏
 *
 * 你有一个下标从0开始、长度为偶数的整数数组nums，同时还有一个空数组arr。Alice和Bob决定玩一个游戏，游戏中每一轮Alice和Bob都会各自执行一次操作。游戏规则如下：
 * 每一轮，Alice 先从nums中移除一个最小元素，然后Bob执行同样的操作。
 * 接着，Bob会将移除的元素添加到数组arr中，然后Alice也执行同样的操作。
 * 游戏持续进行，直到nums变为空。
 * 返回结果数组arr。
 *
 * 示例 1：
 * 输入：nums = [5,4,2,3]
 * 输出：[3,2,5,4]
 * 解释：第一轮，Alice 先移除 2 ，然后 Bob 移除 3 。然后 Bob 先将 3 添加到 arr 中，接着 Alice 再将 2 添加到 arr 中。于是 arr = [3,2] 。
 * 第二轮开始时，nums = [5,4] 。Alice 先移除 4 ，然后 Bob 移除 5 。接着他们都将元素添加到 arr 中，arr 变为 [3,2,5,4] 。
 *
 * 示例 2：
 * 输入：nums = [2,5]
 * 输出：[5,2]
 * 解释：第一轮，Alice 先移除 2 ，然后 Bob 移除 5 。然后 Bob 先将 5 添加到 arr 中，接着 Alice 再将 2 添加到 arr 中。于是 arr = [5,2] 。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * nums.length % 2 == 0
 */
public class NumberGame {

    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] answer = new int[n];


        for (int i = 0; i < n; i += 2) {
            answer[i + 1] = nums[i];
            answer[i] = nums[i + 1];
        }
        return answer;
    }
}
