package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/6/22
 * @author chenglong
 * description : 找树左下角的值
 *
 * 给定一个二叉树的根节点root，请找出该二叉树的最底层最左边节点的值。
 * 假设二叉树中至少有一个节点。
 *
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *
 * 提示:
 * 二叉树的节点个数的范围是 [1,10^4]
 * -2^31<= Node.val <= 2^31- 1
 */
public class FindBottomLeftValue {

    public int findBottomLeftValue(TreeNode root) {
        //使用分层遍历bfs
        List<TreeNode> cur = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        cur.add(root);
        while (cur.size() > 0) {
            for (int i = 0; i < cur.size(); i++) {
                TreeNode node = cur.get(i);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            if (next.size() == 0) {
                return cur.get(0).val;
            }
            cur.clear();
            cur.addAll(next);
            next.clear();
        }
        return 0;
    }
}
