package com.demo.algorithm.leetcode.contest.week291;

/**
 * create on 2022/5/6
 * @author chenglong
 * description : 移除指定数字得到的最大结果
 *
 * 给你一个表示某个正整数的字符串number和一个字符digit。
 * 从number中恰好移除一个等于digit的字符后，找出并返回按十进制表示最大的结果字符串。生成的测试用例满足digit在 number中出现至少一次。
 *
 * 示例 1：
 * 输入：number = "123", digit = "3"
 * 输出："12"
 * 解释："123" 中只有一个 '3' ，在移除 '3' 之后，结果为 "12" 。
 *
 * 示例 2：
 * 输入：number = "1231", digit = "1"
 * 输出："231"
 * 解释：可以移除第一个 '1' 得到 "231" 或者移除第二个 '1' 得到 "123" 。
 * 由于 231 > 123 ，返回 "231" 。
 *
 * 示例 3：
 * 输入：number = "551", digit = "5"
 * 输出："51"
 * 解释：可以从 "551" 中移除第一个或者第二个 '5' 。
 * 两种方案的结果都是 "51" 。
 *
 * 提示：
 * 2 <= number.length <= 100
 * number 由数字 '1' 到 '9' 组成
 * digit 是 '1' 到 '9' 中的一个数字
 * digit 在 number 中出现至少一次
 */
public class RemoveDigit {

    public String removeDigit(String number, char digit) {
        int length = number.length();
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (number.charAt(i) == digit) {
                index = i;
                if (i == length - 1 || number.charAt(i + 1) > number.charAt(i)) {
                    break;
                }
            }
        }
        if (index == 0) {
            return number.substring(1);
        }
        if (index == length - 1) {
            return number.substring(0, length - 1);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(number.substring(0, index));
        builder.append(number.substring(index + 1, length));
        return builder.toString();
    }
}
