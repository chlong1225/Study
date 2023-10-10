package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/10/10
 * @author chenglong
 * description : 移动机器人
 *
 * 有一些机器人分布在一条无限长的数轴上，他们初始坐标用一个下标从0开始的整数数组nums表示。当你给机器人下达命令时，它们以每秒钟一单位的速度开始移动。
 * 给你一个字符串s，每个字符按顺序分别表示每个机器人移动的方向。'L' 表示机器人往左或者数轴的负方向移动，'R'表示机器人往右或者数轴的正方向移动。
 * 当两个机器人相撞时，它们开始沿着原本相反的方向移动。
 * 请你返回指令重复执行d秒后，所有机器人之间两两距离之和。由于答案可能很大，请你将答案对10^9+7取余后返回。
 * 注意：
 * 对于坐标在i和j的两个机器人，(i,j) 和 (j,i) 视为相同的坐标对。也就是说，机器人视为无差别的。
 * 当机器人相撞时，它们立即改变它们的前进时间，这个过程不消耗任何时间。
 * 当两个机器人在同一时刻占据相同的位置时，就会相撞。
 * 例如，如果一个机器人位于位置0并往右移动，另一个机器人位于位置2并往左移动，下一秒，它们都将占据位置1，并改变方向。再下一秒钟后，第一个机器人位于位置0并往左移动，而另一个机器人位于位置2并往右移动。
 * 例如，如果一个机器人位于位置0并往右移动，另一个机器人位于位置1并往左移动，下一秒，第一个机器人位于位置0并往左行驶，而另一个机器人位于位置1并往右移动。
 *
 * 示例 1：
 * 输入：nums = [-2,0,2], s = "RLL", d = 3
 * 输出：8
 * 解释：
 * 1 秒后，机器人的位置为 [-1,-1,1] 。现在下标为 0 的机器人开始往左移动，下标为 1 的机器人开始往右移动。
 * 2 秒后，机器人的位置为 [-2,0,0] 。现在下标为 1 的机器人开始往左移动，下标为 2 的机器人开始往右移动。
 * 3 秒后，机器人的位置为 [-3,-1,1] 。
 * 下标为 0 和 1 的机器人之间距离为 abs(-3 - (-1)) = 2 。
 * 下标为 0 和 2 的机器人之间的距离为 abs(-3 - 1) = 4 。
 * 下标为 1 和 2 的机器人之间的距离为 abs(-1 - 1) = 2 。
 * 所有机器人对之间的总距离为 2 + 4 + 2 = 8 。
 *
 * 示例 2：
 * 输入：nums = [1,0], s = "RL", d = 2
 * 输出：5
 * 解释：
 * 1 秒后，机器人的位置为 [2,-1] 。
 * 2 秒后，机器人的位置为 [3,-2] 。
 * 两个机器人的距离为 abs(-2 - 3) = 5 。
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * -2 * 10^9 <= nums[i] <= 2 * 10^9
 * 0 <= d <= 10^9
 * nums.length == s.length
 * s 只包含'L'和'R'。
 * nums[i]互不相同。
 */
public class SumDistance {

    private static final int MODE = 1000000007;

    public int sumDistance(int[] nums, String s, int d) {
        //1，机器人移动后的位置
        long[] dates = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (s.charAt(i) == 'L') {
                dates[i] = (long) nums[i] - d;
            } else {
                dates[i] = (long) nums[i] + d;
            }
        }
        //2，对机器人位置进行排序
        Arrays.sort(dates);
        //3，统计机器人之间的距离
        /**
         * i位置的距离：(dates[i]-dates[i-1] + dates[i] - dates[i-2] + dates[i] - dates[i-3] .... + dates[i]-dates[0])
         * i*dates[i] - (dates[i-1]+dates[i-2] +...dates[0])
         */
        long sum = dates[0];
        long total = 0;
        for (int i = 1; i < dates.length; i++) {
            total += (i * (dates[i] % MODE) - sum) % MODE;
            total %= MODE;
            if (total < 0) {
                total += MODE;
            }
            sum += dates[i];
            sum %= MODE;
        }
        return (int) total;
    }
}
