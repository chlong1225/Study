package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 11/23/21
 * @author chenglong
 * description : 打家劫舍III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class Rob3 {

    //用于标记,防止去重
    Map<TreeNode, int[]> marks = new HashMap<>();

    public int rob(TreeNode root) {
        //当前房屋只有两种状态,偷/不偷
        if (root == null) {
            return 0;
        }
        marks.clear();
        int result = Math.max(getUnRob(root), getRob(root) + root.val);
        return result;
    }

    //root偷时子树的最大金额
    private int getRob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (marks.get(root) != null) {
            int[] data = marks.get(root);
            if (data[1] != 0) {
                return data[1];
            }
        }
        int result = 0;
        if (root.left != null) {
            if (root.left.left != null) {
                result += Math.max(getUnRob(root.left.left), getRob(root.left.left) + root.left.left.val);
            }
            if (root.left.right != null) {
                result += Math.max(getUnRob(root.left.right), getRob(root.left.right) + root.left.right.val);
            }
        }
        if (root.right != null) {
            if (root.right.left != null) {
                result += Math.max(getUnRob(root.right.left), getRob(root.right.left) + root.right.left.val);
            }
            if (root.right.right != null) {
                result += Math.max(getUnRob(root.right.right), getRob(root.right.right) + root.right.right.val);
            }
        }
        if (marks.get(root) == null) {
            int[] data = new int[2];
            data[1] = result;
            marks.put(root, data);
        } else {
            int[] data = marks.get(root);
            data[1] = result;
            marks.put(root, data);
        }
        return result;
    }

    //root不偷时子树最大金额
    private int getUnRob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (marks.get(root) != null) {
            int[] data = marks.get(root);
            if (data[0] != 0) {
                return data[0];
            }
        }
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = Math.max(getUnRob(root.left), getRob(root.left) + root.left.val);
        }
        if (root.right != null) {
            right = Math.max(getUnRob(root.right), getRob(root.right) + root.right.val);
        }
        if (marks.get(root) == null) {
            int[] data = new int[2];
            data[0] = left + right;
            marks.put(root, data);
        } else {
            int[] data = marks.get(root);
            data[0] = left + right;
            marks.put(root, data);
        }
        return left + right;
    }
}
