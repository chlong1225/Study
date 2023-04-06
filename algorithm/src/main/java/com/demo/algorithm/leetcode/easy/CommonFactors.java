package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/4/6
 * @author chenglong
 * description : 公因子的数目
 *
 * 给你两个正整数a和b，返回a和b的公因子的数目。
 * 如果x可以同时整除a和b，则认为x是a和b的一个公因子 。
 *
 * 示例 1：
 * 输入：a = 12, b = 6
 * 输出：4
 * 解释：12 和 6 的公因子是 1、2、3、6 。
 *
 * 示例 2：
 * 输入：a = 25, b = 30
 * 输出：2
 * 解释：25 和 30 的公因子是 1、5 。
 *
 * 提示：
 * 1 <= a, b <= 1000
 */
public class CommonFactors {

    public int commonFactors(int a, int b) {
        int count = 1;
        int min = Math.min(a, b);
        for (int i = 2; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                count++;
            }
        }
        return count;
    }
}
