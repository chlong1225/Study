package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create by chenglong on 9/8/21
 * description : 平衡二叉树
 *
 *  给定一个二叉树，判断它是否是高度平衡的二叉树。
 *  本题中，一棵高度平衡二叉树定义为： 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class BalanceTree {

    private static boolean isBalance = true;

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isBalance && isBalanced(root.left) && isBalanced(root.right) && Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1) {
            return true;
        } else {
            return false;
        }
    }

    private static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if (Math.abs(left - right) > 1) {
            isBalance = false;
        }
        return Math.max(left, right) + 1;
    }

}
