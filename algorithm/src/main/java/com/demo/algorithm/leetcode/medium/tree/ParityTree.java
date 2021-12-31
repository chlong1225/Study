package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 12/31/21
 * @author chenglong
 * description : 奇偶树
 *
 * 如果一棵二叉树满足下述几个条件，则可以称为奇偶树 ：
 * 二叉树根节点所在层下标为0 ，根的子节点所在层下标为1 ，根的孙节点所在层下标为2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是奇整数，从左到右按顺序严格递增
 * 奇数下标 层上的所有节点的值都是偶整数，从左到右按顺序严格递减
 * 给你二叉树的根节点，如果二叉树为奇偶树，则返回true ，否则返回false 。
 *
 * 示例 1：
 * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * 输出：true
 * 解释：每一层的节点值分别是：
 * 0 层：[1]
 * 1 层：[10,4]
 * 2 层：[3,7,9]
 * 3 层：[12,8,6,2]
 * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
 *
 * 示例 2：
 * 输入：root = [5,4,2,3,3,7]
 * 输出：false
 * 解释：每一层的节点值分别是：
 * 0 层：[5]
 * 1 层：[4,2]
 * 2 层：[3,3,7]
 * 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
 *
 * 示例 3：
 * 输入：root = [5,9,1,3,5,7]
 * 输出：false
 * 解释：1 层上的节点值应为偶数。
 *
 * 示例 4：
 * 输入：root = [1]
 * 输出：true
 *
 * 示例 5：
 * 输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * 输出：true
 *  
 * 提示：
 * 树中节点数在范围 [1, 105] 内
 * 1 <= Node.val <= 106
 */
public class ParityTree {

    public boolean isEvenOddTree(TreeNode root) {
        //使用广度优先遍历bfs（分层遍历）
        List<TreeNode> datas = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        datas.add(root);
        //是否为偶数
        boolean isDouble = true;
        while (!datas.isEmpty()) {
            int size = datas.size();
            int pre = 0;
            for (int i = 0; i < size; i++) {
                TreeNode tem = datas.get(i);
                if (i == 0) {
                    pre = tem.val;
                } else {
                    if (isDouble) {
                        //偶数层严格递增
                        if (tem.val <= pre) {
                            return false;
                        }
                    } else {
                        //奇数层严格递减
                        if (tem.val >= pre) {
                            return false;
                        }
                    }
                    pre = tem.val;
                }
                if (isDouble) {
                    //偶数层必须为奇数
                    if (pre % 2 == 0) {
                        return false;
                    }
                } else {
                    //奇数层必须为偶数
                    if (pre % 2 == 1) {
                        return false;
                    }
                }
                if (tem.left != null) {
                    next.add(tem.left);
                }
                if (tem.right != null) {
                    next.add(tem.right);
                }
            }
            datas.clear();
            datas.addAll(next);
            next.clear();
            isDouble = !isDouble;
        }
        return true;
    }
}
