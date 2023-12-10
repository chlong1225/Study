package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/12/7
 * @author chenglong
 * description : 另一棵树的子树
 *
 * 给你两棵二叉树root和subRoot。检验root中是否包含和subRoot具有相同结构和节点值的子树。如果存在，返回true；否则，返回false。
 * 二叉树tree的一棵子树包括tree的某个节点和这个节点的所有后代节点。tree也可以看做它自身的一棵子树。
 *
 * 示例 1：
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 *
 * 提示：
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -104 <= root.val <= 10^4
 * -104 <= subRoot.val <= 10^4
 */
public class Subtree {

    private boolean hasFind;

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        hasFind = false;
        dfs(root, subRoot);
        return hasFind;
    }

    private void dfs(TreeNode root, TreeNode subRoot) {
        if (root.val == subRoot.val) {
            //此时进行比对
            hasFind = true;
            compareTree(subRoot, root);
        }
        if (!hasFind && root.left != null) {
            dfs(root.left, subRoot);
        }
        if (!hasFind && root.right != null) {
            dfs(root.right, subRoot);
        }
    }

    private void compareTree(TreeNode subRoot, TreeNode root) {
        if (root.val != subRoot.val) {
            hasFind = false;
            return;
        }
        if (subRoot.left == null) {
            if (root.left != null) {
                hasFind = false;
            }
        } else {
            if (root.left == null) {
                hasFind = false;
                return;
            }
            compareTree(subRoot.left, root.left);
        }
        if (subRoot.right == null) {
            if (root.right != null) {
                hasFind = false;
            }
        } else {
            if (root.right == null) {
                hasFind = false;
                return;
            }
            compareTree(subRoot.right, root.right);
        }
    }
}
