package com.demo.algorithm.leetcode.easy.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/3/10
 * @author chenglong
 * description : N叉树的前序遍历
 *
 * 给定一个n叉树的根节点root，返回其节点值的前序遍历 。
 * n叉树在输入中按层序遍历进行序列化表示，每组子节点由空值null分隔（请参见示例）。
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 * 提示：
 * 节点总数在范围[0, 10^4]内
 * 0 <= Node.val <= 10^4
 * n叉树的高度小于或等于1000
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 */
public class PreOrder {

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        if (root.children != null && root.children.size() > 0) {
            for (int i = 0; i < root.children.size(); i++) {
                dfs(root.children.get(i), result);
            }
        }
    }


    class Node {
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
    }
}
