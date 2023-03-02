package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/3/2
 * @author chenglong
 * description : 二叉树的所有路径
 *
 * 给你一个二叉树的根节点root，按任意顺序，返回所有从根节点到叶子节点的路径。
 * 叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：["1"]
 *
 * 提示：
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(builder.toString());
        } else {
            if (root.left != null) {
                dfs(builder, root.left, result);
            }
            if (root.right != null) {
                dfs(builder, root.right, result);
            }
        }
        return result;
    }

    private void dfs(StringBuilder pre, TreeNode node, List<String> result) {
        StringBuilder builder = new StringBuilder(pre);
        builder.append("->");
        builder.append(node.val);
        if (node.left == null && node.right == null) {
            result.add(builder.toString());
        } else {
            if (node.left != null) {
                dfs(builder, node.left, result);
            }
            if (node.right != null) {
                dfs(builder, node.right, result);
            }
        }
    }
}
