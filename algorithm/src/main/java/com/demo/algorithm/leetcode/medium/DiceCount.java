package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/12/1.
 * description : 掷骰子的N种方法
 *
 * 这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。
 * 我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
 * 如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回。
 *
 * 示例 1：
 * 输入：d = 1, f = 6, target = 3
 * 输出：1
 *
 * 示例 2：
 * 输入：d = 2, f = 6, target = 7
 * 输出：6
 *
 * 示例 3：
 * 输入：d = 2, f = 5, target = 10
 * 输出：1
 *
 * 示例 4：
 * 输入：d = 1, f = 2, target = 3
 * 输出：0
 *
 * 示例 5：
 * 输入：d = 30, f = 30, target = 500
 * 输出：222616187
 *  
 * 提示：
 * 1 <= d, f <= 30
 * 1 <= target <= 1000
 */
public class DiceCount {

    private static final int MOD = 1000000007;

    public int numRollsToTarget(int d, int f, int target) {
        /**
         * 转换为动态规划背包问题: 最大重量:target,物品d个,每个物品的价值1~f。装满背包的方式有多少种
         * 状态数组:marks[i][j]:
         * i:添加的数量;
         * j:总的点数;
         * marks[i][j]:添加i次,产生j重量的方式
         * marks[i][j] = marks[i-1][j-1]+marks[i-1][j-2]+...+marks[i-1][j-f]
         * j-f>=1
         */
        int[] marks = new int[target + 1];
        //初始化状态数组，掷一次骰子总和 1~f均有一次组合（f不能超过target）
        for (int i = 1; i <= f; i++) {
            if (i <= target) {
                marks[i] = 1;
            }
        }
        for (int i = 2; i <= d; i++) {
            for (int j = target; j >= 1; j--) {
                int sum = 0;
                for (int k = 1; k <= f; k++) {
                    int index = j - k;
                    if (index > 0) {
                        sum += marks[index];
                        if (sum >= MOD) {
                            sum %= MOD;
                        }
                    } else {
                        break;
                    }
                }
                marks[j] = sum;
            }
        }
        return marks[target];
    }
}
