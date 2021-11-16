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
    private int mMiddleIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        mInorder = inorder;
        mPostorder = postorder;
        int length = mPostorder.length;
        mPosIndex = length - 1;
        mMiddleIndex = length - 1;
        TreeNode root = new TreeNode(mPostorder[mPosIndex--]);
        root.right = buildTree(root.val);
        root.left = buildTree(Integer.MIN_VALUE);
        return root;
    }

    /**
     * @param stop : 限定当前递归结束
     * @return : 构建子树
     */
    private TreeNode buildTree(int stop) {
        if (mPosIndex < 0) {
            return null;
        }
        if (stop == mInorder[mMiddleIndex]) {
            mMiddleIndex--;
            return null;
        }
        TreeNode root = new TreeNode(mPostorder[mPosIndex--]);
        root.right = buildTree(root.val);
        root.left = buildTree(stop);
        return root;
    }
}
