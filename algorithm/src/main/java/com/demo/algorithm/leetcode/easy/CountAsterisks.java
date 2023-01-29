package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/1/29
 * @author chenglong
 * description : 统计星号
 *
 * 给你一个字符串s，每两个连续竖线'|'为一对。换言之，第一个和第二个'|'为一对，第三个和第四个'|'为一对，以此类推。
 * 请你返回不在竖线对之间，s中'*'的数目。
 * 注意，每个竖线'|'都会恰好属于一个对。
 *
 * 示例 1：
 * 输入：s = "l|*e*et|c**o|*de|"
 * 输出：2
 * 解释：不在竖线对之间的字符加粗加斜体后，得到字符串："l|*e*et|c**o|*de|" 。
 * 第一和第二条竖线 '|' 之间的字符不计入答案。
 * 同时，第三条和第四条竖线 '|' 之间的字符也不计入答案。
 * 不在竖线对之间总共有2个星号，所以我们返回2。
 *
 * 示例 2：
 * 输入：s = "iamprogrammer"
 * 输出：0
 * 解释：在这个例子中，s 中没有星号。所以返回 0 。
 *
 * 示例 3：
 * 输入：s = "yo|uar|e**|b|e***au|tifu|l"
 * 输出：5
 * 解释：需要考虑的字符加粗加斜体后："yo|uar|e**|b|e***au|tifu|l" 。不在竖线对之间总共有 5 个星号。所以我们返回 5 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s只包含小写英文字母，竖线'|'和星号'*'。
 * s包含偶数个竖线'|' 。
 */
public class CountAsterisks {

    public int countAsterisks(String s) {
        int count = 0;
        //竖线的数量
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                num++;
            } else if (s.charAt(i) == '*') {
                if (num % 2 == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
