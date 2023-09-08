package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/9/8
 * @author chenglong
 * description : 计算列车到站时间
 *
 * 给你一个正整数arrivalTime表示列车正点到站的时间（单位：小时），另给你一个正整数delayedTime表示列车延误的小时数。
 * 返回列车实际到站的时间。
 * 注意，该问题中的时间采用24小时制。
 *
 * 示例 1：
 * 输入：arrivalTime = 15, delayedTime = 5
 * 输出：20
 * 解释：列车正点到站时间是 15:00 ，延误 5 小时，所以列车实际到站的时间是 15 + 5 = 20（20:00）。
 *
 * 示例 2：
 * 输入：arrivalTime = 13, delayedTime = 11
 * 输出：0
 * 解释：列车正点到站时间是 13:00 ，延误 11 小时，所以列车实际到站的时间是 13 + 11 = 24（在 24 小时制中表示为 00:00 ，所以返回 0）。
 *
 * 提示：
 * 1 <= arrivaltime < 24
 * 1 <= delayedTime <= 24
 */
public class FindDelayedArrivalTime {

    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
