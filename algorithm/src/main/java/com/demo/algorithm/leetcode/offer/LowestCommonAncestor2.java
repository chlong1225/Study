package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/10.
 * description : 剑指Offer68-II. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:root =[3,5,1,6,2,0,8,null,null,7,4]
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class LowestCommonAncestor2 {

    List<TreeNode> date1 = new ArrayList<>();
    List<TreeNode> date2 = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        date1.clear();
        date2.clear();
        List<TreeNode> path = new ArrayList<>();
        dfs(root, p, q, path);
        TreeNode result = root;
        if (date1.size() > date2.size()) {
            for (int i = 0; i < date2.size(); i++) {
                if (date1.get(i).val == date2.get(i).val) {
                    result = date1.get(i);
                } else {
                    break;
                }
            }
        } else {
            for (int i = 0; i < date1.size(); i++) {
                if (date1.get(i).val == date2.get(i).val) {
                    result = date1.get(i);
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private void dfs(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> path) {
        path.add(root);
        if (root.val == p.val) {
            date1.addAll(path);
        }
        if (root.val == q.val) {
            date2.addAll(path);
        }
        if (date1.size() > 0 && date2.size() > 0) {
            return;
        }
        if (root.left != null) {
            dfs(root.left, p, q, path);
        }
        if (root.right != null) {
            dfs(root.right, p, q, path);
        }
        path.remove(path.size() - 1);
    }
}
