package com.demo.algorithm.leetcode.contest.week295;

/**
 * Created by chl on 2022/5/29.
 * description : 价格减免
 *
 * 句子是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 '$' 。
 * 如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个价格。
 * 例如"$100"、"$23" 和 "$6.75" 表示价格，而 "100"、"$" 和 "2$3" 不是。
 * 注意：本题输入中的价格均为整数。
 * 给你一个字符串sentence和一个整数discount。对于每个表示价格的单词，都在价格的基础上减免discount%，并更新该单词到句子中。
 * 所有更新后的价格应该表示为一个恰好保留小数点后两位的数字。
 * 返回表示修改后句子的字符串。
 *
 * 示例 1：
 * 输入：sentence = "there are $1 $2 and 5$ candies in the shop", discount = 50
 * 输出："there are $0.50 $1.00 and 5$ candies in the shop"
 * 解释：
 * 表示价格的单词是 "$1" 和 "$2" 。
 * - "$1" 减免 50% 为 "$0.50" ，所以 "$1" 替换为 "$0.50" 。
 * - "$2" 减免 50% 为 "$1" ，所以 "$1" 替换为 "$1.00" 。
 *
 * 示例 2：
 * 输入：sentence = "1 2 $3 4 $5 $6 7 8$ $9 $10$", discount = 100
 * 输出："1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$"
 * 解释：
 * 任何价格减免 100% 都会得到 0 。
 * 表示价格的单词分别是 "$3"、"$5"、"$6" 和 "$9"。
 * 每个单词都替换为 "$0.00"。
 *
 * 提示：
 * 1 <= sentence.length <= 10^5
 * sentence 由小写英文字母、数字、' ' 和'$' 组成
 * sentence 不含前导和尾随空格
 * sentence 的所有单词都用单个空格分隔
 * 所有价格都是 正 整数且不含前导零
 * 所有价格 最多 为 10 位数字
 * 0 <= discount <= 100
 */
public class DiscountPrices {

   public String discountPrices(String sentence, int discount) {
      String[] dates = sentence.split(" ");
      StringBuilder builder = new StringBuilder();
      int length = dates.length;
      for (int i = 0; i < length; i++) {
         if (i > 0) {
            builder.append(" ");
         }
         if (isPrice(dates[i])) {
            builder.append('$');
            builder.append(getNewPrice(Long.parseLong(dates[i].substring(1)), discount));
         } else {
            builder.append(dates[i]);
         }
      }
      return builder.toString();
   }

   private String getNewPrice(long price, int discount) {
      long num = price * (100 - discount);
      if (num < 10) {
         return "0.0" + num;
      }
      if (num < 100) {
         return "0." + num;
      }
      long a = num / 100;
      long b = num % 100;
      StringBuilder builder = new StringBuilder();
      builder.append(a);
      builder.append('.');
      if (b < 10) {
         builder.append(0);
         builder.append(b);
      } else {
         builder.append(b);
      }
      return builder.toString();
   }

   private boolean isPrice(String str) {
      if (str.charAt(0) != '$') {
         return false;
      }
      int length = str.length();
      if (length == 1) {
         return false;
      }
      for (int i = 1; i < length; i++) {
         if (str.charAt(i) > '9' || str.charAt(i) < '0') {
            return false;
         }
      }
      return true;
   }
}
