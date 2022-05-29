package com.demo.algorithm.leetcode.contest.doubleweek79;

/**
 * Created by chl on 2022/5/28.
 * description : 判断一个数的数字计数是否等于数位的值
 *
 *给你一个下标从0开始长度为n的字符串num，它只包含数字。
 * 如果对于每个0 <=i<n的下标i，都满足数位i在num中出现了num[i]次，那么请你返回true，否则返回false。
 *
 * 示例 1：
 * 输入：num = "1210"
 * 输出：true
 * 解释：
 * num[0] = '1' 。数字 0 在 num 中出现了一次。
 * num[1] = '2' 。数字 1 在 num 中出现了两次。
 * num[2] = '1' 。数字 2 在 num 中出现了一次。
 * num[3] = '0' 。数字 3 在 num 中出现了零次。
 * "1210" 满足题目要求条件，所以返回 true 。
 *
 * 示例 2：
 * 输入：num = "030"
 * 输出：false
 * 解释：
 * num[0] = '0' 。数字 0 应该出现 0 次，但是在 num 中出现了一次。
 * num[1] = '3' 。数字 1 应该出现 3 次，但是在 num 中出现了零次。
 * num[2] = '0' 。数字 2 在 num 中出现了 0 次。
 * 下标 0 和 1 都违反了题目要求，所以返回 false 。
 *
 * 提示：
 * n == num.length
 * 1 <= n <= 10
 * num只包含数字。
 */
public class DigitCount {

    public boolean digitCount(String num) {
        int n = num.length();
        int[] counts = new int[n];
        //1，统计0~n-1的每个数字出现的次数
        for (int i = 0; i < n; i++) {
            int index = num.charAt(i) - '0';
            if (index < n) {
                counts[index]++;
            }
        }
        //2，比较出现的次数
        for (int i = 0; i < n; i++) {
            int count = num.charAt(i) - '0';
            if (count != counts[i]) {
                return false;
            }
        }
        return true;
    }
}
