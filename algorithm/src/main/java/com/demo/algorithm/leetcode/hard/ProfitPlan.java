package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/11/30.
 * description : 盈利计划
 *
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 *
 * 示例 1：
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 *
 * 示例 2：
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 *  
 * 提示：
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 */
public class ProfitPlan {

    private static final int MOD = 1000000007;

    //深度dfs搜索时间复杂度2^n,由于选择工作之间相互关联,考虑使用动态规划
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        //工种数量
        int length = group.length;
        /**
         * 类别0,1背包问题进行分析:
         * 背包最大容积:minProfit, 物品总数:group;物品对应价值:profit。这里多了限制条件：总人数n
         * 定义状态数组：marks[i][j][k]:
         * i:对应工种序号
         * j:对应人数
         * k:对应利润。这里限定minProfit
         * marks[i][j][k]对应统计组合的次数
         * 状态方程: marks[i][j][k] = marks[i-1][j][k]+marks[i-1][j-group[i-1]][k-profit[i-1]]
         */
        //在人数没有限制下的最大利润
        int[][][] marks = new int[length + 1][n + 1][minProfit + 1];
        //0利润的方式:所有任务都不做,需要的人数为0,有一种方案
        marks[0][0][0] = 1;
        for (int i = 1; i <= length; i++) {
            //当前任务的利润与需要的人数
            int curProfit = profit[i - 1];
            int people = group[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < people) {
                        //消耗的人数少于people,当前任务不做
                        marks[i][j][k] = marks[i - 1][j][k];
                    } else {
                        marks[i][j][k] = marks[i - 1][j][k] + marks[i - 1][j - people][Math.max(0, k - curProfit)];
                        if (marks[i][j][k] >= MOD) {
                            marks[i][j][k] %= MOD;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += marks[length][i][minProfit];
            if (sum >= MOD) {
                sum %= MOD;
            }
        }
        return sum;
    }

}
