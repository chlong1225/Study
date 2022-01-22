package com.demo.algorithm.leetcode.hard.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * Created by chl on 2022/1/9.
 * description : 二叉树中的最大路径和
 *
 * 路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径至少包含一个节点，且不一定经过根节点。
 * 路径和是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 */
public class MaxPathSum {

    private int maxPath;

    public int maxPathSum(TreeNode root) {
        maxPath = root.val;
        int left = dfs(root.left);
        int right = dfs(root.right);
        //不包含节点时的最大值
        maxPath = Math.max(maxPath, left);
        maxPath = Math.max(maxPath, right);
        //包含当前节点最大值
        if (left != Integer.MIN_VALUE) {
            maxPath = Math.max(maxPath, root.val + left);
        }
        if (right != Integer.MIN_VALUE) {
            maxPath = Math.max(maxPath, root.val + right);
        }
        if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE) {
            maxPath = Math.max(maxPath, root.val + left + right);
        }
        return maxPath;
    }

    //深度遍历包含当前节点的最大值
    private int dfs(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int left = dfs(root.left);
        maxPath = Math.max(maxPath, left);
        int right = dfs(root.right);
        maxPath = Math.max(maxPath, right);
        //防止int的取值范围越界.比如:Integer.MIN_VALUE+(负数)会变成较大的正数
        if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE) {
            maxPath = Math.max(maxPath, root.val + left + right);
        }
        int max = root.val;
        if (left != Integer.MIN_VALUE) {
            max = Math.max(max, root.val + left);
        }
        if (right != Integer.MIN_VALUE) {
            max = Math.max(max, root.val + right);
        }
        return max;
    }
}
