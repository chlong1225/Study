package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/5/17
 * @author chenglong
 * description : 验证外星语词典
 *
 * 某种外星语也使用英文小写字母，但可能顺序order不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词words，以及其字母表的顺序order，只有当给定的单词在这种外星语中按字典序排列时，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 *
 * 示例 2：
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 *
 * 示例 3：
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在words[i]和order中的所有字符都是英文小写字母。
 */
public class AlienSorted {

    public boolean isAlienSorted(String[] words, String order) {
        //1，记录小写字母与order的对照表，用于后序比较顺序
        int[] indexs = new int[26];
        for (int i = 0; i < order.length(); i++) {
            indexs[order.charAt(i) - 'a'] = i;
        }
        int length = words.length;
        if (length == 1) {
            return true;
        }
        //2，依次比较前后两个单词
        for (int i = 1; i < length; i++) {
            String pre = words[i - 1];
            String cur = words[i];
            if (!compareStr(pre, cur, indexs)) {
                return false;
            }
        }
        return true;
    }

    //比较pre的字典顺序是否小于cur
    private boolean compareStr(String pre, String cur, int[] indexs) {
        int length1 = pre.length();
        int length2 = cur.length();
        int n = Math.min(length1, length2);
        for (int i = 0; i < n; i++) {
            int preIndex = indexs[pre.charAt(i) - 'a'];
            int curIndex = indexs[cur.charAt(i) - 'a'];
            if (preIndex < curIndex) {
                return true;
            }
            if (preIndex > curIndex) {
                return false;
            }
        }
        //此时从0～n之间pre与cur相同
        return length1 <= length2;
    }
}
