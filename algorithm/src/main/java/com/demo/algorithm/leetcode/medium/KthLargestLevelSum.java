package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2024/2/23
 * @author chenglong
 * description : 二叉树中的第K大层和
 *
 * 给你一棵二叉树的根节点root和一个正整数k。
 * 树中的层和是指同一层上节点值的总和。
 * 返回树中第k大的层和（不一定不同）。如果树少于k层，则返回-1。
 * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
 *
 * 示例 1：
 * 输入：root = [5,8,9,2,1,3,7,4,6], k = 2
 * 输出：13
 * 解释：树中每一层的层和分别是：
 * - Level 1: 5
 * - Level 2: 8 + 9 = 17
 * - Level 3: 2 + 1 + 3 + 7 = 13
 * - Level 4: 4 + 6 = 10
 * 第 2 大的层和等于 13 。
 *
 * 示例 2：
 * 输入：root = [1,2,null,3], k = 1
 * 输出：3
 * 解释：最大的层和是 3 。
 *
 * 提示：
 * 树中的节点数为 n
 * 2 <= n <= 10^5
 * 1 <= Node.val <= 10^6
 * 1 <= k <= n
 */
public class KthLargestLevelSum {

    public long kthLargestLevelSum(TreeNode root, int k) {
        //1，获取每层的和
        List<Long> dates = new ArrayList<>();
        List<TreeNode> curs = new ArrayList<>();
        List<TreeNode> nexts = new ArrayList<>();
        curs.add(root);
        while (curs.size() > 0) {
            long sum = 0;
            for (int i = 0; i < curs.size(); i++) {
                sum += curs.get(i).val;
                if (curs.get(i).left != null) {
                    nexts.add(curs.get(i).left);
                }
                if (curs.get(i).right != null) {
                    nexts.add(curs.get(i).right);
                }
            }
            dates.add(sum);
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        if (dates.size() < k) {
            return -1;
        }
        //2，对每层的和进行排序
        Collections.sort(dates);
        int index = dates.size() - k;
        return dates.get(index);
    }
}
