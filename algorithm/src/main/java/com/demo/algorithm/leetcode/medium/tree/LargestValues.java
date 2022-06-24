package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/6/24
 * @author chenglong
 * description : 在每个树行中找最大值
 *
 * 给定一棵二叉树的根节点root，请找出该二叉树中每一层的最大值。
 *
 * 示例1：
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 *
 * 示例2：
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 * 提示：
 * 二叉树的节点个数的范围是 [0,10^4]
 * -2^31<= Node.val <= 2^31- 1
 */
public class LargestValues {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> dates = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        dates.add(root);
        result.add(root.val);
        while (dates.size() > 0) {
            int max = 0;
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if (node.left != null) {
                    next.add(node.left);
                    if (next.size() == 1) {
                        max = next.get(0).val;
                    } else {
                        int last = next.get(next.size() - 1).val;
                        if (last > max) {
                            max = last;
                        }
                    }
                }
                if (node.right != null) {
                    next.add(node.right);
                    if (next.size() == 1) {
                        max = next.get(0).val;
                    } else {
                        int last = next.get(next.size() - 1).val;
                        if (last > max) {
                            max = last;
                        }
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            if (next.size() > 0) {
                result.add(max);
            }
            next.clear();
        }
        return result;
    }
}
