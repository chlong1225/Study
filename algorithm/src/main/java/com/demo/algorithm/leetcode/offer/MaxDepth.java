package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/10.
 * description : 剑指Offer55-I. 二叉树的深度
 *
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度3 。
 *
 * 提示：
 * 节点总数 <= 10000
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            depth++;
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return depth;
    }
}
