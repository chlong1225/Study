package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/16.
 * description : 剑指Offer14-II. 剪绳子II
 *
 * 给你一根长度为n的绳子，请把绳子剪成整数长度的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
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
 * 2 <= n <= 1000
 */
public class CuttingRope2 {

    private static final int MOD = 1000000007;

    public int cuttingRope(int n) {
        /**
         * 经分析：使用模拟比较时，由于存在数据比较，无法在计算过程中取模，
         * 造成使用long类型数据越界，最终结果异常。
         * 这里需要分析出最大值的场景，然后在计算过程中取模，防止数据越界
         * 1，长度相同时，均分时产生的乘积最大。比如：6=1+5=2+4=3+3。但3*3>2*4>1*5
         * 2，尽可能拆分长度为3的子集。比如：6*6<3*3*3*3; 6+6=3+3+3+3
         * 3，拆分多余的，尽可能分解为2。比如：4=3+1=2+2；但3*1<2*2
         */
        //1，处理特殊场景。n=2或3
        if (n < 4) {
            return n - 1;
        }
        //2，拆分绳索
        //记录拆分为3的个数
        int a = 0;
        //记录拆分为2的个数
        int b = 0;
        if (n % 3 == 1) {
            b = 2;
        } else if (n % 3 == 2) {
            b = 1;
        }
        a = (n - 2 * b) / 3;
        //3，计算a个3的乘积
        long sum = 1;
        long modulus = 3;
        while (a > 0) {
            if (a % 2 == 1) {
                sum *= modulus;
                sum %= MOD;
            }
            modulus *= modulus;
            modulus %= MOD;
            a /= 2;
        }
        if (b == 0) {
            return (int) (sum);
        }
        if (b == 1) {
            return (int) (2 * sum % MOD);
        }
        return (int) (4*sum%MOD);
    }
}
