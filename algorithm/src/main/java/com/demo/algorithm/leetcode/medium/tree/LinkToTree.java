package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.ListNode;
import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 12/6/21
 * @author chenglong
 * description : 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class LinkToTree {

    private List<Integer> datas = new ArrayList<>();

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        //1，使用list存储链表节点数据
        ListNode p = head;
        datas.clear();
        while (p != null) {
            datas.add(p.val);
            p = p.next;
        }
        int start = 0;
        int end = datas.size() - 1;
        int middle = (start + end) >> 1;
        TreeNode root = new TreeNode(datas.get(middle));
        root.left = buildTree(start, middle - 1);
        root.right = buildTree(middle + 1, end);
        return root;
    }

    //构建节点
    private TreeNode buildTree(int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) >> 1;
        TreeNode root = new TreeNode(datas.get(middle));
        root.left = buildTree(start, middle - 1);
        root.right = buildTree(middle + 1, end);
        return root;
    }
}
