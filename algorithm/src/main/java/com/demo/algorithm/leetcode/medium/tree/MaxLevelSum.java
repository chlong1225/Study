package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/7/31.
 * description : 最大层内元素和
 *
 * 给你一个二叉树的根节点root。设根节点位于二叉树的第1层，而根节点的子节点位于第2层，依此类推。
 * 请返回层内元素之和最大的那几层（可能只有一层）的层号，并返回其中最小的那个。
 *
 * 示例 1：
 * 输入：root = [1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 *
 * 示例 2：
 * 输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * 输出：2
 *
 * 提示：
 * 树中的节点数在[1, 104]范围内
 * -10^5<= Node.val <= 10^5
 */
public class MaxLevelSum {

   public int maxLevelSum(TreeNode root) {
      int result = 0;
      int level = 0;
      int max = Integer.MIN_VALUE;
      List<TreeNode> dates = new ArrayList<>();
      List<TreeNode> next = new ArrayList<>();
      dates.add(root);
      while (dates.size() > 0) {
         level++;
         int sum = 0;
         for (int i = 0; i < dates.size(); i++) {
            TreeNode node = dates.get(i);
            sum += node.val;
            if (node.left != null) {
               next.add(node.left);
            }
            if (node.right != null) {
               next.add(node.right);
            }
         }
         if (sum > max) {
            max = sum;
            result = level;
         }
         dates.clear();
         dates.addAll(next);
         next.clear();
      }
      return result;
   }
}
