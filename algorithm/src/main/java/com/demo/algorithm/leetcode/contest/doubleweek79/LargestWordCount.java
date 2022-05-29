package com.demo.algorithm.leetcode.contest.doubleweek79;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/5/29.
 * description : 最多单词数的发件人
 *
 * 给你一个聊天记录，共包含n条信息。给你两个字符串数组messages和senders，其中messages[i]是senders[i]发出的一条信息。
 * 一条信息是若干用单个空格连接的单词，信息开头和结尾不会有多余空格。发件人的单词计数是这个发件人总共发出的单词数。注意，一个发件人可能会发出多于一条信息。
 * 请你返回发出单词数最多的发件人名字。如果有多个发件人发出最多单词数，请你返回字典序最大的名字。
 *
 * 注意：
 * 字典序里，大写字母小于小写字母。
 * "Alice" 和"alice"是不同的名字。
 *
 * 示例 1：
 * 输入：messages = ["Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"], senders = ["Alice","userTwo","userThree","Alice"]
 * 输出："Alice"
 * 解释：Alice 总共发出了 2 + 3 = 5 个单词。
 * userTwo 发出了 2 个单词。
 * userThree 发出了 3 个单词。
 * 由于 Alice 发出单词数最多，所以我们返回 "Alice" 。
 *
 * 示例 2：
 * 输入：messages = ["How is leetcode for everyone","Leetcode is useful for practice"], senders = ["Bob","Charlie"]
 * 输出："Charlie"
 * 解释：Bob 总共发出了 5 个单词。
 * Charlie 总共发出了 5 个单词。
 * 由于最多单词数打平，返回字典序最大的名字，也就是 Charlie 。
 *
 * 提示：
 * n == messages.length == senders.length
 * 1 <= n <= 10^4
 * 1 <= messages[i].length <= 100
 * 1 <= senders[i].length <= 10
 * messages[i]包含大写字母、小写字母和' '。
 * messages[i]中所有单词都由单个空格隔开。
 * messages[i]不包含前导和后缀空格。
 * senders[i]只包含大写英文字母和小写英文字母。
 */
public class LargestWordCount {

   public String largestWordCount(String[] messages, String[] senders) {
      int length = messages.length;
      if (length == 1) {
         return senders[0];
      }
      //记录发送者对应的单词数
      Map<String, Integer> marks = new HashMap<>();
      int max = 0;
      //1，统计每个发送者发送的单词数量，并比较出最大单词数
      for (int i = 0; i < length; i++) {
         int count = (messages[i].split(" ")).length;
         String name = senders[i];
         if (marks.containsKey(name)) {
            int tem = marks.get(name) + count;
            if (tem > max) {
               max = tem;
            }
            marks.put(name, tem);
         } else {
            if (count > max) {
               max = count;
            }
            marks.put(name, count);
         }
      }
      List<String> dates = new ArrayList<>();
      //2，找出所有单词数最大的发送者
      for (String name : marks.keySet()) {
         if (marks.get(name) == max) {
            dates.add(name);
         }
      }
      if (dates.size() == 1) {
         return dates.get(0);
      }
      //3，对发件人名字进行排序
      Collections.sort(dates);
      return dates.get(dates.size() - 1);
   }
}
