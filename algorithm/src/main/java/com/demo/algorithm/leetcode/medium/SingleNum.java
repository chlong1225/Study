package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/2/14
 * @author chenglong
 * description : 有序数组中的单一元素
 *
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *
 * 提示:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i]<= 10^5
 */
public class SingleNum {

    public int singleNonDuplicate(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int start = 0;
        int end = length - 1;
        while (start < end) {
            int middle = (start + end) >> 1;
            if (nums[middle] != nums[middle - 1] && nums[middle] != nums[middle + 1]) {
                return nums[middle];
            }
            if (nums[middle] == nums[middle - 1]) {
                //统计从middle+1～end的数量
                int count = end - middle;
                if (count % 2 == 0) {
                    //单一元素在start～middle-2之间
                    end = middle - 2;
                } else {
                    //单一元素在middle+1～end之间
                    start = middle + 1;
                }
            } else {
                //此时middle与middle+1位置的数相同。统计从start～middle-1的数量
                int count = middle - start;
                if (count % 2 == 0) {
                    //此时单一元素在middle+2～end之间
                    start = middle + 2;
                } else {
                    //单一元素在start～middle-1之间
                    end = middle - 1;
                }
            }
        }
        return nums[start];
    }
}
