package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/10/5
 * @author chenglong
 * description : 子域名访问计数
 *
 * 网站域名"discuss.leetcode.com"由多个子域名组成。顶级域名为"com"，二级域名为"leetcode.com"，最低一级为"discuss.leetcode.com"。
 * 当访问域名"discuss.leetcode.com"时，同时也会隐式访问其父域名"leetcode.com"以及"com"。
 * 计数配对域名是遵循"rep d1.d2.d3"或"rep d1.d2"格式的一个域名表示，其中rep表示访问域名的次数，d1.d2.d3为域名本身。
 * 例如，"9001 discuss.leetcode.com" 就是一个 计数配对域名，表示discuss.leetcode.com被访问了9001次。
 * 给你一个计数配对域名组成的数组cpdomains，解析得到输入中每个子域名对应的计数配对域名，并以数组形式返回。可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：cpdomains = ["9001 discuss.leetcode.com"]
 * 输出：["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
 * 解释：例子中仅包含一个网站域名："discuss.leetcode.com"。
 * 按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。
 *
 * 示例 2：
 * 输入：cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * 输出：["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * 解释：按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
 * 而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
 *
 * 提示：
 * 1 <= cpdomain.length <= 100
 * 1 <= cpdomain[i].length <= 100
 * cpdomain[i] 会遵循 "repi d1i.d2i.d3i" 或 "repi d1i.d2i" 格式
 * repi 是范围 [1, 104] 内的一个整数
 * d1i、d2i 和 d3i 由小写英文字母组成
 */
public class SubdomainVisits {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> marks = new HashMap<>();
        //1，解析并记录子域名出现的次数
        int length = cpdomains.length;
        for (int i = 0; i < length; i++) {
            String[] split = cpdomains[i].split(" ");
            int count = Integer.parseInt(split[0]);
            String a = split[1];
            String b = "";
            String c = "";
            int tem = 0;
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) == '.') {
                    tem++;
                    if (tem == 1) {
                        b = a.substring(j + 1);
                    } else if (tem == 2) {
                        c = a.substring(j + 1);
                    }
                }
            }
            //三个域名分别对应a,b,c
            if (a.length() > 0) {
                if (marks.containsKey(a)) {
                    marks.put(a, marks.get(a) + count);
                } else {
                    marks.put(a, count);
                }
            }
            if (b.length() > 0) {
                if (marks.containsKey(b)) {
                    marks.put(b, marks.get(b) + count);
                } else {
                    marks.put(b, count);
                }
            }
            if (c.length() > 0) {
                if (marks.containsKey(c)) {
                    marks.put(c, marks.get(c) + count);
                } else {
                    marks.put(c, count);
                }
            }
        }
        List<String> result = new ArrayList<>();
        //2，遍历统计并组装
        for (String key : marks.keySet()) {
            result.add(marks.get(key) + " " + key);
        }
        return result;
    }
}
