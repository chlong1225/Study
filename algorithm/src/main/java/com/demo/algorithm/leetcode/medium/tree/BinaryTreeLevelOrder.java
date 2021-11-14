package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/14.
 * description : 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> roots = new ArrayList<>();
        roots.add(root);
        while (roots.size() > 0) {
            List<TreeNode> next = new ArrayList<>();
            List<Integer> datas = new ArrayList<>();
            int size = roots.size();
            for (int i = 0; i < size; i++) {
                TreeNode tem = roots.get(i);
                datas.add(tem.val);
                if (tem.left != null) {
                    next.add(tem.left);
                }
                if (tem.right != null) {
                    next.add(tem.right);
                }
            }
            result.add(datas);
            roots.clear();
            roots.addAll(next);
            next.clear();
        }
        return result;
    }
}
