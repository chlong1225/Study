package com.demo.algorithm.leetcode.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create by chenglong on 9/8/21
 * description : 对称二叉树
 *
 *  给定一个二叉树，检查它是否是镜像对称的。

 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 */
public class SymmetryTree {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        boolean[] results = {true};
        compareTree(root.left, root.right, results);
        return results[0];
    }

    private static void compareTree(TreeNode left, TreeNode right, boolean[] results) {
        if (results[0]) {
            results[0] = (left.val == right.val);
        }
        if (results[0]) {
            if (left.left != null && right.right != null) {
                compareTree(left.left, right.right, results);
            } else if (left.left == null && right.right != null) {
                results[0] = false;
            } else if (left.left != null) {
                results[0] = false;
            }
        }
        if (results[0]) {
            if (left.right != null && right.left != null) {
                compareTree(left.right, right.left, results);
            } else if (left.right == null && right.left != null) {
                results[0] = false;
            } else if (left.right != null) {
                results[0] = false;
            }
        }
    }

}
