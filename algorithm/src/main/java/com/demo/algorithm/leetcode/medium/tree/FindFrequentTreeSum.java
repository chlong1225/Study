package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/6/19
 * @author chenglong
 * description : 出现次数最多的子树元素和
 *
 * 给你一个二叉树的根结点root，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * 一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 * 示例 1：
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 *
 * 示例2：
 * 输入: root = [5,2,-5]
 * 输出: [2]
 *
 * 提示:
 * 节点数在[1, 10^4]范围内
 * -10^5<= Node.val <= 10^5
 */
public class FindFrequentTreeSum {

    private final Map<Integer, Integer> marks = new HashMap<>();
    private int max = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        //1，统计所有子数的和次数并记录最大次数
        dfs(root);
        //2，统计数量为max的子树和
        List<Integer> dates = new ArrayList<>();
        for (int key : marks.keySet()) {
            if (marks.get(key) == max) {
                dates.add(key);
            }
        }
        //3，将集合转数组
        int[] result = new int[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            result[i] = dates.get(i);
        }
        return result;
    }

    private int dfs(TreeNode root) {
        int total = root.val;
        if (root.left != null) {
            total += dfs(root.left);
        }
        if (root.right != null) {
            total += dfs(root.right);
        }
        if (marks.containsKey(total)) {
            int count = marks.get(total) + 1;
            marks.put(total, count);
            if (count > max) {
                max = count;
            }
        } else {
            marks.put(total, 1);
            if (max < 1) {
                max = 1;
            }
        }
        return total;
    }
}
