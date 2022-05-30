package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2022/5/30
 * @author chenglong
 * description : 从根到叶的二进制数之和
 *
 * 给出一棵二叉树，其上每个结点的值都是0或1。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数01101，也就是13。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 返回这些数字之和。题目数据保证答案是一个32位整数。
 *
 * 示例 1：
 * 输入：root = [1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * 示例 2：
 * 输入：root = [0]
 * 输出：0
 *
 * 提示：
 * 树中的节点数在[1, 1000]范围内
 * Node.val仅为0或1
 */
public class SumRootToLeaf {

    private int sum;

    public int sumRootToLeaf(TreeNode root) {
        sum = 0;
        dfs(0, root);
        return sum;
    }

    private void dfs(int base, TreeNode root) {
        base = base * 2 + root.val;
        if (root.left == null && root.right == null) {
            //当前节点为叶子节点
            sum += base;
        } else {
            if (root.left != null) {
                dfs(base, root.left);
            }
            if (root.right != null) {
                dfs(base, root.right);
            }
        }
    }
}
