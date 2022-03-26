package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer28. 对称的二叉树
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *
 *          1
 *         / \
 *        2  2
 *      / \ / \
 *     3  4 4 3
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *           1
 *          / \
 *         2   2
 *          \  \
 *          3  3
 *
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 */
public class Symmetric {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root.left);
        dates.add(root.right);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            //1，判断dates数据是否对称
            int size = dates.size();
            int n = size >> 1;
            for (int i = 0; i < n; i++) {
                if (dates.get(i) == null) {
                    if (dates.get(size - 1 - i) != null) {
                        return false;
                    }
                } else {
                    if (dates.get(size - 1 - i) == null) {
                        return false;
                    }
                    if (dates.get(size - 1 - i).val != dates.get(i).val) {
                        return false;
                    }
                }
            }
            //2，获取下一层节点，同时记录非空节点数量
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = dates.get(i);
                if (node == null) {
                    next.add(null);
                    next.add(null);
                } else {
                    next.add(node.left);
                    next.add(node.right);
                    if (node.left != null) {
                        count++;
                    }
                    if (node.right != null) {
                        count++;
                    }
                }
            }
            //3，非空节点为0时结束循环，否则数据重置
            if (count == 0) {
                break;
            } else {
                dates.clear();
                dates.addAll(next);
                next.clear();
            }
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
