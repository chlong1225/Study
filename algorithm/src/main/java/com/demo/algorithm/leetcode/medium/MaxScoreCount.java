package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/19.
 * description : 统计最高分的节点数目
 * <p>
 * 给你一棵根节点为0的二叉树，它总共有n个节点，节点编号为0到n-1。同时给你一个下标从0开始的整数数组parents表示这棵树，
 * 其中parents[i]是节点i的父节点。由于节点0是根，所以parents[0]==-1。
 * 一个子树的大小为这个子树内节点的数目。每个节点都有一个与之关联的分数。求出某个节点分数的方法是，将这个节点和与它相连的边全部删除，
 * 剩余部分是若干个非空子树，这个节点的分数为所有这些子树大小的乘积。
 * 请你返回有最高得分节点的数目。
 * <p>
 * 示例1:
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * <p>
 * 示例 2：
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * <p>
 * 提示：
 * n == parents.length
 * 2 <= n <= 10^5
 * parents[0] == -1
 * 对于i!=0，有0<= parents[i]<= n - 1
 * parents表示一棵二叉树。
 */
public class MaxScoreCount {

    private long max = 0;
    private int n = 0;
    private int count = 0;

    public int countHighestScoreNodes(int[] parents) {
        //1，构建二叉树
        TreeNode root = new TreeNode(0);
        int length = parents.length;
        TreeNode[] marks = new TreeNode[length];
        marks[0] = root;
        for (int i = 1; i < length; i++) {
            //处理子节点
            if (marks[i] == null) {
                TreeNode child = new TreeNode(i);
                marks[i] = child;
            }
            //处理父节点
            int parent = parents[i];
            if (marks[parent] == null) {
                TreeNode tem = new TreeNode(parent);
                marks[parent] = tem;
            }
            //关联子父节点
            if (marks[parent].left == null) {
                marks[parent].left = marks[i];
            } else {
                marks[parent].right = marks[i];
            }
        }
        //2，计算分数并获取最大
        /**
         * 分析：节点的分数 = 左节点数量left*右节点的数量right*其它节点数量other
         * 其中other+left+right=length-1
         */
        max = 0;
        n = length;
        count = 0;
        dfs(root);
        return count;
    }

    //获取当前节点的子节点数量
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int other = n - left - right - 1;
        //计算分数
        long score = checkNum(left) * checkNum(right) * checkNum(other);
        if (score == max) {
            count++;
        }else if (score > max) {
            max = score;
            count = 1;
        }
        return left + right + 1;
    }

    private long checkNum(int num) {
        if (num == 0) {
            return 1;
        }
        return num;
    }

    private static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
