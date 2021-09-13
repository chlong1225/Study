package com.demo.algorithm.leetcode.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/8/21
 * description : 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class TreeMaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> parent = new ArrayList<>();
        parent.add(root);
        List<TreeNode> child = new ArrayList<>();
        int depth = 0;
        int length;
        while (parent.size() > 0) {
            length = parent.size();
            child.clear();
            depth++;
            for (int i = 0; i < length; i++) {
                TreeNode treeNode = parent.get(i);
                if (treeNode.left != null) {
                    child.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    child.add(treeNode.right);
                }
            }
            if (child.size() == 0) {
                return depth;
            }
            parent.clear();
            parent.addAll(child);
        }
        return depth;
    }
}
