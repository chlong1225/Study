package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create on 2022/5/7
 * @author chenglong
 * description : 最小基因变化
 *
 * 基因序列可以表示为一条由8个字符组成的字符串，其中每个字符都是'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列start变为end所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * 给你两个基因序列start和end，以及一个基因库bank，请你找出并返回能够使start变化为end所需的最少变化次数。如果无法完成此基因变化返回 -1。
 * 注意：起始基因序列start 默认是有效的，但是它并不一定会出现在基因库中。
 *
 * 示例 1：
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 *
 * 示例 2：
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 *
 * 示例 3：
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 *
 * 提示：
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 */
public class MinMutation {

    private static final int MOD = 4;
    private final int[] indexs = new int[128];
    private char[] replaces = {'A', 'C', 'G', 'T'};

    public int minMutation(String start, String end, String[] bank) {
        int length = bank.length;
        if (length == 0) {
            return -1;
        }
        for (int i = 0; i < replaces.length; i++) {
            indexs[replaces[i]] = i;
        }
        //1，将字符串转换为数字，便于比较
        Set<Integer> dates = new HashSet<>();
        for (int i = 0; i < length; i++) {
            dates.add(strToInt(bank[i]));
        }
        int endNum = strToInt(end);
        if (!dates.contains(endNum)) {
            return -1;
        }
        if (start.equals(end)) {
            return 0;
        }
        //记录基因与次数的对照关系，防止重复转换
        Map<Integer, Integer> marks = new HashMap<>();
        marks.put(strToInt(start), 0);
        List<String> cur = new ArrayList<>();
        cur.add(start);
        List<String> next = new ArrayList<>();
        int step = 0;
        //2，使用广度搜索遍历
        while (cur.size() > 0) {
            step++;
            for (int i = 0; i < cur.size(); i++) {
                //当前需要被转换的基因
                String str = cur.get(i);
                for (int j = 0; j < str.length(); j++) {
                    //转换前的字符
                    char pre = str.charAt(j);
                    for (int l = 0; l < 4; l++) {
                        if (replaces[l] == pre) {
                            continue;
                        }
                        int newNum = strToInt(str, j, replaces[l]);
                        if (newNum == endNum) {
                            return step;
                        }
                        if (!dates.contains(newNum)) {
                            //转换后为无效基因
                            continue;
                        }
                        if (marks.containsKey(newNum)) {
                            //之前已经转换过
                            continue;
                        }
                        marks.put(newNum, step);
                        char[] chars = str.toCharArray();
                        chars[j] = replaces[l];
                        next.add(new String(chars));
                    }
                }
            }
            cur.clear();
            cur.addAll(next);
            next.clear();
        }
        return -1;
    }

    private int strToInt(String str,int index,char c) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char tem = str.charAt(i);
            if (i == index) {
                tem = c;
            }
            num = num * MOD + indexs[tem];
        }
        return num;
    }
    
    private int strToInt(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * MOD + indexs[str.charAt(i)];
        }
        return num;
    }
}
