package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/9.
 * description : 二叉树展开为链表
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 *
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */
public class TreeToLink {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> datas = new ArrayList<>();
        dfs(root, datas);
        for (int i = 1; i < datas.size(); i++) {
            TreeNode pre = datas.get(i - 1);
            pre.left = null;
            pre.right = datas.get(i);
        }
    }

    private void dfs(TreeNode root, List<TreeNode> datas) {
        if (root == null) {
            return;
        }
        datas.add(root);
        dfs(root.left, datas);
        dfs(root.right, datas);
    }
}
