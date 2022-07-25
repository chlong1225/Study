package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/7/25.
 * description : 完全二叉树插入器
 *
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 *
 * 实现 CBTInserter 类:
 * CBTInserter(TreeNode root)使用头节点为root的给定树初始化该数据结构；
 * CBTInserter.insert(int v)向树中插入一个值为Node.val == val的新节点TreeNode。使树保持完全二叉树的状态，并返回插入节点TreeNode的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *
 * 示例 1：
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 *
 * 提示：
 * 树中节点数量范围为[1, 1000]
 * 0 <= Node.val <= 5000
 * root是完全二叉树
 * 0 <= val <= 5000
 * 每个测试用例最多调用insert和get_root操作10^4次
 *
 */
public class CBTInserter {

    //当前层最多节点数
    private int max;
    //记录上一层的节点
    private final List<TreeNode> pre = new ArrayList<>();
    //记录当前层的节点
    private final List<TreeNode> cur = new ArrayList<>();
    //根节点
    private TreeNode mRoot;

    public CBTInserter(TreeNode root) {
        mRoot = root;
        max = 1;
        pre.clear();
        cur.clear();
        if (root != null) {
            cur.add(root);
            while (cur.size() >= max) {
                max *= 2;
                pre.clear();
                pre.addAll(cur);
                cur.clear();
                for (int i = 0; i < pre.size(); i++) {
                    TreeNode node = pre.get(i);
                    if (node.left != null) {
                        cur.add(node.left);
                    }
                    if (node.right != null) {
                        cur.add(node.right);
                    }
                }
            }
        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);
        cur.add(node);
        int count = cur.size();
        int result = 0;
        if (count == max) {
            //最后一个节点
            TreeNode parent = pre.get(pre.size() - 1);
            parent.right = node;
            result = parent.val;
            pre.clear();
            pre.addAll(cur);
            cur.clear();
            max *= 2;
        } else {
            TreeNode parent = pre.get((count - 1) / 2);
            if (count % 2 == 0) {
                //右节点
                parent.right = node;
            } else {
                parent.left = node;
            }
            result = parent.val;
        }
        return result;
    }

    public TreeNode get_root() {
        return mRoot;
    }
}
