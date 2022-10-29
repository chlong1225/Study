package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/10/18
 * @author chenglong
 * description : 最大为N的数字组合
 *
 * 给定一个按非递减顺序排列的数字数组digits。你可以用任意次数digits[i]来写的数字。例如，如果digits=['1','3','5']，我们可以写数字，如'13','551', 和'1351315'。
 * 返回可以生成的小于或等于给定整数n的正整数的个数。
 *
 * 示例 1：
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 *
 * 示例 2：
 * 输入：digits = ["1","4","9"], n = 1000000000
 * 输出：29523
 * 解释：
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 *
 * 示例 3:
 * 输入：digits = ["7"], n = 8
 * 输出：1
 *
 * 提示：
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i]是从'1'到'9'的数
 * digits中的所有值都不同
 * digits按非递减顺序排列
 * 1 <= n <= 10^9
 */
public class AtMostNGivenDigitSet {

    public int atMostNGivenDigitSet(String[] digits, int n) {
        int length = digits.length;
        //1，计算n的位数
        String target = "" + n;
        int count = target.length();
        //2，依次统计1～count-1位的数量
        int total = 0;
        int m = length;
        for (int i = 1; i < count; i++) {
            total += m;
            m *= length;
        }
        //3，统计数量
        int[] marks = new int[10];
        for (int i = 0; i < length; i++) {
            marks[Integer.parseInt(digits[i])] = 1;
        }
        for (int i = 1; i < 10; i++) {
            marks[i] += marks[i - 1];
        }
        //4，处理与n位数相同的场景
        for (int i = 0; i < count; i++) {
            int index = target.charAt(i) - '0';
            if (index == 0) {
                break;
            }
            int pre = marks[index - 1];
            int cur = marks[index] - marks[index - 1];
            if (pre == 0 && cur == 0) {
                break;
            }
            if (pre > 0) {
                //获取当前场景的组合
                int base = 1;
                for (int j = i + 1; j < count; j++) {
                    base *= length;
                }
                total += (pre * base);
            }
            if (cur == 0) {
                break;
            } else {
                if (i == count - 1) {
                    total++;
                }
            }
        }
        return total;
    }
}
