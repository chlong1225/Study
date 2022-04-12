package com.demo.algorithm.leetcode.contest.week288;

/**
 * Created by chl on 2022/4/12.
 * description : 按奇偶性交换后的最大数字
 *
 * 给你一个正整数num。你可以交换num中奇偶性相同的任意两位数字（即都是奇数或者偶数）。
 * 返回交换任意次之后num的最大可能值。
 *
 * 示例 1：
 * 输入：num = 1234
 * 输出：3412
 * 解释：交换数字 3 和数字 1 ，结果得到 3214 。
 * 交换数字 2 和数字 4 ，结果得到 3412 。
 * 注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
 * 注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
 *
 * 示例 2：
 * 输入：num = 65875
 * 输出：87655
 * 解释：交换数字 8 和数字 6 ，结果得到 85675 。
 * 交换数字 5 和数字 7 ，结果得到 87655 。
 * 注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
 *
 * 提示：
 * 1 <= num <= 10^9
 */
public class LargestInteger {

    public int largestInteger(int num) {
        String s = "" + num;
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length - 1; i++) {
            int cur = chars[i] - '0';
            for (int j = i + 1; j < length; j++) {
                int tem = chars[j] - '0';
                if ((cur + tem) % 2 == 0) {
                    //cur与tem奇偶性相同
                    if (cur < tem) {
                        chars[i] = chars[j];
                        chars[j] = (char) ('0' + cur);
                        cur = tem;
                    }
                }
            }
        }
        return Integer.parseInt(new String(chars));
    }
}
