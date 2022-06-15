package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2022/6/15
 * @author chenglong
 * description : 找出第K小的数对距离
 *
 * 数对(a,b)由整数a和b组成，其数对距离定义为a和b的绝对差值。
 * 给你一个整数数组nums和一个整数k，数对由nums[i]和nums[j]组成且满足0 <= i < j < nums.length。返回所有数对距离中第k小的数对距离。
 *
 * 示例 1：
 * 输入：nums = [1,3,1], k = 1
 * 输出：0
 * 解释：数对和对应的距离如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 距离第 1 小的数对是 (1,1) ，距离为 0 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1], k = 2
 * 输出：0
 *
 * 示例 3：
 * 输入：nums = [1,6,1], k = 3
 * 输出：5
 *
 * 提示：
 * n == nums.length
 * 2 <= n <= 10^4
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= n * (n - 1) / 2
 */
public class SmallestDistancePair {

    public int smallestDistancePair(int[] nums, int k) {
        /**
         * 由于数对取差值的绝对值，可以不用考虑顺序
         */
        //1，对数组进行排序
        Arrays.sort(nums);
        int min = 0;
        int max = nums[nums.length - 1] - nums[0];
        //2，处理特殊场景
        if (min == max) {
            //此时数组中元素都相同，数对距离都为0
            return 0;
        }
        //3，使用二分法统计指定差值的数量
        while (min <= max) {
            int middle = (max - min) / 2 + min;
            int count = getCount(middle, nums);
            if (count >= k) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return min;
    }

    //获取差值小于等于target的数对数量
    private int getCount(int target, int[] nums) {
        int length = nums.length;
        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            int start = nums[i];
            int max = start + target;
            int index = findIndex(max, i, nums);
            count += (index - i);
            if (index == length - 1) {
                //i之后的数对都满足条件
                int tem = index - (i + 1);
                count += (tem * (tem + 1) / 2);
                break;
            }
        }
        return count;
    }

    //查找小于等于target值的位置
    private int findIndex(int target, int i, int[] nums) {
        int start = i + 1;
        int end = nums.length - 1;
        if (target >= nums[end]) {
            return end;
        }
        if (target < nums[start]) {
            return i;
        }
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (nums[middle] > target) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        if (nums[start] > target) {
            start--;
        }
        return start;
    }
}
