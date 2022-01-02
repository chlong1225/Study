package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2021/12/23.
 * description : 最长重复子串
 *
 * 给你一个字符串s，考虑其所有重复子串 ：即，s的连续子串，在s中出现2次或更多次。这些出现之间可能存在重叠。
 * 返回任意一个可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 *
 * 示例 1：
 * 输入：s = "banana"
 * 输出："ana"
 *
 * 示例 2：
 * 输入：s = "abcd"
 * 输出：""
 *  
 * 提示：
 * 2 <= s.length <= 3 * 104
 * s 由小写英文字母组成
 */
public class MaxRepeatStr {

    public String longestDupSubstring(String s) {
        int lenght = s.length();
        for (int i = lenght - 1; i >= 1; i--) {
            //i:子串的长度
            String str = findStr(i, s);
            if (!str.isEmpty()) {
                return str;
            }
        }
        return "";
    }

    //暴力枚举比较,会超时.
    private String findStr(int count, String s) {
        int length = s.length();
        int maxIndex = length - count;
        for (int i = 0; i <= maxIndex; i++) {
            String s1 = s.substring(i, i + count);
            int start = i + 1;
            while (start <= maxIndex) {
                String tem = s.substring(start, start + count);
                if (s1.equals(tem)) {
                    return s1;
                }
                start++;
            }
        }
        return "";
    }

    //使用二分法+自定义hash进行优化
    public String longestDupSubstring2(String s) {
        /**
         * 如果重复子串最大长度为n,则长度n-1肯定能够找到重复子串.n+1肯定没有子串
         * 优化：将长度方向倒序查找改为二分查找。如果i找到了重复子串，查找i+1，如果没有查找i-1
         */
        int length = s.length();
        //1，重复子串的长度范围1~length-1
        int minLength = 1;
        int maxLength = length - 1;
        int middle;
        String result = "";
        while (minLength <= maxLength) {
            middle = (minLength + maxLength) >> 1;
            String str = findStr2(middle, s);
            if (str.isEmpty()) {
                maxLength = middle - 1;
            } else {
                result = str;
                minLength = middle + 1;
            }
        }
        return result;
    }

    //查找长度为count的重复子串,使用自定义的hash值
    private String findStr2(int count, String s) {
        /**
         * 字符为小写字母，可以转换为26进制的数字。
         * 题目的字符长度太大，直接转换会造成long的取值范围越界，采取取模的方式。
         * 但取模会造成hash冲突，hash相同时原始字符串进行二次比较
         */
        int length = s.length();
        long mod = (Long.MAX_VALUE / 26 - 1) / 26 - 1;
        long hash = 0;
        long pre = 1;
        for (int i = 0; i < count; i++) {
            hash = hash * 26 + (s.charAt(i) - 'a');
            if (hash >= mod) {
                hash %= mod;
            }
            if (i != 0) {
                pre *= 26;
                if (pre >= mod) {
                    pre %= mod;
                }
            }
        }
        Map<Long, List<Integer>> marks = new HashMap<>();
        List<Integer> indexs = new ArrayList<>();
        indexs.add(0);
        marks.put(hash, indexs);
        int maxIndex = length - count;
        for (int i = 1; i <= maxIndex; i++) {
            hash = (hash - pre * (s.charAt(i - 1) - 'a')) * 26 + (s.charAt(count + i - 1) - 'a');
            hash %= mod;
            if (hash < 0) {
                hash += mod;
            }
            if (marks.get(hash) == null) {
                List<Integer> tem = new ArrayList<>();
                tem.add(i);
                marks.put(hash, tem);
            } else {
                //hash值相同时再次比较字符串,防止hash冲突
                List<Integer> datas = marks.get(hash);
                String s1 = s.substring(i, i + count);
                int size = datas.size();
                for (int j = 0; j < size; j++) {
                    String str = s.substring(datas.get(j), datas.get(j) + count);
                    if (s1.equals(str)) {
                        return s1;
                    }
                }
                //hash值相同,但找不到相同的字符串时,将起始index存入map
                datas.add(i);
                marks.put(hash, datas);
            }
        }
        return "";
    }
}
