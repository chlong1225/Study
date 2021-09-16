package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create by chenglong on 9/8/21
 * description : 将有序数组转换为二叉搜索树
 *
 *  给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *  高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 */
public class ArrayToTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int max = nums.length - 1;
        int min = 0;
        int middle = (min + max) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        buildRootLeft(root, nums, min, middle - 1);
        buildRootRight(root, nums, middle + 1, max);
        return root;
    }

    private static void buildRootRight(TreeNode root, int[] nums, int start, int end) {
        if (start <= end) {
            int middle = (start + end) / 2;
            root.right = new TreeNode(nums[middle]);
            buildRootLeft(root.right, nums, start, middle - 1);
            buildRootRight(root.right, nums, middle + 1, end);
        }
    }

    private static void buildRootLeft(TreeNode root, int[] nums, int start, int end) {
        if (start <= end) {
            int middle = (start + end) / 2;
            root.left = new TreeNode(nums[middle]);
            buildRootLeft(root.left, nums, start, middle - 1);
            buildRootRight(root.left, nums, middle + 1, end);
        }
    }

}
