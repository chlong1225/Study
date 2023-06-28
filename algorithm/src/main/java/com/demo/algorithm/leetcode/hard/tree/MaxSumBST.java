package com.demo.algorithm.leetcode.hard.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * Created by chl on 2023/5/21.
 * description : 二叉搜索子树的最大键值和
 *
 * 给你一棵以root为根的二叉树，请你返回任意二叉搜索子树的最大键值和。
 * 二叉搜索树的定义如下：
 * 任意节点的左子树中的键值都小于此节点的键值。
 * 任意节点的右子树中的键值都大于此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 *
 * 示例 1：
 * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * 输出：20
 * 解释：键值为 3 的子树是和最大的二叉搜索树。
 *
 * 示例 2：
 * 输入：root = [4,3,null,1,2]
 * 输出：2
 * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
 *
 * 示例 3：
 * 输入：root = [-4,-2,-5]
 * 输出：0
 * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
 *
 * 示例 4：
 * 输入：root = [2,1,3]
 * 输出：6
 *
 * 示例 5：
 * 输入：root = [5,4,8,3,null,6,3]
 * 输出：7
 *
 * 提示：
 * 每棵树有1到40000个节点。
 * 每个节点的键值在[-4 * 10^4, 4 * 10^4] 之间。
 */
public class MaxSumBST {

    private int max;

    public int maxSumBST(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    //{最小值，最大值，总和，是否为二叉搜索树0,1}
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0, 0, 0};
        }
        if (root.left == null && root.right == null) {
            //此时为叶子节点
            if (root.val > max) {
                max = root.val;
            }
            return new int[]{root.val, root.val, root.val, 1};
        }
        if (root.left != null) {
            int[] lefts = dfs(root.left);
            if (root.right != null) {
                int[] rights = dfs(root.right);
                if (lefts[3] == 1 && rights[3] == 1 && lefts[1] < root.val && rights[0] > root.val) {
                    int sum = lefts[2] + rights[2] + root.val;
                    if (sum > max) {
                        max = sum;
                    }
                    return new int[]{lefts[0], rights[1], sum, 1};
                }
            } else {
                //此时left不为null，right为null
                if (lefts[3] == 1 && lefts[1] < root.val) {
                    int sum = lefts[2] + root.val;
                    if (sum > max) {
                        max = sum;
                    }
                    return new int[]{lefts[0], root.val, sum, 1};
                }
            }
        } else {
            //此时left为null，right不为空
            int[] rights = dfs(root.right);
            if (rights[3] == 1 && root.val < rights[0]) {
                //右边节点是二叉搜索树且此时root+右边构成二叉搜索树
                int sum = root.val + rights[2];
                if (sum > max) {
                    max = sum;
                }
                return new int[]{root.val, rights[1], sum, 1};
            }
        }
        return new int[]{0, 0, 0, 0};
    }


}
