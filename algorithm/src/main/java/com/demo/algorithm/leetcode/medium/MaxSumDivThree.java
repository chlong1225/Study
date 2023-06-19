package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2023/6/19
 * @author chenglong
 * description : 可被三整除的最大和
 *
 * 给你一个整数数组nums，请你找出并返回能被三整除的元素最大和。
 *
 * 示例 1：
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 *
 * 示例 2：
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *
 * 提示：
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class MaxSumDivThree {

    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        //1，按照对3取余进行数据分类。
        List<Integer> dates1 = new ArrayList<>();
        List<Integer> dates2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] % 3 == 1) {
                dates1.add(nums[i]);
            } else if (nums[i] % 3 == 2) {
                dates2.add(nums[i]);
            }
        }
        if (sum % 3 == 0) {
            return sum;
        }
        //此时需要分类讨论删除的数
        //2，排序
        Collections.sort(dates1);
        Collections.sort(dates2);
        if (sum % 3 == 1) {
            //删除余数为1的或两个余数为2的
            if (dates1.size() > 0) {
                if (dates2.size() >= 2) {
                    if (dates1.get(0) >= dates2.get(0) + dates2.get(1)) {
                        sum -= dates2.get(0);
                        sum -= dates2.get(1);
                    } else {
                        sum -= dates1.get(0);
                    }
                } else {
                    sum -= dates1.get(0);
                }
            } else {
                sum -= dates2.get(0);
                sum -= dates2.get(1);
            }
        } else {
            //删除余数为2的数
            if (dates1.size() > 1) {
                if (dates2.size() > 0) {
                    if (dates2.get(0) > dates1.get(0) + dates1.get(1)) {
                        sum -= dates1.get(0);
                        sum -= dates1.get(1);
                    } else {
                        sum -= dates2.get(0);
                    }
                } else {
                    sum -= dates1.get(0);
                    sum -= dates1.get(1);
                }
            } else {
                sum -= dates2.get(0);
            }
        }
        return sum;
    }
}
