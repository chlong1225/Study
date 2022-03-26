package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer32 - I. 从上到下打印二叉树
 *
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 * 提示：
 * 节点总数 <= 1000
 */
public class LevelOrder {

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> result = new ArrayList<>();
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                result.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
}
