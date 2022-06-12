package com.demo.algorithm.leetcode.contest.week297;

import java.util.Arrays;

/**
 * Created by chl on 2022/6/12.
 * description : 公平分发饼干
 *
 * 给你一个整数数组cookies，其中cookies[i]表示在第i个零食包中的饼干数量。另给你一个整数k表示等待分发零食包的孩子数量，所有零食包都需要分发。
 * 在同一个零食包中的所有饼干都必须分发给同一个孩子，不能分开。
 * 分发的不公平程度定义为单个孩子在分发过程中能够获得饼干的最大总数。
 * 返回所有分发的最小不公平程度。
 *
 * 示例 1：
 * 输入：cookies = [8,15,10,20,8], k = 2
 * 输出：31
 * 解释：一种最优方案是 [8,15,8] 和 [10,20] 。
 * - 第 1 个孩子分到 [8,15,8] ，总计 8 + 15 + 8 = 31 块饼干。
 * - 第 2 个孩子分到 [10,20] ，总计 10 + 20 = 30 块饼干。
 * 分发的不公平程度为 max(31,30) = 31 。
 * 可以证明不存在不公平程度小于 31 的分发方案。
 *
 * 示例 2：
 * 输入：cookies = [6,1,3,2,2,4,1,2], k = 3
 * 输出：7
 * 解释：一种最优方案是 [6,1]、[3,2,2] 和 [4,1,2] 。
 * - 第 1 个孩子分到 [6,1] ，总计 6 + 1 = 7 块饼干。
 * - 第 2 个孩子分到 [3,2,2] ，总计 3 + 2 + 2 = 7 块饼干。
 * - 第 3 个孩子分到 [4,1,2] ，总计 4 + 1 + 2 = 7 块饼干。
 * 分发的不公平程度为 max(7,7,7) = 7 。
 * 可以证明不存在不公平程度小于 7 的分发方案。
 *
 * 提示：
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 10^5
 * 2 <= k <= cookies.length
 */
public class DistributeCookies {

   private int min = Integer.MAX_VALUE;

   public int distributeCookies(int[] cookies, int k) {
      Arrays.sort(cookies);
      //零食与小朋友数量相同时，只能分配每人一包
      if (k == cookies.length) {
         return cookies[k - 1];
      }
      int[] marks = new int[k];
      dfs(0, marks, cookies);
      return min;
   }

   private void dfs(int index, int[] marks, int[] cookies) {
      int n = cookies.length;
      int m = marks.length;
      if (index == n) {
         int max = marks[0];
         for (int i = 1; i < m; i++) {
            if (marks[i] > max) {
               max = marks[i];
            }
         }
         if (min > max) {
            min = max;
         }
         return;
      }
      int cur = cookies[index];
      for (int i = 0; i < m; i++) {
         marks[i] += cur;
         if (marks[i] < min) {
            dfs(index + 1, marks, cookies);
         }
         marks[i] -= cur;
      }
   }
}
