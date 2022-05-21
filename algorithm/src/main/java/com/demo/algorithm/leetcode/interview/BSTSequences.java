package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题04.09. 二叉搜索树序列
 *
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 * 给定一个由不同节点组成的二叉搜索树root，输出所有可能生成此树的数组。
 *
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: [[2,1,3],[2,3,1]]
 * 解释: 数组 [2,1,3]、[2,3,1] 均可以通过从左向右遍历元素插入树中形成以下二叉搜索树
 *       2
 *      / \
 *     1   3
 *
 * 示例2:
 * 输入: root = [4,1,null,null,3,2]
 * 输出: [[4,1,3,2]]
 *
 * 提示：
 * 二叉搜索树中的节点数在[0, 1000]的范围内
 * 1<=节点值<= 10^6
 * 用例保证符合要求的数组数量不超过5000
 */
public class BSTSequences {

    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            List<Integer> items = new ArrayList<>();
            result.add(items);
            return result;
        }
        //分层遍历bfs
        List<Integer> path = new ArrayList<>();
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        bfs(dates, path, result);
        return result;
    }

    private void bfs(List<TreeNode> dates, List<Integer> path, List<List<Integer>> result) {
        if (dates.isEmpty()) {
            List<Integer> items = new ArrayList<>(path);
            result.add(items);
            return;
        }
        List<TreeNode> copy = new ArrayList<>(dates);
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = dates.get(i);
            path.add(node.val);
            dates.remove(node);
            if (node.left != null) {
                dates.add(node.left);
            }
            if (node.right != null) {
                dates.add(node.right);
            }
            bfs(dates, path, result);
            path.remove(path.size() - 1);
            dates = new ArrayList<>(copy);
        }
    }

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }
}
