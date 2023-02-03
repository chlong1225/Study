package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/2/3
 * @author chenglong
 * description : 二叉树着色游戏
 *
 * 有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点root，树上总共有n个节点，且n为奇数，其中每个节点上的值从1到n各不相同。
 * 最开始时：
 * 「一号」玩家从 [1, n]中取一个值x（1 <= x <= n）；
 * 「二号」玩家也从[1, n]中取一个值y（1 <= y <= n）且y != x。
 * 「一号」玩家给值为x的节点染上红色，而「二号」玩家给值为y的节点染上蓝色。
 * 之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。
 * 如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。
 * 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利️。
 * 现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个y值可以确保你赢得这场游戏，则返回true ；若无法获胜，就请返回 false 。
 *
 * 示例 1 ：
 * 输入：root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 * 输出：true
 * 解释：第二个玩家可以选择值为 2 的节点。
 *
 * 示例 2 ：
 * 输入：root = [1,2,3], n = 3, x = 1
 * 输出：false
 *
 * 提示：
 * 树中节点数目为 n
 * 1 <= x <= n <= 100
 * n 是奇数
 * 1 <= Node.val <= n
 * 树中所有值互不相同
 */
public class BtreeGameWinningMove {

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        //1，查找x对应的节点
        TreeNode find = findNode(root, x);
        if (find == null) {
            return false;
        }
        //2，查找左右两边的节点数量
        int left = getNodeCount(find.left);
        int right = getNodeCount(find.right);
        int last = n - left - right - 1;
        if (left > n / 2 || right > n / 2 || last > n / 2) {
            return true;
        }
        return false;
    }

    private int getNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            sum += dates.size();
            for (int i = 0; i < dates.size(); i++) {
                TreeNode cur = dates.get(i);
                if (cur.left != null) {
                    next.add(cur.left);
                }
                if (cur.right != null) {
                    next.add(cur.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return sum;
    }

    //使用bfs查找
    private TreeNode findNode(TreeNode root, int target) {
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                TreeNode cur = dates.get(i);
                if (cur.val == target) {
                    return cur;
                }
                if (cur.left != null) {
                    next.add(cur.left);
                }
                if (cur.right != null) {
                    next.add(cur.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return null;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
