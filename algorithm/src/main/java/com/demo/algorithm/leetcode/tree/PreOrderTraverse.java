package com.demo.algorithm.leetcode.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/10/21
 * description : 二叉树的前序遍历
 *
 *  给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *  示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 */
public class PreOrderTraverse {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        getResult(root, result);
        return result;
    }

    private void getResult(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            getResult(root.left, result);
            getResult(root.right, result);
        }
    }

}
