package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/17
 * @author chenglong
 * description : 层数最深叶子节点的和
 *
 * 给你一棵二叉树的根节点root，请你返回层数最深的叶子节点的和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 *
 * 示例 2：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 *
 * 提示：
 * 树中节点数目在范围 [1, 10^4]之间。
 * 1 <= Node.val <= 100
 */
public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {
        List<TreeNode> dates = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        dates.add(root);
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            if (next.isEmpty()) {
                //此时dates存的是最后一层
                int sum = 0;
                for (int i = 0; i < dates.size(); i++) {
                    sum += dates.get(i).val;
                }
                return sum;
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return 0;
    }
}
