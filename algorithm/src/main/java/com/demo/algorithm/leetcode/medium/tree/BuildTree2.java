package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * Created by chl on 2021/11/15.
 * description : 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BuildTree2 {

    private int[] mInorder;
    private int[] mPostorder;
    private int mPosIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        mInorder = inorder;
        mPostorder = postorder;
        int length = mPostorder.length;
        mPosIndex = length - 1;
        TreeNode root = new TreeNode(mPostorder[mPosIndex--]);
        int middle = 0;
        for (int i = 0; i < length; i++) {
            if (inorder[i] == root.val) {
                middle = i;
                break;
            }
        }
        root.right = buildTree(middle + 1, length - 1);
        root.left = buildTree(0, middle - 1);
        return root;
    }

    /**
     * @param middleStart : 中序遍历中的起始位置
     * @param middleEnd : 中序遍历中的结束位置
     * @return : 构建子树
     */
    private TreeNode buildTree(int middleStart, int middleEnd) {
        if (middleStart > middleEnd) {
            return null;
        }
        if (mPosIndex < 0) {
            return null;
        }
        TreeNode root = new TreeNode(mPostorder[mPosIndex--]);
        int middle = 0;
        for (int i = middleStart; i <= middleEnd; i++) {
            if (mInorder[i] == root.val) {
                middle = i;
                break;
            }
        }
        root.right = buildTree(middle + 1, middleEnd);
        root.left = buildTree(middleStart, middle - 1);
        return root;
    }
}
