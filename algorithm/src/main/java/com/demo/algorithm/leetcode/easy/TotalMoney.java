package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/1/18
 * @author chenglong
 * description : 计算力扣银行的钱
 *
 * Hercy想要为购买第一辆车存钱。他每天都往力扣银行里存钱。
 * 最开始，他在周一的时候存入1块钱。从周二到周日，他每天都比前一天多存入1块钱。在接下来每一个周一，他都会比前一个周一多存入1块钱。
 * 给你n，请你返回在第 n天结束的时候他在力扣银行总共存了多少块钱。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：10
 * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
 *
 * 示例 2：
 * 输入：n = 10
 * 输出：37
 * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
 *
 * 示例 3：
 * 输入：n = 20
 * 输出：96
 * 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class TotalMoney {

    public int totalMoney(int n) {
        int[] monkeys = {0, 1, 3, 6, 10, 15, 21, 28};
        //存了几个星期
        int count = n / 7;
        //对应星期几。0～6
        n %= 7;
        //计算完整星期存的钱
        int sum = count * monkeys[7] + count * (count - 1) * 7 / 2;
        sum += (monkeys[n] + count * n);
        return sum;
    }
}
