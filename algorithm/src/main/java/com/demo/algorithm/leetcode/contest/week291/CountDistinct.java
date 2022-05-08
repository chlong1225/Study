package com.demo.algorithm.leetcode.contest.week291;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/5/8.
 * description : 含最多K个可整除元素的子数组
 *
 * 给你一个整数数组nums和两个整数k和p，找出并返回满足要求的不同的子数组数，要求子数组中最多k个可被p整除的元素。
 * 如果满足下述条件之一，则认为数组nums1和nums2是不同数组：
 * 两数组长度不同或者
 * 存在至少一个下标i满足nums1[i]!=nums2[i]。
 * 子数组定义为：数组中的连续元素组成的一个非空序列。
 *
 * 示例 1：
 * 输入：nums = [2,3,3,2,2], k = 2, p = 2
 * 输出：11
 * 解释：
 * 位于下标 0、3 和 4 的元素都可以被 p = 2 整除。
 * 共计 11 个不同子数组都满足最多含 k = 2 个可以被 2 整除的元素：
 * [2]、[2,3]、[2,3,3]、[2,3,3,2]、[3]、[3,3]、[3,3,2]、[3,3,2,2]、[3,2]、[3,2,2] 和 [2,2] 。
 * 注意，尽管子数组 [2] 和 [3] 在 nums 中出现不止一次，但统计时只计数一次。
 * 子数组 [2,3,3,2,2] 不满足条件，因为其中有 3 个元素可以被 2 整除。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4], k = 4, p = 1
 * 输出：10
 * 解释：
 * nums 中的所有元素都可以被 p = 1 整除。
 * 此外，nums 中的每个子数组都满足最多 4 个元素可以被 1 整除。
 * 因为所有子数组互不相同，因此满足所有限制条件的子数组总数为 10 。
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i], p <= 200
 * 1 <= k <= nums.length
 */
public class CountDistinct {

    public int countDistinct(int[] nums, int k, int p) {
        int length = nums.length;
        //1，统计当前位置以及之前位置整除的数量
        int[] counts = new int[length];
        if (nums[0] % p == 0) {
            counts[0] = 1;
        }
        for (int i = 1; i < length; i++) {
            counts[i] = counts[i - 1];
            if (nums[i] % p == 0) {
                counts[i]++;
            }
        }
        int sum = 0;
        Map<Integer, List<int[]>> marks = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                //i~j之间连续子数组
                int total = counts[j];
                if (i > 0) {
                    total -= counts[i - 1];
                }
                if (total > k) {
                    break;
                }
                //i~j之间的数量
                int space = j - i + 1;
                int[] indexs = {i, j};
                if (marks.containsKey(space)) {
                    List<int[]> dates = marks.get(space);
                    if (!compareDate(indexs, dates, nums)) {
                        sum++;
                        dates.add(indexs);
                    }

                } else {
                    List<int[]> tem = new ArrayList<>();
                    tem.add(indexs);
                    marks.put(space, tem);
                    sum++;
                }
            }
        }
        return sum;
    }

    private boolean compareDate(int[] indexs, List<int[]> dates, int[] nums) {
        int compareStart = indexs[0];
        int length = indexs[1] - compareStart + 1;
        for (int i = 0; i < dates.size(); i++) {
            int start = dates.get(i)[0];
            int count = 0;
            for (int j = 0; j < length; j++) {
                if (nums[j + compareStart] == nums[j + start]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == length) {
                return true;
            }
        }
        return false;
    }
}
