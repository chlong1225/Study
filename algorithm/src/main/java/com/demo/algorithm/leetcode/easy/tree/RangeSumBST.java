package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create by chenglong on 2023/12/10
 * description : 二叉搜索树的范围和
 *
 * 给定二叉搜索树的根结点root，返回值位于范围[low,high]之间的所有结点的值的和。
 *
 * 示例 1：
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 *
 * 示例 2：
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 2 * 10^4] 内
 * 1 <= Node.val <= 10^5
 * 1 <= low <= high <= 10^5
 * 所有Node.val互不相同
 */
public class RangeSumBST {

    private int min;
    private int max;
    private int sum;

    public int rangeSumBST(TreeNode root, int low, int high) {
        min = low;
        max = high;
        sum = 0;
        dfs(root);
        return sum;
    }

    private void dfs(TreeNode root) {
        if (root.val >= min) {
            if (root.val <= max) {
                sum += root.val;
                if (root.left != null) {
                    dfs(root.left);
                }
                if (root.right != null) {
                    dfs(root.right);
                }
            } else {
                //此时右边的节点值都会大于max，无效
                if (root.left != null) {
                    dfs(root.left);
                }
            }
        } else {
            //此时左边节点的值都会小于min，无效
            if (root.right != null) {
                dfs(root.right);
            }
        }
    }
}
