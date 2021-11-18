package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 11/18/21
 * @author chenglong
 * description : 二叉树的坡度
 *
 * 给定一个二叉树，计算整个树的坡度 。
 * 一个树的节点的坡度定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 * 整个树的坡度就是其所有节点的坡度之和。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：1
 * 解释：
 * 节点 2 的坡度：|0-0| = 0（没有子节点）
 * 节点 3 的坡度：|0-0| = 0（没有子节点）
 * 节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
 * 坡度总和：0 + 0 + 1 = 1
 *
 * 示例 2：
 * 输入：root = [4,2,9,3,5,null,7]
 * 输出：15
 * 解释：
 * 节点 3 的坡度：|0-0| = 0（没有子节点）
 * 节点 5 的坡度：|0-0| = 0（没有子节点）
 * 节点 7 的坡度：|0-0| = 0（没有子节点）
 * 节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
 * 节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
 * 节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 16 ）
 * 坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
 *
 * 示例 3：
 * 输入：root = [21,7,14,1,1,2,2,3,3]
 * 输出：9
 *
 * 提示：
 * 树中节点数目的范围在 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class TreeSlope {

    private int sum = 0;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sum = 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        sum += Math.abs(left - right);
        return sum;
    }

    /**
     * @param root
     * @return : 计算当前树包含子树的总和
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = root.val;
        int left = dfs(root.left);
        int right = dfs(root.right);
        count += (left + right);
        sum += Math.abs(left - right);
        return count;
    }
}
