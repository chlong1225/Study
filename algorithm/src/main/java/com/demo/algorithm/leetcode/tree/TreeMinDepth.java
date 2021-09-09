package com.demo.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 9/8/21
 * description : 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 */
public class TreeMinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> parent = new ArrayList<>();
        List<TreeNode> child = new ArrayList<>();
        parent.add(root);
        int length;
        int minDepth = 0;
        while (parent.size() > 0) {
            minDepth++;
            length = parent.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = parent.get(i);
                if (node.left == null && node.right == null) {
                    return minDepth;
                }
                if (node.left != null) {
                    child.add(node.left);
                }
                if (node.right != null) {
                    child.add(node.right);
                }
            }
            parent.clear();
            parent.addAll(child);
            child.clear();
        }

        return minDepth;
    }
}
