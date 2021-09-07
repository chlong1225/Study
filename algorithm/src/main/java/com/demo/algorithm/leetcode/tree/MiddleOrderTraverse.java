package com.demo.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/6/21
 * description : 二叉树的中序遍历
 *
 *  给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *  示例1：
 *  输入：root = [1,null,2,3]
 *  输出：[1,3,2]
 *
 *  示例2：
 *  输入：root = []
 *  输出：[]
 *
 *  示例3：
 *  输入：root = [1]
 *  输出：[1]
 *
 * 示例4：
 * 输入：root = [1,2]
 * 输出：[2,1]
 */
public class MiddleOrderTraverse {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        getLeft(root.left, result);
        result.add(root.val);
        getRight(root.right, result);
        return result;
    }

    private static void getRight(TreeNode right, List<Integer> result) {
        if (right != null) {
            getLeft(right.left, result);
            result.add(right.val);
            getRight(right.right, result);
        }
    }

    private static void getLeft(TreeNode left, List<Integer> result) {
        if (left != null) {
            getLeft(left.left, result);
            result.add(left.val);
            getRight(left.right, result);
        }
    }

}
