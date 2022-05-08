package com.demo.algorithm.leetcode.contest.week292;

/**
 * Created by chl on 2022/5/8.
 * description :
 */
public class AverageOfSubtree {

    private int total = 0;

    public int averageOfSubtree(TreeNode root) {
        total = 0;
        dfs(root);
        return total;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int count = left[0] + right[0] + 1;
        int sum = left[1] + right[1] + root.val;
        if (sum / count == root.val) {
            total++;
        }
        return new int[]{count, sum};
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
