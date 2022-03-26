package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer32-II. 从上到下打印二叉树 II
 *
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 提示：
 *
 * 节点总数 <= 1000
 */
public class LevelOrder2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                items.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            result.add(items);
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return result;
    }
}
