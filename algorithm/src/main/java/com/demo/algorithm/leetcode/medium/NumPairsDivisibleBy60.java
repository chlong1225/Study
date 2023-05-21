package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/5/8
 * @author chenglong
 * description : 总持续时间可被60整除的歌曲
 *
 * 在歌曲列表中，第i首歌曲的持续时间为time[i]秒。
 * 返回其总持续时间（以秒为单位）可被60整除的歌曲对的数量。形式上，我们希望下标数字i和j满足i < j且有(time[i] + time[j])%60==0。
 *
 * 示例 1：
 * 输入：time = [30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整除：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 *
 * 示例 2：
 * 输入：time = [60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整除。
 *
 * 提示：
 * 1 <= time.length <= 6 * 10^4
 * 1 <= time[i] <= 500
 */
public class NumPairsDivisibleBy60 {

    public int numPairsDivisibleBy60(int[] time) {
        //1，对时间进行取模操作并记录数量
        int[] marks = new int[60];
        for (int i = 0; i < time.length; i++) {
            marks[time[i] % 60]++;
        }
        //2，统计符合条件的数据
        long base = 1;
        long total = 0;
        if (marks[0] != 0) {
            total += (base * marks[0] * (marks[0] - 1) / 2);
        }
        if (marks[30] != 0) {
            total += (base * marks[30] * (marks[30] - 1) / 2);
        }
        for (int i = 1; i < 30; i++) {
            if (marks[i] != 0 && marks[60 - i] != 0) {
                //此时计算组合数
                total += (base * marks[i] * marks[60 - i]);
            }
        }
        return (int) total;
    }
}
