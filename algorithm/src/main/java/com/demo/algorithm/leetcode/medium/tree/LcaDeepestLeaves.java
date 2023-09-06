package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/9/6
 * @author chenglong
 * description : 最深叶节点的最近公共祖先
 *
 * 给你一个有根节点root的二叉树，返回它最深的叶节点的最近公共祖先。
 * 回想一下：
 * 叶节点是二叉树中没有子节点的节点
 * 树的根节点的深度为0，如果某一节点的深度为d，那它的子节点的深度就是d+1
 * 如果我们假定A是一组节点S的最近公共祖先，S中的每个节点都在以A为根节点的子树中，且A的深度达到此条件下可能的最大值。
 *
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
 *
 * 示例 3：
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。
 *
 * 提示：
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是独一无二的。
 */
public class LcaDeepestLeaves {

    private TreeNode result = null;
    private int maxDepth = -1;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private int dfs(TreeNode root, int depth) {
        if (root == null) {
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        int left = dfs(root.left, depth + 1);
        int right = dfs(root.right, depth + 1);
        if (left == right && left == maxDepth) {
            result = root;
        }
        return Math.max(left, right);
    }
}
