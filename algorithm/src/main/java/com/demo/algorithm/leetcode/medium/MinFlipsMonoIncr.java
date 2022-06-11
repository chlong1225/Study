package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/6/11.
 * description : 将字符串翻转到单调递增
 *
 * 如果一个二进制字符串，是以一些0（可能没有0）后面跟着一些1（也可能没有 1）的形式组成的，那么该字符串是单调递增的。
 * 给你一个二进制字符串s，你可以将任何0翻转为1或者将1翻转为0 。
 * 返回使s单调递增的最小翻转次数。
 *
 * 示例 1：
 * 输入：s = "00110"
 * 输出：1
 * 解释：翻转最后一位得到 00111.
 *
 * 示例 2：
 * 输入：s = "010110"
 * 输出：2
 * 解释：翻转得到 011111，或者是 000111。
 *
 * 示例 3：
 * 输入：s = "00011000"
 * 输出：2
 * 解释：翻转得到 00000000。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'
 */
public class MinFlipsMonoIncr {

   public int minFlipsMonoIncr(String s) {
      /**
       * 由于相邻的字符之间存在约束关系，可以使用动态规划
       */
      int length = s.length();
      if (length == 1) {
         return 0;
      }
      //记录当前字符为0,1时对应的次数
      int count0 = 0;
      int count1 = 1;
      for (int i = 0; i < length; i++) {
         if (s.charAt(i) == '0') {
            count1 = Math.min(count0, count1) + 1;
         } else {
            count1 = Math.min(count0, count1);
            count0++;
         }
      }
      return Math.min(count0, count1);
   }
}
