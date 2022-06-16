package com.demo.algorithm.leetcode.contest.doubleweek80;

/**
 * Created by chl on 2022/6/12.
 * description : 强密码检验器II
 *
 * 如果一个密码满足以下所有条件，我们称它是一个强密码：
 *
 * 它有至少 8个字符。
 * 至少包含一个小写英文字母。
 * 至少包含一个大写英文字母。
 * 至少包含一个数字。
 * 至少包含一个特殊字符。特殊字符为："!@#$%^&*()-+"中的一个。
 * 它不包含2个连续相同的字符（比方说"aab"不符合该条件，但是"aba"符合该条件）。
 * 给你一个字符串password，如果它是一个强密码，返回true，否则返回false。
 *
 * 示例 1：
 * 输入：password = "IloveLe3tcode!"
 * 输出：true
 * 解释：密码满足所有的要求，所以我们返回 true 。
 *
 * 示例 2：
 * 输入：password = "Me+You--IsMyDream"
 * 输出：false
 * 解释：密码不包含数字，且包含 2 个连续相同的字符。所以我们返回 false 。
 *
 * 示例 3：
 * 输入：password = "1aB!"
 * 输出：false
 * 解释：密码不符合长度要求。所以我们返回 false 。
 *
 * 提示：
 * 1 <= password.length <= 100
 * password包含字母，数字和"!@#$%^&*()-+"这些特殊字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/strong-password-checker-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StrongPasswordCheckerII {

   public boolean strongPasswordCheckerII(String password) {
      int length = password.length();
      if (length < 8) {
         return false;
      }
      boolean hasNum = false;
      boolean hasA = false;
      boolean hasa = false;
      boolean hasOther = false;
      char pre = 0;
      for (int i = 0; i < length; i++) {
         char cur = password.charAt(i);
         if (i != 0) {
            if (pre == cur) {
               return false;
            }
         }
         pre = cur;
         if (cur >= '0' && cur <= '9') {
            hasNum = true;
         } else if (cur >= 'a' && cur <= 'z') {
            hasa = true;
         } else if (cur >= 'A' && cur <= 'Z') {
            hasA = true;
         } else {
            hasOther = true;
         }
      }
      return hasa && hasA && hasNum && hasOther;
   }
}
