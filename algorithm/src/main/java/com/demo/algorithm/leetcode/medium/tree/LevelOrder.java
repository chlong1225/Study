package com.demo.algorithm.leetcode.medium.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/8.
 * description : N叉树的层序遍历
 *
 * 给定一个N叉树返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *
 * 提示：
 * 树的高度不会超过1000
 * 树的节点总数在 [0,10^4] 之间
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Node> dates = new ArrayList<>();
        dates.add(root);
        List<Node> next = new ArrayList<>();
        while (dates.size() > 0) {
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < dates.size(); i++) {
                items.add(dates.get(i).val);
                List<Node> children = dates.get(i).children;
                if (children != null && children.size() > 0) {
                    for (int j = 0; j < children.size(); j++) {
                        if (children.get(j) != null) {
                            next.add(children.get(j));
                        }
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
            result.add(items);
        }
        return result;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
