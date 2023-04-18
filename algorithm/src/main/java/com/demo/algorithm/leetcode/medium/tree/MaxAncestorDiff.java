package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/4/18
 * @author chenglong
 * description : 节点与其祖先之间的最大差值
 *
 * 给定二叉树的根节点root，找出存在于不同节点A和B之间的最大值V，其中V = |A.val - B.val|，且A是B的祖先。
 *（如果A的任何子节点之一为B，或者A的任何子节点是B的祖先，那么我们认为A是B的祖先）
 *
 * 示例 1：
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 *
 * 示例 2：
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 *
 * 提示：
 * 树中的节点数在2到5000之间。
 * 0 <= Node.val <= 10^5
 */
public class MaxAncestorDiff {

    private int maxSpace = 0;

    public int maxAncestorDiff(TreeNode root) {
        maxSpace = 0;
        dfs(root);
        return maxSpace;
    }

    private void dfs(TreeNode root) {
        //空节点或叶子节点结束
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        //root为A节点
        if (root.left != null) {
            int min = dfsMin(root.left);
            int max = dfsMax(root.left);
            int space = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
            if (space > maxSpace) {
                maxSpace = space;
            }
            dfs(root.left);
        }
        if (root.right != null) {
            int min = dfsMin(root.right);
            int max = dfsMax(root.right);
            int space = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
            if (space > maxSpace) {
                maxSpace = space;
            }
            dfs(root.right);
        }
    }

    private int dfsMax(TreeNode root) {
        int max = root.val;
        if (root.left != null) {
            int left = dfsMax(root.left);
            if (left > max) {
                max = left;
            }
        }
        if (root.right != null) {
            int right = dfsMax(root.right);
            if (right > max) {
                max = right;
            }
        }
        return max;
    }

    private int dfsMin(TreeNode root) {
        int min = root.val;
        if (root.left != null) {
            int left = dfsMin(root.left);
            if (left < min) {
                min = left;
            }
        }
        if (root.right != null) {
            int right = dfsMin(root.right);
            if (right < min) {
                min = right;
            }
        }
        return min;
    }
}
