package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/3/20
 * @author chenglong
 * description : 至少有1位重复的数字
 *
 * 给定正整数n，返回在[1, n]范围内具有至少1位重复数字的正整数的个数。
 *
 * 示例 1：
 * 输入：n = 20
 * 输出：1
 * 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
 *
 * 示例 2：
 * 输入：n = 100
 * 输出：10
 * 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
 *
 * 示例 3：
 * 输入：n = 1000
 * 输出：262
 *
 * 提示：
 * 1 <= n <= 10^9
 */
public class NumDupDigitsAtMostN {

    private static int[] marks = new int[10];

    static {
        marks[1] = 9;
        for (int i = 2; i < 10; i++) {
            marks[i] = marks[i - 1] * (11 - i);
        }
        for (int i = 2; i < 10; i++) {
            marks[i] += marks[i - 1];
        }
    }

    public int numDupDigitsAtMostN(int n) {
        /**
         * 至少一位重复对应都不重复，故只需要获取都不重复的数量
         */
        if (n < 11) {
            return 0;
        }
        return n - getDifferentNum(n);
    }

    //查找数字重复的数量
    private int getDifferentNum(int n) {
        //1，按位拆分数字
        int length = 0;
        int tem = n;
        while (tem > 0) {
            length++;
            tem /= 10;
        }
        tem = n;
        int[] dates = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            dates[i] = tem % 10;
            tem /= 10;
        }
        int sum = 0;
        //记录已经使用过的数字，避免重复
        boolean[] hasUser = new boolean[10];
        //2，计算首位最多不同。统计1～1*10^(length-1)
        sum += marks[length - 1];
        //3，统计从1*10^(length-1)～cur*10^(length-1)
        int cur = dates[0];
        int count = 0;
        if (cur > 1) {
            count = cur - 1;
            for (int i = 1; i < length; i++) {
                count *= (10 - i);
            }
            sum += count;
        }
        hasUser[cur] = true;
        for (int i = 1; i < length; i++) {
            //当前位的数字
            cur = dates[i];
            if (i == length - 1) {
                for (int j = 0; j <= cur; j++) {
                    if (!hasUser[j]) {
                        sum++;
                    }
                }
                break;
            }
            count = 0;
            for (int j = 0; j < cur; j++) {
                if (!hasUser[j]) {
                    count++;
                }
            }
            int remove = 0;
            for (int j = 0; j < 10; j++) {
                if (hasUser[j]) {
                    remove++;
                }
            }
            for (int j = i + 1; j < length; j++) {
                //j = i+1时，取值：9-remove
                count *= (9 - remove - (j - i - 1));
            }
            sum += count;
            if (hasUser[cur]) {
                break;
            }
            hasUser[cur] = true;
        }
        return sum;
    }
}
