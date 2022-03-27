package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer54. 二叉搜索树的第k大节点
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点的值。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *   2
 * 输出: 4
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class KthLargest {

    private int result;
    private int count;

    public int kthLargest(TreeNode root, int k) {
        count = k;
        dfs(root);
        return result;
    }

    //反着的中序遍历
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            dfs(root.right);
        }
        count--;
        if (count == 0) {
            result = root.val;
            return;
        }
        if (count < 0) {
            return;
        }
        if (root.left != null) {
            dfs(root.left);
        }
    }
}
