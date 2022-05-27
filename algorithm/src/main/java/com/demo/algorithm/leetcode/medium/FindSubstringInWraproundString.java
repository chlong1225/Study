package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/5/25
 * @author chenglong
 * description : 环绕字符串中唯一的子字符串
 *
 * 把字符串s看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以s看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 现在给定另一个字符串p。返回s中唯一的p的非空子串的数量。
 *
 * 示例1:
 * 输入: p = "a"
 * 输出: 1
 * 解释: 字符串 s 中只有一个"a"子字符。
 *
 * 示例 2:
 * 输入: p = "cac"
 * 输出: 2
 * 解释: 字符串 s 中的字符串“cac”只有两个子串“a”、“c”。.
 *
 * 示例 3:
 * 输入: p = "zab"
 * 输出: 6
 * 解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。
 *
 * 提示:
 * 1 <= p.length <= 10^5
 * p由小写英文字母构成
 */
public class FindSubstringInWraproundString {

    public int findSubstringInWraproundString(String p) {
        /**
         * 分析：统计以指定字母结束的长度
         * 直接定义统计长度为128，避免计算-'a'，空间换时间
         */
        int[] marks = new int[128];
        int length = p.length();
        if (length == 1) {
            return 1;
        }
        char pre = p.charAt(0);
        int count = 1;
        marks[pre] = 1;
        for (int i = 1; i < length; i++) {
            char cur = p.charAt(i);
            if (pre == 'z' && cur == 'a' || (pre < 'z' && cur - pre == 1)) {
                //当前字符能够连续
                count++;
            } else {
                //字符非连续，需要重新统计长度
                count = 1;
            }
            //对比之前的统计，更新长度
            if (marks[cur] < count) {
                marks[cur] = count;
            }
            pre = cur;
        }
        if (marks[pre] < count) {
            marks[pre] = count;
        }
        //计算不同区间的组合
        int sum = 0;
        for (int i = 'a'; i <= 'z'; i++) {
            sum += marks[i];
        }
        return sum;
    }
}
