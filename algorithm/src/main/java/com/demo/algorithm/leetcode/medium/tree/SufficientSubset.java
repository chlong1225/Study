package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/5/24
 * @author chenglong
 * description : 根到叶路径上的不足节点
 *
 * 给你二叉树的根节点root和一个整数limit，请你同时删除树中所有不足节点，并返回最终二叉树的根节点。
 * 假如通过节点node的每种可能的“根-叶”路径上值的总和全都小于给定的limit，则该节点被称之为不足节点，需要被删除。
 * 叶子节点，就是没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * 输出：[1,2,3,4,null,null,7,8,9,null,14]
 *
 * 示例 2：
 * 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 * 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 *
 * 示例 3：
 * 输入：root = [1,2,-3,-5,null,4,null], limit = -1
 * 输出：[1,null,-3,4]
 *
 * 提示：
 * 树中节点数目在范围 [1, 5000] 内
 * -10^5 <= Node.val <= 10^5
 * -10^9 <= limit <= 10^9
 */
public class SufficientSubset {

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit);
    }

    private TreeNode dfs(TreeNode root, int limit) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            //当前为叶子节点
            if (root.val < limit) {
                return null;
            }
        } else if (root.left == null) {
            root.right = dfs(root.right, limit - root.val);
            if (root.right == null) {
                return null;
            }
        } else if (root.right == null) {
            root.left = dfs(root.left, limit - root.val);
            if (root.left == null) {
                return null;
            }
        } else {
            root.left = dfs(root.left, limit - root.val);
            root.right = dfs(root.right, limit - root.val);
            if (root.left == null && root.right == null) {
                return null;
            }
        }
        return root;
    }
}
