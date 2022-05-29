package com.demo.algorithm.leetcode.contest.week295;

/**
 * Created by chl on 2022/5/29.
 * description : 重排字符形成目标字符串
 *
 * 给你两个下标从0开始的字符串s和target。你可以从s取出一些字符并将其重排，得到若干新的字符串。
 * 从s中取出字符并重新排列，返回可以形成target的最大副本数。
 *
 * 示例 1：
 * 输入：s = "ilovecodingonleetcode", target = "code"
 * 输出：2
 * 解释：
 * 对于 "code" 的第 1 个副本，选取下标为 4 、5 、6 和 7 的字符。
 * 对于 "code" 的第 2 个副本，选取下标为 17 、18 、19 和 20 的字符。
 * 形成的字符串分别是 "ecod" 和 "code" ，都可以重排为 "code" 。
 * 可以形成最多 2 个 "code" 的副本，所以返回 2 。
 *
 * 示例 2：
 * 输入：s = "abcba", target = "abc"
 * 输出：1
 * 解释：
 * 选取下标为 0 、1 和 2 的字符，可以形成 "abc" 的 1 个副本。
 * 可以形成最多 1 个 "abc" 的副本，所以返回 1 。
 * 注意，尽管下标 3 和 4 分别有额外的 'a' 和 'b' ，但不能重用下标 2 处的 'c' ，所以无法形成 "abc" 的第 2 个副本。
 *
 * 示例 3：
 * 输入：s = "abbaccaddaeea", target = "aaaaa"
 * 输出：1
 * 解释：
 * 选取下标为 0 、3 、6 、9 和 12 的字符，可以形成 "aaaaa" 的 1 个副本。
 * 可以形成最多 1 个 "aaaaa" 的副本，所以返回 1 。
 *
 * 提示：
 * 1 <= s.length <= 100
 * 1 <= target.length <= 10
 * s 和 target 由小写英文字母组成
 */
public class RearrangeCharacters {

   public int rearrangeCharacters(String s, String target) {
      int length = s.length();
      //1，统计s中每个字符出现的次数
      int[] counts = new int[26];
      for (int i = 0; i < length; i++) {
         counts[s.charAt(i) - 'a']++;
      }
      //2，统计目标字符串中每个字符出现的数量
      length = target.length();
      int[] base = new int[26];
      for (int i = 0; i < length; i++) {
         base[target.charAt(i) - 'a']++;
      }
      //3，遍历比较字符的数量，从而判断能构建的副本数
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < 26; i++) {
         if (base[i] == 0) {
            continue;
         }
         if (counts[i] < base[i]) {
            return 0;
         }
         int replace = counts[i] / base[i];
         if (min > replace) {
            min = replace;
         }
      }
      return min;
   }
}
