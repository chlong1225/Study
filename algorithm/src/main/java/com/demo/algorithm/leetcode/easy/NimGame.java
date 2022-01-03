package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/1/3.
 * description : Nim游戏
 *
 * 你和你的朋友，两个人一起玩Nim游戏：
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉1-3块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为n的情况下赢得游戏。
 * 如果可以赢，返回true；否则，返回false 。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：false
 * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
 *      因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：true
 *
 * 示例 3：
 * 输入：n = 2
 * 输出：true
 *  
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class NimGame {

    //使用动态规划(超出内存限制)
    public boolean canWinNim(int n) {
        /**
         * 由于操作存在关联性，使用动态规划进行状态转移
         * marks[i]: 石头数量为i+1时是否获胜的状态。
         * i+1：当前石头的数量
         * 状态转移方程: marks[i] = !(marks[i-1]&&marks[i-2]&&marks[i-3])
         */
        if (n <= 3) {
            return true;
        }
        boolean[] marks = new boolean[n];
        //1，初始条件：
        marks[0] = true;
        marks[1] = true;
        marks[2] = true;
        for (int i = 3; i < n; i++) {
            boolean result = marks[i - 1] && marks[i - 2] && marks[i - 3];
            marks[i] = !result;
        }
        return marks[n - 1];
    }

    //通过找规律
    public boolean canWinNim2(int n) {
        return n % 4 != 0;
    }
}
