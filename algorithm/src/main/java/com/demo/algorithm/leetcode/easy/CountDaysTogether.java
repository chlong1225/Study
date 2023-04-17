package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/4/17
 * @author chenglong
 * description : 统计共同度过的日子数
 *
 * Alice和Bob计划分别去罗马开会。
 * 给你四个字符串arriveAlice，leaveAlice，arriveBob和leaveBob。Alice会在日期arriveAlice到leaveAlice之间在城市里（日期为闭区间），而Bob在日期arriveBob到leaveBob之间在城市里（日期为闭区间）。
 * 每个字符串都包含5个字符，格式为"MM-DD"，对应着一个日期的月和日。
 * 请你返回Alice和Bob同时在罗马的天数。
 * 你可以假设所有日期都在同一个自然年，而且不是闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]。
 *
 * 示例 1：
 * 输入：arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
 * 输出：3
 * 解释：Alice 从 8 月 15 号到 8 月 18 号在罗马。Bob 从 8 月 16 号到 8 月 19 号在罗马，他们同时在罗马的日期为 8 月 16、17 和 18 号。所以答案为 3 。
 *
 * 示例 2：
 * 输入：arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
 * 输出：0
 * 解释：Alice 和 Bob 没有同时在罗马的日子，所以我们返回 0 。
 *
 * 提示：
 * 所有日期的格式均为"MM-DD"。
 * Alice和Bob的到达日期都早于或等于他们的离开日期。
 * 题目测试用例所给出的日期均为非闰年的有效日期。
 */
public class CountDaysTogether {

    private static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //统计截止到月底的天数
    static {
        for (int i = 1; i < days.length; i++) {
            days[i] += days[i - 1];
        }
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startA = getDayIndex(arriveAlice);
        int endA = getDayIndex(leaveAlice);
        int startB = getDayIndex(arriveBob);
        int endB = getDayIndex(leaveBob);
        if (startA > endB || endA < startB) {
            return 0;
        }
        return Math.min(endA, endB) - Math.max(startA, startB) + 1;
    }

    private int getDayIndex(String data) {
        int month = Integer.parseInt(data.substring(0, 2));
        int day = Integer.parseInt(data.substring(3));
        return days[month - 1] + day;
    }
}
