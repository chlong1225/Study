package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chl on 2022/6/3.
 * description : 面试题17.26. 稀疏相似度
 *
 * 两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有2个，并集的元素有5个。
 * 给定一系列的长篇文档，每个文档元素各不相同，并与一个ID相关联。它们的相似度非常“稀疏”，也就是说任选2个文档，相似度都很接近0。
 * 请设计一个算法返回每对文档的ID及其相似度。只需输出相似度大于0的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
 * 输入为一个二维数组docs，docs[i]表示id 为i的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于0的文档，
 * 其格式为 {id1},{id2}: {similarity}，其中id1为两个文档中较小的id，similarity为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。
 *
 * 示例:
 * 输入:
 * [
 *  [14, 15, 100, 9, 3],
 *  [32, 1, 9, 3, 5],
 *  [15, 29, 2, 6, 8, 7],
 *  [7, 10]
 * ]
 * 输出:
 * [
 *  "0,1: 0.2500",
 *  "0,2: 0.1000",
 *  "2,3: 0.1429"
 * ]
 *
 * 提示：
 * docs.length <= 500
 * docs[i].length <= 500
 */
public class ComputeSimilarities {

   public List<String> computeSimilarities(int[][] docs) {
      List<String> result = new ArrayList<>();
      if (docs == null || docs.length == 0) {
         return result;
      }
      int m = docs.length;
      //1，对文档的数据进行排序
      for (int i = 0; i < m; i++) {
         if (docs[i] == null || docs[i].length == 0) {
            continue;
         }
         Arrays.sort(docs[i]);
      }
      //2，遍历比较两个文档
      for (int i = 0; i < m - 1; i++) {
         int[] cur = docs[i];
         if (cur == null || cur.length == 0) {
            continue;
         }
         for (int j = i + 1; j < m; j++) {
            int[] next = docs[j];
            if (next == null || next.length == 0) {
               continue;
            }
            int num = getSameNumCount(cur, next);
            if (num > 0) {
               int total = cur.length + next.length - num;
               StringBuilder builder = new StringBuilder();
               builder.append(i);
               builder.append(",");
               builder.append(j);
               builder.append(": ");
               builder.append(getDivide(num, total));
               result.add(builder.toString());
            }
         }
      }
      return result;
   }

   private String getDivide(int num, int total) {
      if (num >= total) {
         return "1.0000";
      }
      num *= 10000;
      int result = num / total;
      int last = num % total;
      if (last * 10 >= 5 * total) {
         result++;
      }
      if (result == 10000) {
         return "1.0000";
      }
      StringBuilder builder = new StringBuilder();
      builder.append("0.");
      if (result < 10) {
         builder.append("000");
         builder.append(result);
      } else if (result < 100) {
         builder.append("00");
         builder.append(result);
      } else if (result < 1000) {
         builder.append("0");
         builder.append(result);
      } else {
         builder.append(result);
      }
      return builder.toString();
   }

   //判断两个排序数组中重复的元素
   private int getSameNumCount(int[] cur, int[] next) {
      if (cur.length > next.length) {
         return getSameNumCount(next, cur);
      }
      int m = cur.length;
      int n = next.length;
      if (cur[m - 1] < next[0] || cur[0] > next[n - 1]) {
         return 0;
      }
      int count = 0;
      int start = 0;
      for (int i = 0; i < m; i++) {
         int target = cur[i];
         //在next数组中查找是否有target数字
         if (target < next[0]) {
            continue;
         }
         if (target == next[0]) {
            count++;
            continue;
         }
         if (target > next[n - 1]) {
            break;
         }
         if (target == next[n - 1]) {
            count++;
            break;
         }
         int end = n - 1;
         while (start < end) {
            int middle = (end - start) / 2 + start;
            if (next[middle] == target) {
               count++;
               break;
            } else if (next[middle] < target) {
               start = middle + 1;
               if (next[start] == target) {
                  count++;
                  break;
               }
               if (next[start] > target) {
                  start--;
                  break;
               }
            } else {
               end = middle - 1;
            }
         }
      }
      return count;
   }
}
