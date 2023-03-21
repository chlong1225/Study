package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2023/3/21
 * @author chenglong
 * description : 执行操作后字典序最小的字符串
 *
 * 给你一个字符串s以及两个整数a和b。其中，字符串s的长度为偶数，且仅由数字0到9组成。
 * 你可以在s上按任意顺序多次执行下面两个操作之一：
 * 累加：将a加到s中所有下标为奇数的元素上（下标从0开始）。数字一旦超过9就会变成0，如此循环往复。例如，s = "3456" 且 a = 5，则执行此操作后 s 变成 "3951"。
 * 轮转：将 s向右轮转b位。例如，s="3456"且b=1，则执行此操作后s变成"6345"。
 * 请你返回在s上执行上述操作任意次后可以得到的字典序最小的字符串。
 * 如果两个字符串长度相同，那么字符串a字典序比字符串b小可以这样定义：在a和b出现不同的第一个位置上，字符串a中的字符出现在字母表中的时间早于b中的对应字符。
 * 例如，"0158”字典序比"0190"小，因为不同的第一个位置是在第三个字符，显然'5'出现在'9'之前。
 *
 * 示例 1：
 * 输入：s = "5525", a = 9, b = 2
 * 输出："2050"
 * 解释：执行操作如下：
 * 初态："5525"
 * 轮转："2555"
 * 累加："2454"
 * 累加："2353"
 * 轮转："5323"
 * 累加："5222"
 * 累加："5121"
 * 轮转："2151"
 * 累加："2050"
 * 无法获得字典序小于 "2050" 的字符串。
 *
 * 示例 2：
 * 输入：s = "74", a = 5, b = 1
 * 输出："24"
 * 解释：执行操作如下：
 * 初态："74"
 * 轮转："47"
 * 累加："42"
 * 轮转："24"
 * 无法获得字典序小于 "24" 的字符串。
 *
 * 示例 3：
 * 输入：s = "0011", a = 4, b = 2
 * 输出："0011"
 * 解释：无法获得字典序小于 "0011" 的字符串。
 *
 * 示例 4：
 * 输入：s = "43987654", a = 7, b = 3
 * 输出："00553311"
 *
 * 提示：
 * 2 <= s.length <= 100
 * s.length是偶数
 * s 仅由数字0到9组成
 * 1 <= a <= 9
 * 1 <= b <= s.length-1
 */
public class FindLexSmallestString {

    public String findLexSmallestString(String s, int a, int b) {
        Set<String> marks = new HashSet<>();
        List<String> curs = new ArrayList<>();
        List<String> nexts = new ArrayList<>();
        marks.add(s);
        curs.add(s);
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                String cur = curs.get(i);
                String add = add(cur, a);
                if (marks.add(add)) {
                    nexts.add(add);
                }
                String rotation = rotation(cur, b);
                if (marks.add(rotation)) {
                    nexts.add(rotation);
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        List<String> dates = new ArrayList<>(marks);
        Collections.sort(dates);
        return dates.get(0);
    }

    private String rotation(String s, int b) {
        StringBuilder builder = new StringBuilder();
        builder.append(s.substring(s.length() - b));
        builder.append(s.substring(0, s.length() - b));
        return builder.toString();
    }

    private String add(String s, int a) {
        int length = s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i % 2 == 1) {
                int num = ((s.charAt(i) - '0') + a) % 10;
                builder.append(num);
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
