package com.demo.algorithm.leetcode.easy;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 完全二叉树的节点个数
 *
 * 给你一棵完全二叉树的根节点root，求出该树的节点个数。
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第h层，则该层包含1~2h个节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 *
 * 示例 2：
 * 输入：root = []
 * 输出：0
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 *
 * 提示：
 * 树中节点的数目范围是[0, 5 * 10^4]
 * 0 <= Node.val <= 5 * 10^4
 * 题目数据保证输入的树是完全二叉树
 *
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 */
public class CountNodes {

    private int count;

    public int countNodes(TreeNode root) {
        count = 0;
        if (root != null) {
            dfs(root);
        }
        return count;
    }

    private void dfs(TreeNode root) {
        count++;
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }
}
