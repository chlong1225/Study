package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer36. 二叉搜索树与双向链表
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class TreeToDoublyList {

    private Node head;
    private Node p;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        dfs(root);
        p.right = head;
        head.left = p;
        return head;
    }

    //中序遍历进行排序
    private void dfs(Node root) {
        if (root.left != null) {
            dfs(root.left);
        }
        if (head == null) {
            head = root;
            p = head;
        } else {
            p.right = root;
            root.left = p;
            p = p.right;
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }

    public static class Node {

        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;
}
