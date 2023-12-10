package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 2023/12/10
 * description : 二叉树的层平均值
 *
 * 给定一个非空二叉树的根节点root,以数组的形式返回每一层节点的平均值。与实际答案相差10-5以内的答案可以被接受。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 *
 * 示例 2:
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 *
 * 提示：
 * 树中节点数量在 [1, 10^4] 范围内
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class AverageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> answer = new ArrayList<>();
        List<TreeNode> curs = new ArrayList<>();
        curs.add(root);
        List<TreeNode> nexts = new ArrayList<>();
        double sum = 0;
        int count = 0;
        while (curs.size() > 0) {
            sum = 0;
            count = 0;
            for (int i = 0; i < curs.size(); i++) {
                TreeNode node = curs.get(i);
                sum += node.val;
                count++;
                if (node.left != null) {
                    nexts.add(node.left);
                }
                if (node.right != null) {
                    nexts.add(node.right);
                }
            }
            answer.add(sum / count);
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return answer;
    }
}
