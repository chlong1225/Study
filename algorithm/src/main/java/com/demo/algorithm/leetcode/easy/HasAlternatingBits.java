package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/3/28
 * @author chenglong
 * description : 交替位二进制数
 *
 * 给定一个正整数，检查它的二进制表示是否总是0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 *
 * 示例 3：
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 *
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class HasAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        if (n <= 2) {
            return true;
        }
        int start = n & 1;
        n = n >> 1;
        while (n > 0) {
            int tem = n & 1;
            if (start == tem) {
                return false;
            } else {
                start = tem;
            }
            n = n >> 1;
        }
        return true;
    }
}
