package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer34. 二叉树中和为某一值的路径
 *
 * 给你二叉树的根节点root和一个整数目标和targetSum ，找出所有从根节点到叶子节点 路径总和等于给定目标和的路径。
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

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, result, target);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> path, List<List<Integer>> result, int target) {
        if (root == null) {
            return;
        }
        target -= root.val;
        if (root.left == null && root.right == null) {
            //root为叶子节点
            if (target == 0) {
                List<Integer> item = new ArrayList<>(path);
                item.add(root.val);
                result.add(item);
            }
        } else {
            if (root.left != null) {
                path.add(root.val);
                dfs(root.left, path, result, target);
                path.remove((path.size() - 1));
            }
            if (root.right != null) {
                path.add(root.val);
                dfs(root.right, path, result, target);
                path.remove((path.size() - 1));
            }
        }
    }
}
