package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 11/26/21
 * @author chenglong
 * description : 二叉搜索树中的搜索
 *
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 例如，
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class SearchNode {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        //使用广度优先bfs
        List<TreeNode> datas = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        datas.add(root);
        while (!datas.isEmpty()) {
            int size = datas.size();
            for (int i = 0; i < size; i++) {
                TreeNode tem = datas.get(i);
                if (tem.val == val) {
                    return tem;
                }
                if (tem.left != null) {
                    next.add(tem.left);
                }
                if (tem.right != null) {
                    next.add(tem.right);
                }
            }
            datas.clear();
            datas.addAll(next);
            next.clear();
        }
        return null;
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        //使用深度优先dfs
        return dfs(root, val);
    }

    //借助二叉搜索树数的特点：左子树节点小于根结点； 右子树节点大于根结点
    private TreeNode dfs(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return dfs(root.left, val);
        }
        return dfs(root.right, val);
    }
}
