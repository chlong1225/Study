package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/5/9
 * @author chenglong
 * description : 有效时间的数目
 *
 * 给你一个长度为5的字符串time，表示一个电子时钟当前的时间，格式为"hh:mm"。最早可能的时间是"00:00"，最晚可能的时间是"23:59"。
 * 在字符串time中，被字符?替换掉的数位是未知的，被替换的数字可能是0到9中的任何一个。
 * 请你返回一个整数answer，将每一个?都用0到9中一个数字替换后，可以得到的有效时间的数目。
 *
 * 示例 1：
 * 输入：time = "?5:00"
 * 输出：2
 * 解释：我们可以将 ? 替换成 0 或 1 ，得到 "05:00" 或者 "15:00" 。注意我们不能替换成 2 ，因为时间 "25:00" 是无效时间。所以我们有两个选择。
 *
 * 示例 2：
 * 输入：time = "0?:0?"
 * 输出：100
 * 解释：两个 ? 都可以被 0 到 9 之间的任意数字替换，所以我们总共有 100 种选择。
 *
 * 示例 3：
 * 输入：time = "??:??"
 * 输出：1440
 * 解释：小时总共有 24 种选择，分钟总共有 60 种选择。所以总共有 24 * 60 = 1440 种选择。
 *
 * 提示：
 * time是一个长度为5的有效字符串，格式为"hh:mm"。
 * "00" <= hh <= "23"
 * "00" <= mm <= "59"
 * 字符串中有的数位是'?'，需要用0到9之间的数字替换。
 */
public class CountTime {

    public int countTime(String time) {
        int h = 1;
        if (time.charAt(0) == '?' && time.charAt(1) == '?') {
            h = 24;
        } else if (time.charAt(0) == '?') {
            if (time.charAt(1) >= '4') {
                h = 2;
            } else {
                h = 3;
            }
        } else if (time.charAt(1) == '?') {
            if (time.charAt(0) < '2') {
                h = 10;
            } else {
                h = 4;
            }
        }
        int m = 1;
        if (time.charAt(3) == '?' && time.charAt(4) == '?') {
            m = 60;
        } else if (time.charAt(3) == '?') {
            m = 6;
        } else if (time.charAt(4) == '?') {
            m = 10;
        }
        return h * m;
    }
}
