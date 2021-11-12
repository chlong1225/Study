package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/10.
 * description : 恢复二叉搜索树
 *
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 示例 1：
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 *
 * 示例 2：
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *  
 * 提示：
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 */
public class RestoreBinarySearchTree {

    //记录需要被交换用来恢复的两个节点
    private TreeNode a;
    private TreeNode b;
    //用于标记上一个节点的值
    private List<TreeNode> mark = new ArrayList<>();

    public void recoverTree(TreeNode root) {
        //1,成员变量状态重置
        a = null;
        b = null;
        mark.clear();
        //2,中序遍历查询两个错误的节点
        dfs(root);
        //3,交换节点的值
        if (a != null && b != null) {
            int tem = a.val;
            a.val = b.val;
            b.val = tem;
        }
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (mark.isEmpty()) {
            mark.add(root);
        } else {
            //如果交换的是相邻两个节点，只会出现一次降序，最多两次
            if (mark.get(0).val > root.val) {
                if (a == null) {
                    a = mark.get(0);
                    b = root;
                } else {
                    b = root;
                    return;
                }
            }
            //更新上一个节点的保存
            mark.set(0, root);
        }
        dfs(root.right);
    }
}
