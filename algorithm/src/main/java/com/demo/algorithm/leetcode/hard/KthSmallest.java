package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by chl on 2023/6/7.
 * description : 有序矩阵中的第k个最小数组和
 *
 * 给你一个m*n的矩阵mat，以及一个整数k，矩阵中的每一行都以非递减的顺序排列。
 * 你可以从每一行中选出1个元素形成一个数组。返回所有可能数组中的第k个最小数组和。
 *
 * 示例 1：
 * 输入：mat = [[1,3,11],[2,4,6]], k = 5
 * 输出：7
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
 *
 * 示例 2：
 * 输入：mat = [[1,3,11],[2,4,6]], k = 9
 * 输出：17
 *
 * 示例 3：
 * 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * 输出：9
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
 *
 * 示例 4：
 * 输入：mat = [[1,1,10],[2,2,9]], k = 7
 * 输出：12
 *
 * 提示：
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n^m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] 是一个非递减数组
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class KthSmallest {

   public int kthSmallest(int[][] mat, int k) {
      int m = mat.length;
      int n = mat[0].length;
      List<Integer> result = new ArrayList<>(k);
      int max = Math.min(n, k);
      for (int i = 0; i < max; i++) {
         result.add(mat[0][i]);
      }
      for (int i = 1; i < m; i++) {
         result = merge(result, mat[i], k);
      }
      return result.get(k - 1);
   }

   private List<Integer> merge(List<Integer> dates, int[] mats, int k) {
      List<Integer> result = new ArrayList<>();
      PriorityQueue<int[]> marks = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
      for (int i = 0; i < dates.size(); i++) {
         marks.offer(new int[]{0, i, dates.get(i) + mats[0]});
      }
      while (k > 0 && (!marks.isEmpty())) {
         int[] cur = marks.poll();
         result.add(cur[2]);
         if (cur[0] + 1 < mats.length) {
            marks.offer(new int[]{cur[0] + 1, cur[1], dates.get(cur[1]) + mats[cur[0] + 1]});
         }
         k--;
      }
      return result;
   }
}
