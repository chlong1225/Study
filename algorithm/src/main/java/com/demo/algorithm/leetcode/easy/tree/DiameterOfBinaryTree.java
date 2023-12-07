package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/12/7
 * @author chenglong
 * description : 二叉树的直径
 *
 * 给你一棵二叉树的根节点，返回该树的直径。
 * 二叉树的直径是指树中任意两个节点之间最长路径的长度。这条路径可能经过也可能不经过根节点root。
 * 两节点之间路径的长度由它们之间边数表示。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 *
 * 示例 2：
 * 输入：root = [1,2]
 * 输出：1
 *
 * 提示：
 * 树中节点数目在范围 [1, 10^4] 内
 * -100 <= Node.val <= 100
 */
public class DiameterOfBinaryTree {

    private int max;

    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        dfs(root);
        return max - 1;
    }

    private int dfs(TreeNode root) {
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = dfs(root.left);
        }
        if (root.right != null) {
            right = dfs(root.right);
        }
        int count = 1 + left + right;
        if (count > max) {
            max = count;
        }
        return 1 + Math.max(left, right);
    }
}
