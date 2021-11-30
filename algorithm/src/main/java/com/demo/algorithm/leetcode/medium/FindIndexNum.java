package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/30/21
 * @author chenglong
 * description : 第N位数字
 *
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *  
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class FindIndexNum {

    public int findNthDigit(int n) {

        //定义记录：位数，当前位数最小值，当前位数最大值，当前位数数字的总数
        int num = 1;
        int min = 1;
        int max = 9;
        //使用long防止计算超出int的范围
        int total = 9;
        //int最大值：2147483647。总计10位，遍历统计到9位，最后一位单独处理。
        while (num < 10) {
            if (n <= total) {
                //找到的对应数字
                int data = min + (n - 1) / num;
                //对应数字从右到左的位数
                int index = num - (n - 1) % num;
                while (--index > 0) {
                    data /= 10;
                }
                return data % 10;
            } else {
                n -= total;
                num++;
                min *= 10;
                max = min * 10 - 1;
                if (Integer.MAX_VALUE / num < (max - min + 1)) {
                    total = Integer.MAX_VALUE;
                } else {
                    total = (max - min + 1) * num;
                }
            }
        }
        return 0;
    }
}
