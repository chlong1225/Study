package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 从二叉搜索树到更大和树
 *
 * 给定一个二叉搜索树root(BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键小于节点键的节点。
 * 节点的右子树仅包含键大于节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *
 * 示例 1：
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * 示例 2：
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 *
 * 提示：
 * 树中的节点数在 [1, 100] 范围内。
 * 0 <= Node.val <= 100
 * 树中的所有值均 不重复 。
 */
public class BstToGst {

    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        sum = 0;
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}
