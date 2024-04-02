package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2024/4/2
 * @author chenglong
 * description : 所有可能的真二叉树
 *
 * 给你一个整数n，请你找出所有可能含n个节点的真二叉树，并以列表形式返回。答案中每棵树的每个节点都必须符合Node.val==0。
 * 答案的每个元素都是一棵真二叉树的根节点。你可以按任意顺序返回最终的真二叉树列表。
 * 真二叉树是一类二叉树，树中每个节点恰好有0或2个子节点。
 *
 * 示例 1：
 * 输入：n = 7
 * 输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：[[0,0,0]]
 *
 * 提示：
 * 1 <= n <= 20
 */
public class AllPossibleFBT {

    public List<TreeNode> allPossibleFBT(int n) {
        //1，经分析真二叉树节点数必须为奇数
        if (n % 2 == 0) {
            return new ArrayList<>();
        }
        List<TreeNode>[] marks = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            marks[i] = new ArrayList<>();
        }
        marks[1].add(new TreeNode(0));
        for (int i = 3; i <= n; i++) {
            if (i % 2 == 1) {
                //比如：i=3三个节点。根节点1。剩下的2为左右节点数
                int count = i - 1;
                for (int left = 1; left <= count - 1; left++) {
                    int right = count - left;
                    //此时左右节点数为left，right
                    if (left % 2 == 1) {
                        //此时marks[left]与marks[right]分别对应左右节点树
                        for (int j = 0; j < marks[left].size(); j++) {
                            for (int k = 0; k < marks[right].size(); k++) {
                                TreeNode node1 = new TreeNode(0);
                                node1.left = marks[left].get(j);
                                node1.right = marks[right].get(k);
                                marks[i].add(node1);
                            }
                        }
                    }
                }
            }
        }
        return marks[n];
    }
}
