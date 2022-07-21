package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2022/7/2
 * @author chenglong
 * description : 二叉树剪枝
 *
 * 给你二叉树的根结点root，此外树的每个结点的值要么是0，要么是1。
 *
 * 返回移除了所有不包含1的子树的原二叉树。
 * 节点node的子树为node本身加上所有node的后代。
 *
 * 示例 1：
 * 输入：root = [1,null,0,0,1]
 * 输出：[1,null,0,null,1]
 * 解释：
 * 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 *
 * 示例 2：
 * 输入：root = [1,0,1,0,0,0,1]
 * 输出：[1,null,1,null,1]
 *
 * 示例 3：
 * 输入：root = [1,1,0,1,1,0,1,0]
 * 输出：[1,1,0,1,1,null,1]
 *
 * 提示：
 * 树中节点的数目在范围 [1, 200]内
 * Node.val为0或1
 */
public class PruneTree {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            dfs(root,true);
        }
        if (root.right != null) {
            dfs(root, false);
        }
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    //搜索当前节点以及子节点是否有1
    private boolean dfs(TreeNode parent, boolean isLeft) {
        TreeNode child = isLeft ? parent.left : parent.right;
        boolean result = false;
        if (child.val == 1) {
            result = true;
            if (child.left != null) {
                dfs(child, true);
            }
            if (child.right != null) {
                dfs(child, false);
            }
        } else {
            //此时为0
            if (child.left != null) {
                result = dfs(child, true);
            }
            if (child.right != null) {
                boolean right = dfs(child, false);
                if (!result) {
                    result = right;
                }
            }
            if (!result) {
                //此时当前位置与子节点都为0需要都删除
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
        return result;
    }
}
