package com.demo.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chl on 2022/6/4.
 * description : 独特的电子邮件地址
 *
 * 每个有效电子邮件地址都由一个本地名和一个域名组成，以'@'符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个'.' 或 '+' 。
 * 例如，在alice@leetcode.com中，alice是本地名 ，而leetcode.com是域名 。
 * 如果在电子邮件地址的本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则不适用于域名 。
 * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com”会转发到同一电子邮件地址。
 * 如果在本地名中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则不适用于域名 。
 * 例如 m.y+name@email.com 将转发到 my@email.com。
 * 可以同时使用这两个规则。
 * 给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。
 *
 * 示例 1：
 * 输入：emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 输出：2
 * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
 *
 * 示例 2：
 * 输入：emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
 * 输出：3
 *
 * 提示：
 * 1 <= emails.length <= 100
 * 1 <= emails[i].length<= 100
 * emails[i] 由小写英文字母、'+'、'.' 和 '@' 组成
 * 每个 emails[i] 都包含有且仅有一个 '@' 字符
 * 所有本地名和域名都不为空
 * 本地名不会以 '+' 字符作为开头
 */
public class NumUniqueEmails {

    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> marks = new HashSet<>();
        int length = emails.length;
        for (int i = 0; i < length; i++) {
            String email = checkEmail(emails[i]);
            marks.add(email);
        }
        return marks.size();
    }

    private String checkEmail(String email) {
        StringBuilder builder = new StringBuilder();
        int index = -1;
        int length = email.length();
        boolean isEnd = false;
        for (int i = 0; i < length; i++) {
            char c = email.charAt(i);
            if (c == '@') {
                index = i;
                break;
            } else {
                //处理本地名
                if (!isEnd) {
                    if (c == '+') {
                        isEnd = true;
                    } else {
                        if (c != '.') {
                            builder.append(c);
                        }
                    }
                }
            }
        }
        builder.append(email.substring(index, length));
        return builder.toString();
    }
}
