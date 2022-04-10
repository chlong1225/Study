package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2022/4/10.
 * description : 剑指Offer55-II. 平衡二叉树
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回false 。
 *
 *
 * 限制：
 * 0 <= 树的结点个数 <= 10000
 */
public class BalancedTree {

    private Map<TreeNode, Integer> marks = new HashMap<>();

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        if (root.left != null) {
            if (marks.containsKey(root.left)) {
                left = marks.get(root.left);
            } else {
                getHeight(root.left);
            }
        }
        int right = 0;
        if (root.right != null) {
            if (marks.containsKey(root.right)) {
                right = marks.get(root.right);
            } else {
                right = getHeight(root.right);
            }
        }
        int height = Math.max(left, right) + 1;
        marks.put(root, height);
        return height;
    }
}
