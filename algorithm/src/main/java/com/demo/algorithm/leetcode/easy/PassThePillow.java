package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/9/26
 * @author chenglong
 * description : 递枕头
 *
 * n个人站成一排，按从1到n编号。
 * 最初，排在队首的第一个人拿着一个枕头。每秒钟，拿着枕头的人会将枕头传递给队伍中的下一个人。一旦枕头到达队首或队尾，传递方向就会改变，队伍会继续沿相反方向传递枕头。
 * 例如，当枕头到达第n个人时，TA会将枕头传递给第n-1个人，然后传递给第n-2个人，依此类推。
 * 给你两个正整数n和time，返回time秒后拿着枕头的人的编号。
 *
 * 示例 1：
 * 输入：n = 4, time = 5
 * 输出：2
 * 解释：队伍中枕头的传递情况为：1 -> 2 -> 3 -> 4 -> 3 -> 2 。
 * 5 秒后，枕头传递到第 2 个人手中。
 *
 * 示例 2：
 * 输入：n = 3, time = 2
 * 输出：3
 * 解释：队伍中枕头的传递情况为：1 -> 2 -> 3 。
 * 2 秒后，枕头传递到第 3 个人手中。
 *
 * 提示：
 * 2 <= n <= 1000
 * 1 <= time <= 1000
 */
public class PassThePillow {

    public int passThePillow(int n, int time) {
        //重复到当前位置的时间
        int repeatTime = (n - 1) * 2;
        time %= repeatTime;
        if (time <= n - 1) {
            //此时在1～n之间传递
            return time + 1;
        }
        //此时在n～1之间传递
        time -= (n - 1);
        return n - time;
    }
}
