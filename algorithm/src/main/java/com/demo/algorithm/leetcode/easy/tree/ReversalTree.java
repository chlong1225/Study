package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create by chenglong on 9/17/21
 * description : 翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class ReversalTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        reversal(root);
        return root;
    }

    private void reversal(TreeNode root) {
        if (root.left != null || root.right != null) {
            TreeNode tem = root.left;
            root.left = root.right;
            root.right = tem;
            if (root.left != null) {
                reversal(root.left);
            }
            if (root.right != null) {
                reversal(root.right);
            }
        }
    }
}
