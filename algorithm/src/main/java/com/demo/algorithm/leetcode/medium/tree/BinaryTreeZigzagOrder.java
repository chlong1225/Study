package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chl on 2021/11/14.
 * description : 二叉树的锯齿形层序遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class BinaryTreeZigzagOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> roots = new ArrayList<>();
        roots.add(root);
        boolean isFirst = true;
        while (roots.size() > 0) {
            List<TreeNode> next = new ArrayList<>();
            List<Integer> datas = new ArrayList<>();
            int size = roots.size();
            for (int i = 0; i < size; i++) {
                TreeNode tem = roots.get(i);
                datas.add(tem.val);
                if (tem.left != null) {
                    next.add(tem.left);
                }
                if (tem.right != null) {
                    next.add(tem.right);
                }
            }
            if (!isFirst) {
                Collections.reverse(datas);
            }
            result.add(datas);
            isFirst = !isFirst;
            roots.clear();
            roots.addAll(next);
            next.clear();
        }
        return result;
    }
}
