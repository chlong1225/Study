package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create by chenglong on 9/7/21
 * description : 相同的树
 *
 *  给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class SameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return false;
        }
        boolean[] isSame = {true,};
        checkRoot(p,q,isSame);
        return isSame[0];
    }

    private static void checkRoot(TreeNode p, TreeNode q, boolean[] isCheck) {
        if (isCheck[0]) {
            if (p.left != null && q.left != null) {
                checkRoot(p.left, q.left, isCheck);
            } else if (p.left != null && q.left == null) {
                isCheck[0] = false;
            } else if (p.left == null && q.left != null) {
                isCheck[0] = false;
            }
        }
        if (isCheck[0]) {
            isCheck[0] = (p.val == q.val);
        }
        if (isCheck[0]) {
            if (p.right != null && q.right != null) {
                checkRoot(p.right, q.right, isCheck);
            } else if (p.right != null && q.right == null) {
                isCheck[0] = false;
            } else if (p.right == null && q.right != null) {
                isCheck[0] = false;
            }
        }
    }
}
