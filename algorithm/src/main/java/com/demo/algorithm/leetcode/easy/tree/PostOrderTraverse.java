package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/10/21
 * description : 二叉树的后序遍历
 *
 *  给定一个二叉树，返回它的 后序 遍历。
 */
public class PostOrderTraverse {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        getResult(root, result);
        return result;
    }

    private void getResult(TreeNode root, List<Integer> result) {
        if (root != null) {
            getResult(root.left, result);
            getResult(root.right, result);
            result.add(root.val);
        }
    }

}
