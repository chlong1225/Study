package com.demo.algorithm.leetcode.contest.doubleweek90;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/10/31
 * @author chenglong
 * description : 距离字典两次编辑以内的单词
 *
 * 给你两个字符串数组queries和dictionary。数组中所有单词都只包含小写英文字母，且长度都相同。
 * 一次编辑中，你可以从queries中选择一个单词，将任意一个字母修改成任何其他字母。从queries中找到所有满足以下条件的字符串：不超过两次编辑内，字符串与dictionary中某个字符串相同。
 * 请你返回queries中的单词列表，这些单词距离dictionary中的单词编辑次数不超过两次。单词返回的顺序需要与queries中原本顺序相同。
 *
 * 示例 1：
 * 输入：queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
 * 输出：["word","note","wood"]
 * 解释：
 * - 将 "word" 中的 'r' 换成 'o' ，得到 dictionary 中的单词 "wood" 。
 * - 将 "note" 中的 'n' 换成 'j' 且将 't' 换成 'k' ，得到 "joke" 。
 * - "ants" 需要超过 2 次编辑才能得到 dictionary 中的单词。
 * - "wood" 不需要修改（0 次编辑），就得到 dictionary 中相同的单词。
 * 所以我们返回 ["word","note","wood"] 。
 *
 * 示例 2：
 * 输入：queries = ["yes"], dictionary = ["not"]
 * 输出：[]
 * 解释：
 * "yes" 需要超过 2 次编辑才能得到 "not" 。
 * 所以我们返回空数组。
 *
 * 提示：
 * 1 <= queries.length, dictionary.length <= 100
 * n == queries[i].length == dictionary[j].length
 * 1 <= n <= 100
 * 所有queries[i]和dictionary[j]都只包含小写英文字母。
 */
public class TwoEditWords {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            String cur = queries[i];
            for (int j = 0; j < dictionary.length; j++) {
                if (checkDiff(cur, dictionary[j])) {
                    result.add(cur);
                    break;
                }
            }
        }
        return result;
    }

    private boolean checkDiff(String cur, String compare) {
        int n = cur.length();
        if (n < 3) {
            return true;
        }
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (cur.charAt(i) != compare.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
