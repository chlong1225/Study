package com.demo.algorithm.leetcode.contest.doubleweek80;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/6/12.
 * description : 替换字符后匹配
 *
 * 给你两个字符串s和sub。同时给你一个二维字符数组mappings，其中mappings[i]=[oldi, newi]表示你可以替换sub中任意数目的oldi个字符，替换成newi 。sub中每个字符不能被替换超过一次。
 * 如果使用 mappings替换0个或者若干个字符，可以将sub变成s的一个子字符串，请你返回true，否则返回false。
 * 一个子字符串是字符串中连续非空的字符序列。
 *
 * 示例 1：
 * 输入：s = "fool3e7bar", sub = "leet", mappings = [["e","3"],["t","7"],["t","8"]]
 * 输出：true
 * 解释：将 sub 中第一个 'e' 用 '3' 替换，将 't' 用 '7' 替换。
 * 现在 sub = "l3e7" ，它是 s 的子字符串，所以我们返回 true 。
 *
 * 示例 2：
 * 输入：s = "fooleetbar", sub = "f00l", mappings = [["o","0"]]
 * 输出：false
 * 解释：字符串 "f00l" 不是 s 的子串且没有可以进行的修改。
 * 注意我们不能用 'o' 替换 '0' 。
 *
 * 示例 3：
 * 输入：s = "Fool33tbaR", sub = "leetd", mappings = [["e","3"],["t","7"],["t","8"],["d","b"],["p","b"]]
 * 输出：true
 * 解释：将 sub 里第一个和第二个 'e' 用 '3' 替换，用 'b' 替换 sub 里的 'd' 。
 * 得到 sub = "l33tb" ，它是 s 的子字符串，所以我们返回 true 。
 *
 * 提示：
 * 1 <= sub.length <= s.length <= 5000
 * 0 <= mappings.length <= 1000
 * mappings[i].length == 2
 * oldi != newi
 * s 和sub只包含大写和小写英文字母和数字。
 * oldi和newi是大写、小写字母或者是个数字。
 */
public class MatchReplacement {

   public boolean matchReplacement(String s, String sub, char[][] mappings) {
      int n = s.length();
      int m = sub.length();
      List<boolean[]> dates = new ArrayList<>(128);
      for (int i = 0; i < 128; i++) {
         dates.add(new boolean[128]);
      }
      int length = mappings.length;
      for (int i = 0; i < length; i++) {
         char[] mapping = mappings[i];
         dates.get(mapping[0])[mapping[1]] = true;
      }
      /**
       * 分析：sub替换后长度不变。
       */
      for (int i = 0; i <= n - m; i++) {
         if (check(s.substring(i, i + m), sub, dates)) {
            return true;
         }
      }
      return false;
   }

   private boolean check(String s, String sub, List<boolean[]> dates) {
      int n = s.length();
      for (int i = 0; i < n; i++) {
         if (s.charAt(i) != sub.charAt(i)) {
            //判断是否可以替换
            if (!dates.get(sub.charAt(i))[s.charAt(i)]) {
               return false;
            }
         }
      }
      return true;
   }
}
