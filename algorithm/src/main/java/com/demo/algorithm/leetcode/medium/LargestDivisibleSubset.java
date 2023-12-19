package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * create by chenglong on 2023/12/14
 * description : 最大整除子集
 *
 * 给你一个由无重复正整数组成的集合nums，请你找出并返回其中最大的整除子集answer，子集中每一元素对(answer[i],answer[j])都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 *
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * nums 中的所有整数互不相同
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> answer = new ArrayList<>();
        if (n == 1) {
            answer.add(nums[0]);
            return answer;
        }
        //1，对数据源进行排序
        Arrays.sort(nums);
        //2，使用动态规划获取最大子集数
        int maxCount = 1;
        int maxIndex = 0;
        int[] marks = new int[n];
        marks[0] = 1;
        for (int i = 1; i < n; i++) {
            marks[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    //此时i可以添加到j后面
                    int tem = marks[j] + 1;
                    if (marks[i] < tem) {
                        marks[i] = tem;
                    }
                }
            }
            if (marks[i] > maxCount) {
                maxCount = marks[i];
                maxIndex = i;
            }
        }
        //3，最大值为nums[maxIndex],然后依次获取其它子集
        answer.add(nums[maxIndex]);
        maxCount--;
        int index = maxIndex - 1;
        while (maxCount >= 0) {
            while (index >= 0) {
                if (marks[index] + 1 == marks[maxIndex] && nums[maxIndex] % nums[index] == 0) {
                    maxIndex = index;
                    answer.add(nums[maxIndex]);
                    maxCount--;
                }
                index--;
            }
            maxCount--;
        }
        Collections.reverse(answer);
        return answer;
    }
}
