package com.demo.algorithm.leetcode.tree;

/**
 * create by chenglong on 9/9/21
 * description : 路径求和
 *
 *  给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *  叶子节点 是指没有子节点的节点。
 *
 */
public class PathSum {

    private boolean hasFind = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        findPath(root, targetSum);
        return hasFind;
    }

    private void findPath(TreeNode root, int targetSum) {
        if (!hasFind) {
            if (root.left == null && root.right == null) {
                if (root.val == targetSum) {
                    hasFind = true;
                }
            } else {
                if (root.left != null) {
                    findPath(root.left, targetSum - root.val);
                }
                if (root.right != null) {
                    findPath(root.right, targetSum - root.val);
                }
            }
        }
    }

}
