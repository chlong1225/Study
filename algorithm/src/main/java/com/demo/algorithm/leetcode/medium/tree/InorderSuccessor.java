package com.demo.algorithm.leetcode.medium.tree;

/**
 * create on 2022/5/16
 * @author chenglong
 * description : 面试题04.06.后继者
 *
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 *   2
 *  / \
 * 1   3
 * 输出: 2
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * 输出: null
 */
public class InorderSuccessor {

    private boolean isNext = false;
    private boolean find = false;
    private TreeNode result = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        isNext = false;
        find = false;
        result = null;
        /**
         * 直接中序遍历比较
         */
        dfs(root, p);
        return result;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        if (find) {
            return;
        }
        dfs(root.left, p);
        if (root.val == p.val) {
            isNext = true;
        } else if (root.val > p.val) {
            if (isNext) {
                isNext = false;
                find = true;
                result = root;
            }
        }
        dfs(root.right, p);
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }
}
