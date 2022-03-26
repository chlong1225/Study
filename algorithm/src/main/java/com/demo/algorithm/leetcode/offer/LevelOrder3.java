package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chl on 2022/3/26.
 * description :  剑指Offer32-III. 从上到下打印二叉树III
 *
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 提示：
 * 节点总数 <= 1000
 */
public class LevelOrder3 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> dates = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        dates.add(root);
        boolean isLeft = true;
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
            dates.clear();
            dates.addAll(next);
            next.clear();
            if (isLeft) {
                isLeft = false;
                result.add(items);
            } else {
                isLeft = true;
                Collections.reverse(items);
                result.add(items);
            }
        }
        return result;
    }
}
