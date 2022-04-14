package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * Created by chl on 2022/4/13.
 * description : 剑指Offer07. 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 */
public class BuildTree {

    private int[] mPreorder;
    private int[] mInorder;
    private int index;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if (length == 0) {
            return null;
        }
        mPreorder = preorder;
        mInorder = inorder;
        index = 0;
        TreeNode root = new TreeNode(mPreorder[index]);
        int middle = 0;
        for (int i = 0; i < length; i++) {
            if (mInorder[i] == mPreorder[index]) {
                middle = i;
                break;
            }
        }
        index++;
        root.left = dfs(0, middle - 1);
        root.right = dfs(middle + 1, length - 1);

        return root;
    }

    private TreeNode dfs(int start, int end) {
        if (start <= end) {
            TreeNode node = new TreeNode(mPreorder[index]);
            int middle = 0;
            for (int i = start; i <= end; i++) {
                if (mInorder[i] == mPreorder[index]) {
                    middle = i;
                    break;
                }
            }
            index++;
            node.left = dfs(start, middle - 1);
            node.right = dfs(middle + 1, end);
            return node;
        }
        return null;
    }
}
