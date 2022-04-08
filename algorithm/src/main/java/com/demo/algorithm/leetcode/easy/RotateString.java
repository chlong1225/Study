package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/4/8.
 * description : 旋转字符串
 *
 * 给定两个字符串, s和goal。如果在若干次旋转操作之后，s能变成goal，那么返回true。
 * s的旋转操作就是将s最左边的字符移动到最右边。
 * 例如, 若s = 'abcde'，在旋转一次之后结果就是'bcdea'。
 *
 * 示例 1:
 * 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "abcde", goal = "abced"
 * 输出: false
 *
 * 提示:
 * 1 <= s.length, goal.length <= 100
 * s和goal由小写英文字母组成
 */
public class RotateString {

   public boolean rotateString(String s, String goal) {
      return s.length() == goal.length() && (s + s).contains(goal);
   }
}
