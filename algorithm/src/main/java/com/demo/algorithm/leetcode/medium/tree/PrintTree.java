package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/22
 * @author chenglong
 * description : 输出二叉树
 *
 * 给你一棵二叉树的根节点root，请你构造一个下标从0开始、大小为m x n的字符串矩阵res，用以表示树的格式化布局。构造此格式化布局矩阵需要遵循以下规则：
 * 树的高度为height，矩阵的行数m应该等于height + 1 。
 * 矩阵的列数n应该等于2^(height+1) -1。
 * 根节点需要放置在顶行的正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在res[r+1][c-2^height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串"" 。
 * 返回构造得到的矩阵res。
 *
 * 示例 1：
 * 输入：root = [1,2]
 * 输出：
 * [["","1",""],
 * ["2","",""]]
 *
 * 示例 2：
 * 输入：root = [1,2,3,null,4]
 * 输出：
 * [["","","","1","","",""],
 * ["","2","","","","3",""],
 * ["","","4","","","",""]]
 *
 * 提示：
 * 树中节点数在范围 [1, 2^10] 内
 * -99 <= Node.val <= 99
 * 树的深度在范围 [1, 10] 内
 */
public class PrintTree {

    private int heigth;
    private final int[] dates = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};

    public List<List<String>> printTree(TreeNode root) {
        //1，获取二叉树的高度
        heigth = 0;
        dfs(0, root);
        //2，计算m,n大小
        int m = heigth + 1;
        int n = dates[m] - 1;
        //3，构建默认值
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> items = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                items.add("");
            }
            result.add(items);
        }
        //4，遍历二叉树进行赋值
        int index = (n - 1) / 2;
        result.get(0).set(index, "" + root.val);
        assign(0, index, root, result);
        return result;
    }

    private void assign(int r, int c, TreeNode root, List<List<String>> result) {
        if (root.left != null) {
            int r1 = r + 1;
            int c1 = c - dates[heigth - r - 1];
            result.get(r1).set(c1, "" + root.left.val);
            assign(r1, c1, root.left, result);
        }
        if (root.right != null) {
            int r2 = r + 1;
            int c2 = c + dates[heigth - r - 1];
            result.get(r2).set(c2, "" + root.right.val);
            assign(r2, c2, root.right, result);
        }

    }

    private void dfs(int step, TreeNode root) {
        if (root.left == null && root.right == null) {
            //此时root为叶子节点
            if (step > heigth) {
                heigth = step;
            }
            return;
        }
        if (root.left != null) {
            dfs(step + 1, root.left);
        }
        if (root.right != null) {
            dfs(step + 1, root.right);
        }
    }
}
