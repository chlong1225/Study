package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/11/23
 * @author chenglong
 * description : 盒子中小球的最大数量
 *
 * 你在一家生产小球的玩具厂工作，有n个小球，编号从lowLimit开始，到highLimit结束（包括lowLimit和highLimit，即n == highLimit - lowLimit + 1）。另有无限数量的盒子，编号从1到infinity。
 * 你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号321的小球应当放入编号3 + 2 + 1 = 6的盒子，而编号10的小球应当放入编号1 + 0 = 1的盒子。
 * 给你两个整数lowLimit和highLimit，返回放有最多小球的盒子中的小球数量。如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 *
 * 示例 1：
 * 输入：lowLimit = 1, highLimit = 10
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
 * 编号 1 的盒子放有最多小球，小球数量为 2 。
 *
 * 示例 2：
 * 输入：lowLimit = 5, highLimit = 15
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
 * 小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
 * 编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
 *
 * 示例 3：
 * 输入：lowLimit = 19, highLimit = 28
 * 输出：2
 * 解释：
 * 盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
 * 小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
 * 编号 10 的盒子放有最多小球，小球数量为 2 。
 *
 * 提示：
 * 1 <= lowLimit <= highLimit <= 10^5
 */
public class CountBalls {

    public int countBalls(int lowLimit, int highLimit) {
        int max = 0;
        int[] marks = new int[46];
        for (int i = lowLimit; i <= highLimit; i++) {
            int index = getNum(i);
            marks[index]++;
            if (marks[index] > max) {
                max = marks[index];
            }
        }
        return max;
    }

    private int getNum(int num) {
        int total = 0;
        while (num > 0) {
            total += (num % 10);
            num /= 10;
        }
        return total;
    }
}
