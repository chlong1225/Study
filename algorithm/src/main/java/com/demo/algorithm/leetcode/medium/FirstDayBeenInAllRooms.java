package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/3/28
 * @author chenglong
 * description : 访问完所有房间的第一天
 *
 * 你需要访问n个房间，房间从0到n-1编号。同时，每一天都有一个日期编号，从0开始，依天数递增。你每天都会访问一个房间。
 * 最开始的第0天，你访问0号房间。给你一个长度为n且下标从0开始的数组nextVisit。在接下来的几天中，你访问房间的次序将根据下面的规则决定：
 * 假设某一天，你访问i号房间。
 * 如果算上本次访问，访问i号房间的次数为奇数，那么第二天需要访问nextVisit[i]所指定的房间，其中0 <= nextVisit[i] <= i。
 * 如果算上本次访问，访问i号房间的次数为偶数，那么第二天需要访问(i+1) mod n 号房间。
 * 请返回你访问完所有房间的第一天的日期编号。题目数据保证总是存在这样的一天。由于答案可能很大，返回对10^9 + 7取余后的结果。
 *
 * 示例 1：
 * 输入：nextVisit = [0,0]
 * 输出：2
 * 解释：
 * - 第 0 天，你访问房间 0 。访问 0 号房间的总次数为 1 ，次数为奇数。
 *   下一天你需要访问房间的编号是 nextVisit[0] = 0
 * - 第 1 天，你访问房间 0 。访问 0 号房间的总次数为 2 ，次数为偶数。
 *   下一天你需要访问房间的编号是 (0 + 1) mod 2 = 1
 * - 第 2 天，你访问房间 1 。这是你第一次完成访问所有房间的那天。
 *
 * 示例 2：
 * 输入：nextVisit = [0,0,2]
 * 输出：6
 * 解释：
 * 你每天访问房间的次序是 [0,0,1,0,0,1,2,...] 。
 * 第 6 天是你访问完所有房间的第一天。
 *
 * 示例 3：
 * 输入：nextVisit = [0,1,2,0]
 * 输出：6
 * 解释：
 * 你每天访问房间的次序是 [0,0,1,1,2,2,3,...] 。
 * 第 6 天是你访问完所有房间的第一天。
 *
 * 提示：
 * n == nextVisit.length
 * 2 <= n <= 10^5
 * 0 <= nextVisit[i] <= i
 */
public class FirstDayBeenInAllRooms {

    private static final int MOD = 1000000007;

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] marks = new long[n];
        marks[1] = 2;
        for (int i = 2; i < n; i++) {
            if (nextVisit[i - 1] == i - 1) {
                marks[i] = marks[i - 1] + 2;
            } else {
                int next = nextVisit[i - 1];
                marks[i] = 2 * marks[i - 1] + 2 - marks[next] + MOD;
            }
            marks[i] %= MOD;
        }
        return (int) marks[n - 1];
    }
}
