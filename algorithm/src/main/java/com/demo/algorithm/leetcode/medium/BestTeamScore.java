package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/3/22
 * @author chenglong
 * description : 无矛盾的最佳球队
 *
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数总和。
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支没有矛盾的球队。如果一名年龄较小球员的分数严格大于一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * 给你两个列表scores和ages，其中每组scores[i]和ages[i]表示第i名球员的分数和年龄。请你返回所有可能的无矛盾球队中得分最高那支的分数 。
 *
 * 示例 1：
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 *
 * 示例 2：
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 *
 * 示例 3：
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 *
 * 提示：
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 */
public class BestTeamScore {

    public int bestTeamScore(int[] scores, int[] ages) {
        int length = ages.length;
        int[][] dates = new int[length][2];
        for (int i = 0; i < length; i++) {
            dates[i][0] = scores[i];
            dates[i][1] = ages[i];
        }
        Arrays.sort(dates, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        int[] marks = new int[length];
        int max = dates[0][0];
        marks[0] = max;
        for (int i = 1; i < length; i++) {
            //遍历小于指定最大的年龄的最大分数，此时叠加:data[i][0]才不会超出限制。
            int maxAge = dates[i][1];
            for (int j = 0; j < i; j++) {
                if (dates[j][1] <= maxAge) {
                    marks[i] = Math.max(marks[j], marks[i]);
                }
            }
            marks[i] += dates[i][0];
            if (max < marks[i]) {
                max = marks[i];
            }
        }
        return max;
    }
}
