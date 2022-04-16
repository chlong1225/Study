package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/16.
 * description : 剑指Offer14-I. 剪绳子
 *
 * 给你一根长度为n的绳子，请把绳子剪成整数长度的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
 *
 * 提示：
 * 2 <= n <= 58
 */
public class CuttingRope {

    public int cuttingRope(int n) {
        if (n == 2) {
            return 1;
        }
        int max = 1;
        //遍历分成的段数
        for (int i = 2; i <= n - 1; i++) {
            //记录每段长度
            int[] count = new int[i];
            int total = n;
            int add = total / i;
            if (add > 0) {
                for (int j = 0; j < i; j++) {
                    count[j] += add;
                }
                total %= i;
            }
            if (total > 0) {
                for (int j = 0; j < total; j++) {
                    count[j]++;
                }
            }
            //计算乘积
            int sum = count[0];
            for (int j = 1; j < i; j++) {
                sum *= count[j];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
