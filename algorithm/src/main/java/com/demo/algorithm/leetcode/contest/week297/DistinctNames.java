package com.demo.algorithm.leetcode.contest.week297;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2022/6/12.
 * description : 公司命名
 *
 * 给你一个字符串数组ideas表示在公司命名过程中使用的名字列表。公司命名流程如下：
 * 从 ideas 中选择2个不同名字，称为ideaA和ideaB 。
 * 交换ideaA和ideaB的首字母。
 * 如果得到的两个新名字都不在ideas中，那么ideaA ideaB（串联 ideaA 和 ideaB ，中间用一个空格分隔）是一个有效的公司名字。
 * 否则，不是一个有效的名字。
 * 返回不同且有效的公司名字的数目。
 *
 * 示例 1：
 * 输入：ideas = ["coffee","donuts","time","toffee"]
 * 输出：6
 * 解释：下面列出一些有效的选择方案：
 * - ("coffee", "donuts")：对应的公司名字是 "doffee conuts" 。
 * - ("donuts", "coffee")：对应的公司名字是 "conuts doffee" 。
 * - ("donuts", "time")：对应的公司名字是 "tonuts dime" 。
 * - ("donuts", "toffee")：对应的公司名字是 "tonuts doffee" 。
 * - ("time", "donuts")：对应的公司名字是 "dime tonuts" 。
 * - ("toffee", "donuts")：对应的公司名字是 "doffee tonuts" 。
 * 因此，总共有 6 个不同的公司名字。
 *
 * 下面列出一些无效的选择方案：
 * - ("coffee", "time")：在原数组中存在交换后形成的名字 "toffee" 。
 * - ("time", "toffee")：在原数组中存在交换后形成的两个名字。
 * - ("coffee", "toffee")：在原数组中存在交换后形成的两个名字。
 * 示例 2：
 *
 * 输入：ideas = ["lack","back"]
 * 输出：0
 * 解释：不存在有效的选择方案。因此，返回 0 。
 *
 * 提示：
 * 2 <= ideas.length <= 5 * 10^4
 * 1 <= ideas[i].length <= 10
 * ideas[i] 由小写英文字母组成
 * ideas中的所有字符串互不相同
 */
public class DistinctNames {

    public long distinctNames(String[] ideas) {
        Map<String, boolean[]> marks = new HashMap<>();
        //1，统计首字母出现的情况
        int length = ideas.length;
        for (int i = 0; i < length; i++) {
            String key = ideas[i].substring(1);
            int index = ideas[i].charAt(0) - 'a';
            if (!marks.containsKey(key)) {
                marks.put(key, new boolean[26]);
            }
            marks.get(key)[index] = true;
        }
        long sum = 0;
        //统计有i无j的数量
        int[][] counts = new int[26][26];
        //2，遍历统计
        for (boolean[] firsts : marks.values()) {
            for (int i = 0; i < 26; i++) {
                if (firsts[i]) {
                    for (int j = 0; j < 26; j++) {
                        if (!firsts[j]) {
                            counts[i][j]++;
                        }
                    }
                } else {
                   //此时对应的key首字母没有i
                    for (int j = 0; j < 26; j++) {
                        if (firsts[j]) {
                            sum += counts[i][j];
                        }
                    }
                }
            }
        }
        return sum * 2;
    }
}
