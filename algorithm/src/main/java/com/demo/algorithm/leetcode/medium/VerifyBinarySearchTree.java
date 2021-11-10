package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/10.
 * description : 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *  
 * 提示：
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */
public class VerifyBinarySearchTree {

    //二叉树进行中序遍历,二叉搜索树升序
    public boolean isValidBST(TreeNode root) {
        List<Integer> datas = new ArrayList<>(1);
        return dfs(root, datas);
    }

    private boolean dfs(TreeNode root, List<Integer> datas) {
        if (root == null) {
            return true;
        }
        boolean dfs = dfs(root.left, datas);
        if (!dfs) {
            return false;
        }
        if (datas.size() == 0) {
            datas.add(root.val);
        } else {
            if (datas.get(0) >= root.val) {
                return false;
            } else {
                datas.set(0, root.val);
            }
        }
        return dfs(root.right, datas);
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidLeft(root.left, root.val) && isValibRight(root.right, root.val);
    }

    //判断最右边的子树是否为二叉搜索树
    private boolean isValibRight(TreeNode right, int min) {
        if (right == null) {
            return true;
        }
        if (right.val <= min) {
            return false;
        }
        return isValibRight(right.right, right.val) && isValidMiddle(right.left, min, right.val);
    }

    //判断最左边的子树是否为二叉搜索树
    private boolean isValidLeft(TreeNode left, int max) {
        if (left == null) {
            return true;
        }
        if (left.val >= max) {
            return false;
        }
        return isValidLeft(left.left, left.val) && isValidMiddle(left.right, left.val, max);
    }

    //判断中间的子树是否二叉搜索树
    private boolean isValidMiddle(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidMiddle(root.left, min, root.val) && isValidMiddle(root.right, root.val, max);
    }
}
