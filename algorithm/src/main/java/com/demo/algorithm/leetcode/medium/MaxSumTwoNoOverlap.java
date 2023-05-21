package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/26
 * @author chenglong
 * description : 两个非重叠子数组的最大和
 *
 * 给你一个整数数组nums和两个整数firstLen和secondLen，请你找出并返回两个非重叠子数组中元素的最大和，长度分别为firstLen和secondLen。
 * 长度为firstLen的子数组可以出现在长为secondLen的子数组之前或之后，但二者必须是不重叠的。
 * 子数组是数组的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
 * 输出：20
 * 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
 *
 * 示例 2：
 * 输入：nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
 * 输出：29
 * 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
 *
 * 示例 3：
 * 输入：nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
 * 输出：31
 * 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
 *
 * 提示：
 * 1 <= firstLen, secondLen <= 1000
 * 2 <= firstLen + secondLen <= 1000
 * firstLen + secondLen <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class MaxSumTwoNoOverlap {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        //1，依次统计firstLen长度对应的和
        int m1 = n - firstLen + 1;
        int[] firstDate = new int[m1];
        int sum = 0;
        for (int i = 0; i < firstLen; i++) {
            sum += nums[i];
        }
        firstDate[0] = sum;
        for (int i = 1; i < m1; i++) {
            sum -= nums[i - 1];
            sum += nums[firstLen - 1 + i];
            firstDate[i] = sum;
        }
        //2，统计secondLen右边对应的和
        int m2 = n - secondLen + 1;
        int[] rights = new int[m2];
        sum = 0;
        for (int i = 0; i < secondLen; i++) {
            sum += nums[i];
        }
        rights[0] = sum;
        for (int i = 1; i < m2; i++) {
            sum -= nums[i - 1];
            sum += nums[secondLen - 1 + i];
            rights[i] = sum;
        }
        //3，统计secondLen左边对应的,index从n-1～secondLen
        int[] lefts = new int[m2];
        lefts[0] = rights[0];
        for (int i = 1; i < m2; i++) {
            lefts[i] = Math.max(lefts[i - 1], rights[i]);
        }
        //4，统计当前位置向右最大连续区间和，index从0～n-secondLen
        for (int i = m2 - 2; i >= 0; i--) {
            rights[i] = Math.max(rights[i], rights[i + 1]);
        }
        int max = 0;
        //5，依次遍历获取最大值
        for (int i = 0; i < m1; i++) {
            //secondLen在左边时最大index
            int maxLeftIndex = i - secondLen;
            if (maxLeftIndex >= 0) {
                int tem = firstDate[i] + lefts[maxLeftIndex];
                if (max < tem) {
                    max = tem;
                }
            }
            //secondLen在右边最小index
            int minRightIndex = i + firstLen;
            if (minRightIndex < m2) {
                int tem = firstDate[i] + rights[minRightIndex];
                if (max < tem) {
                    max = tem;
                }
            }
        }
        return max;
    }
}
