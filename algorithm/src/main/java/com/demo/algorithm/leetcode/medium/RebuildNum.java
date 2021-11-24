package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/24/21
 * @author chenglong
 * description : 从英文中重建数字
 *
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按升序返回原始的数字。
 *
 * 示例 1：
 * 输入：s = "owoztneoer"
 * 输出："012"
 *
 * 示例 2：
 * 输入：s = "fviefuro"
 * 输出："45"
 *  
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 */
public class RebuildNum {

    //数字与单词的对照表，顺序上保证当前单词中至少有一个字符后面单词只没有
    private static final String[] NUMS_STR = {"zero", "two", "eight", "three","four","one","five","six", "seven",  "nine"};
    private static final int[] NUMS = {0, 2, 8, 3, 4, 1, 5, 6, 7, 9};

    private static final int NUM_LENGTH = 10;
    private static final int CHAR_LENGTH = 26;

    public String originalDigits(String s) {
        //1，统计s中字符出现的个数
        int[] counts = strToCounts(s);
        int[] numCounts = new int[NUM_LENGTH];
        //2，遍历查找数字对应的单词能够重建的数量
        for (int i = 0; i < NUM_LENGTH; i++) {
            int[] wordCounts = strToCounts(NUMS_STR[i]);
            int count = Integer.MAX_VALUE;
            for (int j = 0; j < CHAR_LENGTH; j++) {
                if (wordCounts[j] != 0) {
                    count = Math.min(count, counts[j] / wordCounts[j]);
                    if (count == 0) {
                        break;
                    }
                }
            }
            if (count == Integer.MAX_VALUE || count == 0) {
                continue;
            }
            //3,统计单词重建的次数
            numCounts[NUMS[i]] = count;
            //4，移除已经被重建的字符
            for (int j = 0; j < CHAR_LENGTH; j++) {
                if (wordCounts[j] != 0) {
                    counts[j] -= (wordCounts[j] * count);
                }
            }
        }
        return countsToStr(numCounts);
    }

    //统计字符串中字母出现的次数
    private int[] strToCounts(String s) {
        int[] counts = new int[CHAR_LENGTH];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            counts[s.charAt(i) - 'a']++;
        }
        return counts;
    }

    //将统计的数量转换为字符串
    private String countsToStr(int[] counts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                for (int j = 0; j < counts[i]; j++) {
                    builder.append(i);
                }
            }
        }
        return builder.toString();
    }
}
