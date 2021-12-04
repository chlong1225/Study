package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 12/4/21
 * @author chenglong
 * description : 二叉树的层序遍历II
 *
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrder2 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //1，二叉树层序遍历
        List<TreeNode> datas = new ArrayList<>();
        datas.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (datas.size() > 0) {
            List<Integer> tem = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                TreeNode node = datas.get(i);
                tem.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            result.add(tem);
            datas.clear();
            datas.addAll(next);
            next.clear();
        }
        //2，list反转
        Collections.reverse(result);
        return result;
    }
}
