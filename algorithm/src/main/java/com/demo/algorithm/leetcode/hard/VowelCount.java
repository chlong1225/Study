package com.demo.algorithm.leetcode.hard;
/**
 * Created by chl on 2022/1/17.
 * description : 统计元音字母序列的数目
 *
 * 给你一个整数n，请你帮忙统计一下我们可以按下述规则形成多少个长度为n的字符串：
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音'a'后面都只能跟着'e'
 * 每个元音'e'后面只能跟着'a'或者是'i'
 * 每个元音'i'后面不能 再跟着另一个'i'
 * 每个元音'o'后面只能跟着'i'或者是'u'
 * 每个元音'u'后面只能跟着'a'
 * 由于答案可能会很大，所以请你返回 模10^9 + 7之后的结果。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 *
 * 示例 3：
 * 输入：n = 5
 * 输出：68
 *
 * 提示：
 * 1 <= n <= 2 * 10^4
 */
public class VowelCount {

   private static final int MOD = 1000000007;

   public int countVowelPermutation(int n) {
      /**
       * 后一个字母组合数量与前一个字母组合数量相关，可以考虑使用动态规划
       * int[][] marks = new int[n+1][5]
       * 初始条件:marks[1][0] = 1 ; marks[1][1] =1 ... marks[1][4] =1
       * 结果counts = marks[n][0]+...+marks[n][4]
       * 动态规划转移方程：对应题目中的条件
       */
      //marks[i][j]。其中j分别对应：a,e,i,o,u
      int[][] marks = new int[n + 1][5];
      //1，初始化条件
      for (int i = 0; i <= 4; i++) {
         marks[1][i] = 1;
      }
      //2，遍历n进行状态转移
      for (int i = 2; i <= n; i++) {
         //对应a元音后面只能跟着e
         marks[i][1] = marks[i - 1][0];

         //对应e后面只能跟着a或i
         marks[i][0] = marks[i - 1][1];
         marks[i][2] = marks[i - 1][1];

         //对应i后面不能跟着i
         marks[i][0] += marks[i - 1][2];
         if (marks[i][0] >= MOD) {
            marks[i][0] %= MOD;
         }
         marks[i][1] += marks[i - 1][2];
         if (marks[i][1] >= MOD) {
            marks[i][1] %= MOD;
         }
         marks[i][3] = marks[i - 1][2];
         marks[i][4] = marks[i - 1][2];

         //对应o后面只能跟着i或者u
         marks[i][2] += marks[i - 1][3];
         if (marks[i][2] >= MOD) {
            marks[i][2] %= MOD;
         }
         marks[i][4] += marks[i - 1][3];
         if (marks[i][4] >= MOD) {
            marks[i][4] %= MOD;
         }

         //对应u后面只能跟着a
         marks[i][0] += marks[i - 1][4];
         if (marks[i][0] >= MOD) {
            marks[i][0] %= MOD;
         }
      }

      int count = marks[n][0];
      for (int i = 1; i < 5; i++) {
         count += marks[n][i];
         if (count >= MOD) {
            count %= MOD;
         }
      }
      return count;
   }
}
