package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/5/30
 * @author chenglong
 * description : 删点成林
 *
 * 给出二叉树的根节点root，树上每个节点都有一个不同的值。
 * 如果节点值在to_delete中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 *
 * 示例 2：
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 *
 * 提示：
 * 树中的节点数最大为1000。
 * 每个节点都有一个介于1到1000之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete包含一些从1到1000、各不相同的值。
 */
public class DelNodes {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        boolean[] marks = new boolean[1001];
        for (int i = 0; i < to_delete.length; i++) {
            marks[to_delete[i]] = true;
        }
        List<TreeNode> result = new ArrayList<>();
        dfs(root, true, marks, result);
        return result;
    }

    private TreeNode dfs(TreeNode root, boolean isRoot, boolean[] marks, List<TreeNode> result) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left = dfs(root.left, marks[root.val], marks, result);
        }
        if (root.right != null) {
            root.right = dfs(root.right, marks[root.val], marks, result);
        }
        if (marks[root.val]) {
            return null;
        } else {
            if (isRoot) {
                result.add(root);
            }
            return root;
        }
    }
}
