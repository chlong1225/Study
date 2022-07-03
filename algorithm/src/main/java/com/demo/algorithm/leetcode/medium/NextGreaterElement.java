package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/7/3.
 * description : 下一个更大元素III
 *
 * 给你一个正整数n，请你找出符合条件的最小整数，其由重新排列n中存在的每位数字组成，并且其值大于n 。
 * 如果不存在这样的正整数，则返回 -1 。
 * 注意，返回的整数应当是一个32位整数，如果存在满足题意的答案，但不是32位整数，同样返回-1 。
 *
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：21
 *
 * 示例 2：
 * 输入：n = 21
 * 输出：-1
 *
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class NextGreaterElement {

   public int nextGreaterElement(int n) {
      /**
       * 分析：当存在符合条件的的数时，n位数上必定非降序
       */
      //1，转换为字符串，查找需要交换的位置
      char[] dates = ("" + n).toCharArray();
      int index = -1;
      int length = dates.length;
      if (length == 1) {
         return -1;
      }
      char pre = dates[length - 1];
      for (int i = length - 2; i >= 0; i--) {
         if (dates[i] < pre) {
            index = i;
            break;
         }
         pre = dates[i];
      }
      //此时数字从大到小排列，不存在大于n的数
      if (index == -1) {
         return -1;
      }
      int replace = -1;
      for (int i = index + 1; i < length; i++) {
         if (dates[i] > dates[index]) {
            if (replace == -1) {
               replace = i;
            } else {
               if (dates[i] <= dates[replace]) {
                  replace = i;
               }
            }
         }
      }
      //2，交换字符
      char tem = dates[index];
      dates[index] = dates[replace];
      dates[replace] = tem;
      //3，index之后的数字从大到小倒序为从小到大
      for (int i = index + 1; i < length; i++) {
         int end = length + index - i;
         if (i < end) {
            tem = dates[i];
            dates[i] = dates[end];
            dates[end] = tem;
         } else {
            break;
         }
      }
      //4，判断是否越界
      if (length < 10) {
         return Integer.parseInt(new String(dates));
      }
      char[] compare = ("" + Integer.MAX_VALUE).toCharArray();
      boolean isBig = false;
      for (int i = 0; i < dates.length; i++) {
         if (dates[i] > compare[i]) {
            isBig = true;
            break;
         } else if (dates[i] < compare[i]) {
            break;
         }
      }
      if (isBig) {
         return -1;
      }
      return Integer.parseInt(new String(dates));
   }
}
