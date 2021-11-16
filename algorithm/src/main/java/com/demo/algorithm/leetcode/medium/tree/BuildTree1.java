package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * Created by chl on 2021/11/15.
 * description : 从前序与中序遍历序列构造二叉树
 *
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 *
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 */
public class BuildTree1 {

    private int[] mPreorder;
    private int[] mInorder;
    private int mPreIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        mPreorder = preorder;
        mInorder = inorder;
        mPreIndex = 0;
        TreeNode root = new TreeNode(preorder[mPreIndex]);
        mPreIndex++;
        int length = inorder.length;
        int middle = 0;
        for (int i = 0; i < length; i++) {
            if (inorder[i] == root.val) {
                middle = i;
                break;
            }
        }
        root.left = buildTree(0, middle - 1);
        root.right = buildTree(middle + 1, length - 1);
        return root;
    }

    /**
     * @param middleStart : 中序遍历中的起始位置
     * @param middleEnd : 中序遍历中的结尾位置
     * @return : 构建子树
     */
    private TreeNode buildTree(int middleStart, int middleEnd) {
        if (middleStart > middleEnd) {
            return null;
        }
        TreeNode root = new TreeNode();
        root.val = mPreorder[mPreIndex];
        mPreIndex++;
        int middle = 0;
        for (int i = middleStart; i <= middleEnd; i++) {
            if (mInorder[i] == root.val) {
                middle = i;
                break;
            }
        }
        root.left = buildTree(middleStart, middle - 1);
        root.right = buildTree(middle + 1, middleEnd);
        return root;
    }
}
