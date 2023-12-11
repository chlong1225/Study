package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 2023/12/11
 * description : 叶子相似的树
 *
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个叶值序列 。
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是叶相似的。
 * 如果给定的两个根结点分别为root1和root2的树是叶相似的，则返回true；否则返回false。
 *
 * 示例 1：
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 *
 * 示例 2：
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 *
 * 提示：
 * 给定的两棵树结点数在 [1, 200] 范围内
 * 给定的两棵树上的值在 [0, 200] 范围内
 */
public class LeafSimilar {

    private final List<Integer> dates1 = new ArrayList<>();
    private final List<Integer> dates2 = new ArrayList<>();

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        dates1.clear();
        dates2.clear();
        dfs(root1, dates1);
        dfs(root2, dates2);
        if (dates1.size() != dates2.size()) {
            return false;
        }
        for (int i = 0; i < dates1.size(); i++) {
            if (dates1.get(i) != dates2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(TreeNode root,List<Integer> dates){
        if (root.left != null) {
            dfs(root.left, dates);
        }
        if (root.left == null && root.right == null) {
            dates.add(root.val);
        }
        if (root.right != null) {
            dfs(root.right, dates);
        }
    }
}
