package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 2023/12/10
 * description : 二叉树的堂兄弟节点
 *
 * 在二叉树中，根节点位于深度0处，每个深度为k的节点的子节点位于深度k+1处。
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点root，以及树中两个不同节点的值x和y。
 * 只有与值x和y对应的节点是堂兄弟节点时，才返回true。否则，返回false。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 *
 * 示例 2：
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 *
 * 示例 3：
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *
 * 提示：
 * 二叉树的节点数介于2到100之间。
 * 每个节点的值都是唯一的、范围为1到100的整数。
 */
public class Cousins {

    public boolean isCousins(TreeNode root, int x, int y) {
        List<TreeNode> pre = new ArrayList<>();
        List<TreeNode> curs = new ArrayList<>();
        List<TreeNode> nexts = new ArrayList<>();
        pre.add(root);
        if (root.left != null) {
            curs.add(root.left);
        }
        if (root.right != null) {
            curs.add(root.right);
        }
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                TreeNode node = curs.get(i);
                boolean hasX = false;
                if (node.left != null) {
                    nexts.add(node.left);
                    if (node.left.val == x) {
                        hasX = true;
                    }
                }
                if (node.right != null) {
                    nexts.add(node.right);
                    if (node.right.val == x) {
                        hasX = true;
                    }
                }
                if (hasX && findY(curs, i, y)) {
                    return true;
                }
            }
            pre.clear();
            pre.addAll(curs);
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return false;
    }

    private boolean findY(List<TreeNode> dates, int i, int compare) {
        for (int j = 0; j < dates.size(); j++) {
            if (i != j) {
                TreeNode node = dates.get(j);
                if (node.left != null) {
                    if (node.left.val == compare) {
                        return true;
                    }
                }
                if (node.right != null) {
                    if (node.right.val == compare) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
