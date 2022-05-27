package com.demo.algorithm.leetcode.easy;

import androidx.annotation.NonNull;

/**
 * Created by chl on 2022/5/28.
 * description : 删除最外层的括号
 *
 * 有效括号字符串为空 ""、"(" + A + ")"或A + B ，其中A和B都是有效的括号字符串，+代表字符串的连接。
 * 例如，""，"()"，"(())()"和"(()(()))"都是有效的括号字符串。
 * 如果有效字符串s非空，且不存在将其拆分为s = A + B的方法，我们称其为原语（primitive），其中A和B都是非空有效括号字符串。
 * 给出一个非空有效字符串s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中P_i是有效括号字符串原语。
 * 对s进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 *
 * 示例 1：
 * 输入：s = "(()())(())"
 * 输出："()()()"
 * 解释：
 * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
 *
 * 示例 2：
 * 输入：s = "(()())(())(()(()))"
 * 输出："()()()()(())"
 * 解释：
 * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 *
 * 示例 3：
 * 输入：s = "()()"
 * 输出：""
 * 解释：
 * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
 * 删除每个部分中的最外层括号后得到 "" + "" = ""。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 为 '(' 或 ')'
 * s 是一个有效括号字符串
 */
public class RemoveOuterParentheses {

   public String removeOuterParentheses(@NonNull String s) {
      int length = s.length();
      //位置为0的肯定是左括号
      int start = 0;
      int count = 1;
      StringBuilder builder = new StringBuilder();
      for (int i = 1; i < length; i++) {
         if (s.charAt(i) == '(') {
            count++;
         } else {
            count--;
            if (count == 0) {
               //区间start~i之间有效括号
               builder.append(s.substring(start + 1, i));
               start = i + 1;
            }
         }
      }
      return builder.toString();
   }
}
