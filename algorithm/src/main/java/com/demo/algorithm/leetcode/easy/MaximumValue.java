package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2023/6/23.
 * description : 数组中字符串的最大值
 *
 * 一个由字母和数字组成的字符串的值定义如下：
 * 如果字符串只包含数字，那么值为该字符串在10进制下的所表示的数字。
 * 否则，值为字符串的长度。
 * 给你一个字符串数组strs，每个字符串都只由字母和数字组成，请你返回strs中字符串的最大值。
 *
 * 示例 1：
 * 输入：strs = ["alic3","bob","3","4","00000"]
 * 输出：5
 * 解释：
 * - "alic3" 包含字母和数字，所以值为长度 5 。
 * - "bob" 只包含字母，所以值为长度 3 。
 * - "3" 只包含数字，所以值为 3 。
 * - "4" 只包含数字，所以值为 4 。
 * - "00000" 只包含数字，所以值为 0 。
 * 所以最大的值为 5 ，是字符串 "alic3" 的值。
 *
 * 示例 2：
 * 输入：strs = ["1","01","001","0001"]
 * 输出：1
 * 解释：
 * 数组中所有字符串的值都是 1 ，所以我们返回 1 。
 *
 * 提示：
 * 1 <= strs.length <= 100
 * 1 <= strs[i].length <= 9
 * strs[i]只包含小写英文字母和数字。
 */
public class MaximumValue {

   public int maximumValue(String[] strs) {
      int max = 0;
      for (int i = 0; i < strs.length; i++) {
         int num = getNum(strs[i]);
         if (num > max) {
            max = num;
         }
      }
      return max;
   }

   private int getNum(String str) {
      int sum = 0;
      int base = 10;
      for (int i = 0; i < str.length(); i++) {
         char c = str.charAt(i);
         if (c >= 'a' && c <= 'z') {
            return str.length();
         }
         int cur = c - '0';
         sum = sum * base + cur;
      }
      return sum;
   }
}
