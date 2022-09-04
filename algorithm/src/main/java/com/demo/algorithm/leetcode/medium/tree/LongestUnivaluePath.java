package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2022/9/2
 * @author chenglong
 * description : 最长同值路径
 *
 * 给定一个二叉树的root，返回最长的路径的长度，这个路径中的每个节点具有相同值。这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 *
 * 示例 2:
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 * 提示:
 * 树的节点数的范围是[0, 10^4]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过1000
 */
public class LongestUnivaluePath {

    private int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int left1 = 0;
        int right1 = 0;
        if (root.left != null && root.left.val == root.val) {
            left1 = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            right1 = right + 1;
        }
        if (left1 + right1 > max) {
            max = left1 + right1;
        }
        return Math.max(left1, right1);
    }
}
