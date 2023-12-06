package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/6
 * @author chenglong
 * description : 二叉搜索树中的众数
 *
 * 给你一个含重复值的二叉搜索树（BST）的根节点root，找出并返回BST中的所有众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按任意顺序返回。
 * 假定BST满足如下定义：
 * 结点左子树中所含节点的值小于等于当前节点的值
 * 结点右子树中所含节点的值大于等于当前节点的值
 * 左子树和右子树都是二叉搜索树
 *
 * 示例 1：
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：root = [0]
 * 输出：[0]
 *
 * 提示：
 * 树中节点的数目在范围 [1, 10^4] 内
 * -10^5 <= Node.val <= 10^5
 */
public class FindMode {

    private List<Integer> dates = new ArrayList<>();
    private int pre = Integer.MIN_VALUE;
    private int maxCount = 0;
    private int count = 0;

    public int[] findMode(TreeNode root) {
        dates.clear();
        dfs(root);
        if (count == maxCount) {
            dates.add(pre);
        } else if (count > maxCount) {
            dates.clear();
            dates.add(pre);
        }
        int[] answer = new int[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            answer[i] = dates.get(i);
        }
        return answer;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            dfs(root.left);
        }
        //统计当前值的数量
        if (pre != root.val) {
            if (count > 0) {
                if (count == maxCount) {
                    dates.add(pre);
                } else if (count > maxCount) {
                    maxCount = count;
                    dates.clear();
                    dates.add(pre);
                }
            }
            count = 1;
        } else {
            count++;
        }
        pre = root.val;
        if (root.right != null) {
            dfs(root.right);
        }
    }
}
