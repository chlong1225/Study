package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/12/6
 * @author chenglong
 * description : 二叉搜索树的最小绝对差
 *
 * 给你一个二叉搜索树的根节点root，返回树中任意两不同节点值之间的最小差值。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 * 示例 1：
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 *
 * 示例 2：
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 * 提示：
 * 树中节点的数目范围是 [2, 10^4]
 * 0 <= Node.val <= 10^5
 */
public class MinimumDifference {

    private int pre = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    private void dfs(TreeNode root){
        if (root.left != null) {
            dfs(root.left);
        }
        if (pre != Integer.MIN_VALUE) {
            int tem = root.val - pre;
            if (min > tem) {
                min = tem;
            }
        }
        pre = root.val;
        if (root.right != null) {
            dfs(root.right);
        }
    }
}
