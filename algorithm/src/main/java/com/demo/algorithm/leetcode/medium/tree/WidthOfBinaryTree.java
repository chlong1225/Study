package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/28
 * @author chenglong
 * description : 二叉树最大宽度
 *
 * 给你一棵二叉树的根节点root，返回树的最大宽度 。
 * 树的最大宽度是所有层中最大的宽度。
 * 每一层的宽度被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的null节点，这些null节点也计入长度。
 * 题目数据保证答案将会在32位带符号整数范围内。
 *
 * 示例 1：
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 *
 * 示例 2：
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 *
 * 示例 3：
 * 输入：root = [1,3,2,5]
 * 输出：2
 * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
 *
 * 提示：
 * 树中节点的数目范围是 [1, 3000]
 * -100 <= Node.val <= 100
 */
public class WidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 1;
        List<TreeNode> dates = new ArrayList<>();
        root.val = 1;
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            if (dates.size() > 1) {
                int space = dates.get(dates.size() - 1).val - dates.get(0).val + 1;
                if (space > max) {
                    max = space;
                }
            }
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if (node.left != null) {
                    node.left.val = node.val * 2 - 1;
                    next.add(node.left);
                }
                if (node.right != null) {
                    node.right.val = node.val * 2;
                    next.add(node.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return max;
    }
}
