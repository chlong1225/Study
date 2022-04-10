package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/10.
 * description : 剑指Offer68-I. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树: root=[6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        List<TreeNode> date1 = new ArrayList<>();
        List<TreeNode> date2 = new ArrayList<>();
        dfs(root, p, date1);
        dfs(root, q, date2);
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

    private void dfs(TreeNode root, TreeNode p, List<TreeNode> date) {
        date.add(root);
        if (root.val > p.val) {
            dfs(root.left, p, date);
        } else if (root.val < p.val) {
            dfs(root.right, p, date);
        }
    }
}
