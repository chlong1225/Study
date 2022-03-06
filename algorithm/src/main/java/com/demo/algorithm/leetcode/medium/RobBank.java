package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/6.
 * description :  适合打劫银行的日子
 *
 * 你和一群强盗准备打劫银行。给你一个下标从0开始的整数数组security，其中security[i]是第i天执勤警卫的数量。
 * 日子从0开始编号。同时给你一个整数time。
 * 如果第i天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * 第i天前和后都分别至少有time天。
 * 第i天前连续 time天警卫数目都是非递增的。
 * 第i天后连续 time天警卫数目都是非递减的。
 * 更正式的，第i天是一个合适打劫银行的日子当且仅当：security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * 请你返回一个数组，包含所有适合打劫银行的日子（下标从0开始）。返回的日子可以任意顺序排列。
 *
 * 示例 1：
 * 输入：security = [5,3,3,3,5,6,2], time = 2
 * 输出：[2,3]
 * 解释：
 * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
 * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
 * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
 *
 * 示例 2：
 * 输入：security = [1,1,1,1,1], time = 0
 * 输出：[0,1,2,3,4]
 * 解释：
 * 因为time等于0 ，所以每一天都是适合打劫银行的日子，所以返回每一天。
 *
 * 示例 3：
 * 输入：security = [1,2,3,4,5,6], time = 2
 * 输出：[]
 * 解释：
 * 没有任何一天的前 2 天警卫数目是非递增的。
 * 所以没有适合打劫银行的日子，返回空数组。
 *
 * 示例 4：
 * 输入：security = [1], time = 5
 * 输出：[]
 * 解释：
 * 没有日子前面和后面有 5 天时间。
 * 所以没有适合打劫银行的日子，返回空数组。
 *
 * 提示：
 * 1 <= security.length <= 10^5
 * 0 <= security[i], time <= 10^5
 *
 */
public class RobBank {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        /**
         * 分析：需要找到长度为2*time+1的区间，满足i-time~i之间递减，也包含相同，区间i~i+time之间递增也包含相等。
         * 可以使用双指针+滑动窗口的方式
         */
        List<Integer> result = new ArrayList<>();
        int length = security.length;
        //1，处理特殊场景
        if (time == 0) {
            //time=0代表每天都适合打劫
            for (int i = 0; i < length; i++) {
                result.add(i);
            }
            return result;
        }
        if (length < 2 * time + 1) {
            //无法找到前后长度为time的日志
            return result;
        }
        int start = findBefore(0, security, time);
        if (start == -1) {
            return result;
        }
        while (start < length - time) {
            //start满足起始要求，检查后续要求
            boolean isFind = true;
            for (int i = start + 1; i < start + 1 + time; i++) {
                if (security[i - 1] > security[i]) {
                    isFind = false;
                    break;
                }
            }
            if (isFind) {
                result.add(start);
            }
            if (security[start] >= security[start + 1]) {
                start++;
            } else {
                start = findBefore(start + 1, security, time);
                if (start == -1) {
                    return result;
                }
            }
        }
        return result;
    }

    //从start位置开始查找time+1个非递增的区间，返回区间结束的位置index
    private int findBefore(int start, int[] security, int time) {
        int length = security.length;
        int pre = security[start];
        int count = 1;
        for (int i = start + 1; i < length; i++) {
            if (security[i] <= pre) {
                count++;
                if (count == time + 1) {
                    return i;
                }
            } else {
                //重新开始
                count = 1;
            }
            pre = security[i];
        }
        return -1;
    }

    public List<Integer> goodDaysToRobBank2(int[] security, int time) {
        /**
         * 使用动态规划预处理数据
         */
        List<Integer> result = new ArrayList<>();
        int length = security.length;
        //1，处理特殊场景
        if (time == 0) {
            //time=0代表每天都适合打劫
            for (int i = 0; i < length; i++) {
                result.add(i);
            }
            return result;
        }
        if (length < 2 * time + 1) {
            //无法找到前后长度为time的日志
            return result;
        }
        //记录当前值左边连续大于或等于的数量
        int[] left = new int[length];
        for (int i = time - 1; i >= 0; i--) {
            if (security[i + 1] <= security[i]) {
                left[time]++;
            } else {
                break;
            }
        }
        for (int i = time + 1; i < length - time; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
        }
        //记录当前值右边连续大于或等于的数量
        int[] right = new int[length];
        for (int i = length - time; i < length; i++) {
            if (security[i - 1] <= security[i]) {
                right[length - time - 1]++;
            } else {
                break;
            }
        }
        for (int i = length - time - 2; i >= time; i--) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0;
            }
        }
        for (int i = time; i < length - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                result.add(i);
            }
        }
        return result;
    }
}
