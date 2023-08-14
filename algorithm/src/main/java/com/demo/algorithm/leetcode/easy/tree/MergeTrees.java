package com.demo.algorithm.leetcode.easy.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2023/8/14
 * @author chenglong
 * description : 合并二叉树
 *
 * 给你两棵二叉树：root1和root2。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为null的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * 示例 1：
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 *
 * 示例 2：
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 *
 * 提示：
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 */
public class MergeTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        dfs(root1, root2);
        return root1;
    }

    //将root2合并到root1中
    private void dfs(TreeNode root1, TreeNode root2) {
        //1，合并根结点
        root1.val = root1.val + root2.val;
        //2，处理左边节点
        if (root1.left == null) {
            root1.left = root2.left;
        } else {
            if (root2.left != null) {
                dfs(root1.left, root2.left);
            }
        }
        //3，处理右边节点
        if (root1.right == null) {
            root1.right = root2.right;
        } else {
            if (root2.right != null) {
                dfs(root1.right, root2.right);
            }
        }
    }
}
