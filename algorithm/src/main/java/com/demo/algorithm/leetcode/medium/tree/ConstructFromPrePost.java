package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2024/2/22
 * @author chenglong
 * description : 根据前序和后序遍历构造二叉树
 *
 * 给定两个整数数组，preorder和postorder，其中preorder是一个具有无重复值的二叉树的前序遍历，postorder是同一棵树的后序遍历，重构并返回二叉树。
 * 如果存在多个答案，您可以返回其中任何一个。
 *
 * 示例 1：
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *
 * 示例 2:
 * 输入: preorder = [1], postorder = [1]
 * 输出: [1]
 *
 * 提示：
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * preorder中所有值都不同
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * postorder中所有值都不同
 * 保证preorder和postorder是同一棵二叉树的前序遍历和后序遍历
 */
public class ConstructFromPrePost {

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        TreeNode root = new TreeNode(preorder[0]);
        dfs(root, 1, n - 1, preorder, 0, n - 2, postorder);
        return root;
    }

    private void dfs(TreeNode root, int preStart, int preEnd, int[] preorder, int postStart, int postEnd, int[] postorder) {
        if (preStart > preEnd) {
            return;
        }
        if (preorder[preStart] != postorder[postEnd]) {
            //此时preStart对应左节点，postEnd对应右节点
            TreeNode left = new TreeNode(preorder[preStart]);
            //计算左边节点后序遍历对应的区间
            int preSpace = -1;
            for (int i = preStart + 1; i <= preEnd; i++) {
                if (preorder[i] == postorder[postEnd]) {
                    preSpace = i;
                    break;
                }
            }
            int postSpace = -1;
            for (int i = postStart; i <= postEnd; i++) {
                if (preorder[preStart] == postorder[i]) {
                    postSpace = i;
                    break;
                }
            }
            dfs(left, preStart + 1, preSpace - 1, preorder, postStart, postSpace - 1, postorder);
            TreeNode right = new TreeNode(postorder[postEnd]);
            dfs(right, preSpace + 1, preEnd, preorder, postSpace + 1, postEnd - 1, postorder);
            root.left = left;
            root.right = right;
        } else {
            //此时只有左边节点或右边节点
            TreeNode left = new TreeNode(preorder[preStart]);
            root.left = left;
            dfs(left, preStart + 1, preEnd, preorder, postStart, postEnd - 1, postorder);
        }
    }
}
