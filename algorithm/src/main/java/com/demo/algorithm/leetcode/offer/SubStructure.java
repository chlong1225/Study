package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *            3
 *           / \
 *          4  5
 *         / \
 *        1  2
 * 给定的树 B：
 *
 *      4
 *     /
 *    1
 * 返回 true，因为B与A的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 * 限制：
 * 0 <= 节点个数 <= 10000
 */
public class SubStructure {

    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        //1，分层遍历a查找与b根节点值相同的节点
        List<TreeNode> dates = new ArrayList<>();
        dates.add(a);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if (node.val == b.val) {
                    //2，判断node节点开始是否包含b节点
                    if (isContain(node, b)) {
                        return true;
                    }
                }
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return false;
    }

    private boolean isContain(TreeNode node, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (node == null) {
            return false;
        }
        return (node.val == b.val) && isContain(node.left, b.left) && isContain(node.right, b.right);
    }
}
