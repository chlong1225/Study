package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/7.
 * description : 不同的二叉搜索树II
 *
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *  
 * 提示：
 * 1 <= n <= 8
 */
public class TreeNum2 {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        if (n == 1) {
            TreeNode root = new TreeNode(1);
            result.add(root);
            return result;
        }
        result = buildTree(1, n);
        return result;
    }

    private List<TreeNode> buildTree(int start, int end) {
        if (start > end) {
            return null;
        }
        List<TreeNode> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = buildTree(start, i - 1);
            List<TreeNode> rights = buildTree(i + 1, end);
            if (lefts == null || lefts.size() == 0) {

                if (rights == null || rights.size() == 0) {
                    TreeNode root = new TreeNode(i);
                    root.left = null;
                    root.right = null;
                    result.add(root);
                } else {
                    for (int j = 0; j < rights.size(); j++) {
                        TreeNode root = new TreeNode(i);
                        root.left = null;
                        root.right = rights.get(j);
                        result.add(root);
                    }
                }
            } else {
                for (int j = 0; j < lefts.size(); j++) {
                    if (rights == null || rights.size() == 0) {
                        TreeNode root = new TreeNode(i);
                        root.left = lefts.get(j);
                        root.right = null;
                        result.add(root);
                    } else {
                        for (int k = 0; k < rights.size(); k++) {
                            TreeNode root = new TreeNode(i);
                            root.left = lefts.get(j);
                            root.right = rights.get(k);
                            result.add(root);
                        }
                    }
                }
            }
        }
        return result;
    }
}
