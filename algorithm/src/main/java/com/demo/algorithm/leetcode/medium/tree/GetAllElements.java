package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/3.
 * description : 两棵二叉搜索树中的所有元素
 *
 * 给你root1和root2这两棵二叉搜索树。请你返回一个列表，其中包含两棵树中的所有整数并按升序排序。.
 *
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 *
 * 示例 2：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 *
 * 提示：
 * 每棵树的节点数在[0, 5000] 范围内
 * -10^5<= Node.val <= 10^5
 */
public class GetAllElements {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> date1 = new ArrayList<>();
        dfs(root1, date1);
        List<Integer> date2 = new ArrayList<>();
        dfs(root2, date2);
        //合并数据
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < date1.size() && index2 < date2.size()) {
            if (date1.get(index1) <= date2.get(index2)) {
                result.add(date1.get(index1));
                index1++;
            } else {
                result.add(date2.get(index2));
                index2++;
            }
        }
        if (index1 == date1.size()) {
            for (int i = index2; i < date2.size(); i++) {
                result.add(date2.get(i));
            }
        } else {
            for (int i = index1; i < date1.size(); i++) {
                result.add(date1.get(i));
            }
        }
        return result;
    }

    private void dfs(TreeNode root, List<Integer> dates) {
        if (root == null) {
            return;
        }
        dfs(root.left, dates);
        dates.add(root.val);
        dfs(root.right, dates);
    }
}
