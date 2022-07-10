package com.demo.algorithm.leetcode.contest.week301;

import java.util.TreeMap;

/**
 * Created by chl on 2022/7/10.
 * description : 无限集中的最小数字
 *
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 *
 * 实现 SmallestInfiniteSet 类：
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含所有正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数num不存在于无限集中，则将一个num添加到该无限集中。
 *
 * 示例：
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 *
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 *                                    // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 *
 * 提示：
 * 1 <= num <= 1000
 * 最多调用popSmallest和addBack方法共计1000 次
 */
public class SmallestInfiniteSet {

   private TreeMap<Integer, Integer> marks = new TreeMap<>();
   private int min = 1;

   public SmallestInfiniteSet() {
      marks.clear();
      min = 1;
   }

   public int popSmallest() {
      while (marks.containsKey(min)) {
         min++;
      }
      int tem = min;
      marks.put(tem, -1);
      min++;
      return tem;
   }

   public void addBack(int num) {
      if (marks.containsKey(num)) {
         marks.remove(num);
         if (num < min) {
            min = num;
         }
      }
   }
}
