package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/10/26
 * @author chenglong
 * description : 统计能整除数字的位数
 *
 * 给你一个整数num，返回num中能整除num的数位的数目。
 * 如果满足nums % val == 0 ，则认为整数val可以整除nums。
 *
 * 示例 1：
 * 输入：num = 7
 * 输出：1
 * 解释：7 被自己整除，因此答案是 1 。
 *
 * 示例 2：
 * 输入：num = 121
 * 输出：2
 * 解释：121 可以被 1 整除，但无法被 2 整除。由于 1 出现两次，所以返回 2 。
 *
 * 示例 3：
 * 输入：num = 1248
 * 输出：4
 * 解释：1248 可以被它每一位上的数字整除，因此答案是 4 。
 *
 * 提示：
 * 1 <= num <= 10^9
 * num 的数位中不含 0
 */
public class CountDigits {

    public int countDigits(int num) {
        int tem = num;
        int count = 0;
        while (tem > 0) {
            int cur = tem % 10;
            if (cur != 0) {
                if (cur == 1) {
                    count++;
                } else {
                    if (num % cur == 0) {
                        count++;
                    }
                }
            }
            tem /= 10;
        }
        return count;
    }
}
