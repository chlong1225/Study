package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 左叶子之和
 *
 * 给定二叉树的根节点root，返回所有左叶子之和。
 *
 * 示例 1：
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是9和15，所以返回24
 *
 * 示例 2:
 * 输入: root = [1]
 * 输出: 0
 *
 * 提示:
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 *
 */
public class SumOfLeftLeaves {

    private int sum;

    public int sumOfLeftLeaves(TreeNode root) {
        sum = 0;
        dfs(root,false);
        return sum;
    }

    private void dfs(TreeNode root, boolean isLeft) {
        if (root.left == null && root.right == null) {
            //当前为叶子节点
            if (isLeft) {
                sum += root.val;
            }
        } else {
            if (root.left != null) {
                dfs(root.left, true);
            }
            if (root.right != null) {
                dfs(root.right, false);
            }
        }
    }
}
