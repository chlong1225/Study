package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 12/4/21
 * @author chenglong
 * description : 路径总和II
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *  
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSum {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        /**
         * 分析：这是个标准的深度遍历dfs
         */
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, path, result);
        return result;
    }

    private void dfs(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> result) {
        int num = targetSum - root.val;
        if (root.left == null && root.right == null) {
            //当前root为根结点
            if (num == 0) {
                List<Integer> tem = new ArrayList<>(path);
                tem.add(root.val);
                result.add(tem);
            }
            return;
        }
        if (root.left != null) {
            path.add(root.val);
            dfs(root.left, num, path, result);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.val);
            dfs(root.right, num, path, result);
            path.remove(path.size() - 1);
        }
    }
}
