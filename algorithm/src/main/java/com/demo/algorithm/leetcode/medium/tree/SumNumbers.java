package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 求根节点到叶节点数字之和
 *
 * 给你一个二叉树的根节点root，树中每个节点都存放有一个0到9之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径1 -> 2 -> 3 表示数字123。
 * 计算从根节点到叶节点生成的所有数字之和。
 * 叶节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 *
 * 示例 2：
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 *
 * 提示：
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 */
public class SumNumbers {

    private final List<Integer> dates = new ArrayList<>();

    public int sumNumbers(TreeNode root) {
        dates.clear();
        //1，使用深度搜索dfs获取所有组合的数字
        dfs(0, root);
        //2，对数字进行求和
        int sum = 0;
        for (int i = 0; i < dates.size(); i++) {
            sum += dates.get(i);
        }
        return sum;
    }

    private void dfs(int sum, TreeNode root) {
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            //当前为叶子节点
            dates.add(sum);
        } else {
            if (root.left != null) {
                dfs(sum, root.left);
            }
            if (root.right != null) {
                dfs(sum, root.right);
            }
        }
    }
}
