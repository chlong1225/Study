package com.demo.algorithm.leetcode.easy.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/3/12
 * @author chenglong
 * description : N叉树的后序遍历
 *
 * 给定一个n叉树的根节点root，返回其节点值的后序遍历 。
 * n叉树在输入中按层序遍历进行序列化表示，每组子节点由空值null分隔（请参见示例）。
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *
 * 提示：
 * 节点总数在范围 [0, 10^4] 内
 * 0 <= Node.val <= 10^4
 * n叉树的高度小于或等于1000
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 */
public class PostOrderNTree {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        List<Node> children = root.children;
        if (children != null && children.size() > 0) {
            for (int i = 0; i < children.size(); i++) {
                dfs(children.get(i), result);
            }
        }
        result.add(root.val);
    }

    public List<Integer> postorder2(Node root) {
        List<Integer> result = new ArrayList<>();
        List<Node> dates = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dates.add(root);
        while (dates.size() > 0) {
            Node node = dates.get(dates.size() - 1);
            List<Node> children = node.children;
            if (children == null || children.size() == 0) {
                result.add(node.val);
                dates.remove(dates.size() - 1);
            } else {
                node.children = null;
                for (int i = children.size() - 1; i >= 0; i--) {
                    dates.add(children.get(i));
                }
            }
        }
        return result;
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
    };
}
