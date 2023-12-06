package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/6
 * @author chenglong
 * description : 数字的补数
 *
 * 对整数的二进制表示取反（0变1，1变0）后，再转换为十进制表示，可以得到这个整数的补数。
 * 例如，整数5的二进制表示是"101"，取反后得到"010"，再转回十进制表示得到补数2。
 * 给你一个整数num，输出它的补数。
 *
 * 示例 1：
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出2。
 *
 * 示例 2：
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 *
 * 提示：
 * 1 <= num < 2^31
 */
public class FindComplement {

    public int findComplement(int num) {
        int total = 0;
        int base = 1;
        while (num > 0) {
            int tem = num % 2;
            if (tem == 0) {
                total += base;
            }
            num /= 2;
            base *= 2;
        }
        return total;
    }
}
