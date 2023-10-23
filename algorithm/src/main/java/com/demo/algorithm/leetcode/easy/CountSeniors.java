package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/10/23
 * @author chenglong
 * description : 老人的数目
 *
 * 给你一个下标从0开始的字符串details。details中每个元素都是一位乘客的信息，信息用长度为15的字符串表示，表示方式如下：
 * 前十个字符是乘客的手机号码。
 * 接下来的一个字符是乘客的性别。
 * 接下来两个字符是乘客的年龄。
 * 最后两个字符是乘客的座位号。
 * 请你返回乘客中年龄 严格大于 60 岁 的人数。
 *
 * 示例 1：
 * 输入：details = ["7868190130M7522","5303914400F9211","9273338290F4010"]
 * 输出：2
 * 解释：下标为 0 ，1 和 2 的乘客年龄分别为 75 ，92 和 40 。所以有 2 人年龄大于 60 岁。
 *
 * 示例 2：
 * 输入：details = ["1313579440F2036","2921522980M5644"]
 * 输出：0
 * 解释：没有乘客的年龄大于 60 岁。
 *
 * 提示：
 * 1 <= details.length <= 100
 * details[i].length == 15
 * details[i] 中的数字只包含 '0' 到 '9' 。
 * details[i][10] 是 'M' ，'F' 或者 'O' 之一。
 * 所有乘客的手机号码和座位号互不相同。
 */
public class CountSeniors {

    public int countSeniors(String[] details) {
        int count = 0;
        for (int i = 0; i < details.length; i++) {
            int age = Integer.parseInt(details[i].substring(11, 13));
            if (age > 60) {
                count++;
            }
        }
        return count;
    }
}
