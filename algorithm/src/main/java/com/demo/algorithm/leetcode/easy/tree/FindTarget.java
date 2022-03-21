package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/3/21.
 * description :  两数之和IV-输入BST
 *
 * 给定一个二叉搜索树root和一个目标结果k，如果BST中存在两个元素且它们的和等于给定的目标结果，则返回true。
 *
 * 示例 1：
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 *
 * 示例 2：
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 *
 * 提示:
 * 二叉树的节点个数的范围是[1, 10^4].
 * -10^4<= Node.val <= 10^4
 * root为二叉搜索树
 * -10^5<= k <= 10^5
 */
public class FindTarget {

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> marks = new HashSet<>();
        List<Integer> dates = new ArrayList<>();
        dfs(root, marks, dates);
        for (int i = 0; i < dates.size(); i++) {
            int num = dates.get(i);
            if (num > k / 2) {
                break;
            }
            if (num * 2 == k) {
                return false;
            }
            if (marks.contains(k - num)) {
                return true;
            }
        }
        return false;
    }

    private void dfs(TreeNode root, Set<Integer> marks, List<Integer> dates) {
        if (root == null) {
            return;
        }
        dfs(root.left, marks, dates);
        marks.add(root.val);
        dates.add(root.val);
        dfs(root.right, marks, dates);
    }
}
