package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2022/5/24
 * @author chenglong
 * description : 单值二叉树
 *
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回true；否则返回false。
 *
 * 示例 1：
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：[2,2,2,5,2]
 * 输出：false
 *
 * 提示：
 * 给定树的节点数范围是[1, 100]。
 * 每个节点的值都是整数，范围为[0, 99]。
 */
public class UnivalTree {

    private boolean isDiff = false;

    public boolean isUnivalTree(TreeNode root) {
        isDiff = false;
        int compare = root.val;
        dfs(root.left, compare);
        dfs(root.right, compare);
        return !isDiff;
    }

    private void dfs(TreeNode root, int compare) {
        if (root == null) {
            return;
        }
        if (isDiff) {
            return;
        }
        if (root.val != compare) {
            isDiff = true;
        } else {
            dfs(root.left, compare);
            dfs(root.right, compare);
        }
    }
}
