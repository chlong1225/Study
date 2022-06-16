package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/6/16
 * @author chenglong
 * description : 数组中的k-diff数对
 *
 * 给定一个整数数组和一个整数k，你需要在数组里找到不同的k-diff数对，并返回不同的k-diff数对的数目。
 * 这里将k-diff数对定义为一个整数对(nums[i], nums[j])，并满足下述全部条件：
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * 注意，|val| 表示 val 的绝对值。
 *
 * 示例 1：
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 *
 * 示例 2：
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 *
 * 示例 3：
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1)。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 */
public class FindPairs {

    public int findPairs(int[] nums, int k) {
        //1，对数组进行排序
        Arrays.sort(nums);
        int count = 0;
        int length = nums.length;
        //2，遍历查找
        for (int i = 0; i < length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                //相同的数字不处理
                continue;
            }
            //数对中需要查找的另一个值
            int find = nums[i] + k;
            if (find == nums[length - 1]) {
                count++;
                break;
            }
            if (find > nums[length - 1]) {
                break;
            }
            int index = findIndex(find, i + 1, nums);
            if (index != -1) {
                count++;
            }
        }
        return count;
    }

    private int findIndex(int find, int start, int[] nums) {
        int end = nums.length - 1;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (nums[middle] == find) {
                return middle;
            }
            if (nums[middle] > find) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
}
