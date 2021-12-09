package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2021/12/9.
 * description : 重复的DNA序列
 *
 * 所有 DNA都由一系列缩写为'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 *
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *  
 * 提示：
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 */
public class RepeatDna {

    //暴力解法
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        int length = s.length();
        int count = 10;
        Map<String, Integer> marks = new HashMap<>();
        for (int i = 0; i <= length - count; i++) {
            String tem = s.substring(i, i + count);
            if (marks.get(tem) == null) {
                marks.put(tem, 1);
            } else {
                if (marks.get(tem) == 1) {
                    result.add(tem);
                }
                marks.put(tem, marks.get(tem) + 1);
            }
        }
        return result;
    }
}
