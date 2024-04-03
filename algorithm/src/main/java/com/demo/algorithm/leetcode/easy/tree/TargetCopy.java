package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2024/4/3
 * @author chenglong
 * description : 找出克隆二叉树中的相同节点
 *
 * 给你两棵二叉树，原始树original和克隆树cloned，以及一个位于原始树original中的目标节点target。
 * 其中，克隆树cloned是原始树original的一个副本。
 * 请找出在树cloned中，与target相同的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回节点指针，其他语言返回节点本身）。
 * 注意：你不能对两棵二叉树，以及target节点进行更改。只能返回对克隆树cloned中已有的节点的引用。
 *
 * 示例 1:
 * 输入: tree = [7,4,3,null,null,6,19], target = 3
 * 输出: 3
 * 解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。
 *
 * 示例 2:
 * 输入: tree = [7], target =  7
 * 输出: 7
 *
 * 示例 3:
 * 输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * 输出: 4
 *
 * 提示：
 * 树中节点的数量范围为 [1, 104] 。
 * 同一棵树中，没有值相同的节点。
 * target 节点是树 original 中的一个节点，并且不会是 null 。
 *
 * 进阶：如果树中允许出现值相同的节点，将如何解答？
 */
public class TargetCopy {

    private TreeNode result;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        result = null;
        dfs(cloned, target);
        return result;
    }

    private void dfs(TreeNode cloned, TreeNode target) {
        if (result != null) {
            return;
        }
        if (cloned.val == target.val) {
            result = cloned;
            return;
        }
        if (cloned.left != null) {
            dfs(cloned.left, target);
        }
        if (cloned.right != null) {
            dfs(cloned.right, target);
        }
    }
}
