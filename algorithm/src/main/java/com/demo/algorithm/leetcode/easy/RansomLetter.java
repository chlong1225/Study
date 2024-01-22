package com.demo.algorithm.leetcode.easy;

/**
 * create on 12/4/21
 * @author chenglong
 * description : 赎金信
 *
 * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
 * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
 * 如果可以构成，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 *
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 *
 * 示例 3
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *  
 * 提示：
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 */
public class RansomLetter {

    public boolean canConstruct(String ransomNote, String magazine) {
        /**
         * 分析：构成赎金信对应的条件：ransomNote中的字符在magazine中均能够找到，并且数量不少于
         */
        //1，比较长度
        int m = ransomNote.length();
        int n = magazine.length();
        if (m > n) {
            return false;
        }
        //2，记录magazine中字符的数量
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < m; i++) {
            int index = ransomNote.charAt(i) - 'a';
            if (counts[index] > 0) {
                counts[index]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
