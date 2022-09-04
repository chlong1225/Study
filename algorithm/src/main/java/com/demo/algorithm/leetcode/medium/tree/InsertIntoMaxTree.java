package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

/**
 * create on 2022/8/30
 * @author chenglong
 * description : 最大二叉树II
 *
 * 最大树定义：一棵树并满足：其中每个节点的值都大于其子树中的任何其他值。
 * 给你最大树的根节点root和一个整数val。
 * 给定的树是利用Construct(a)例程从列表a（root = Construct(a)）递归地构建的：
 * 如果a为空，返回null 。
 * 否则，令a[i]作为a的最大元素。创建一个值为a[i]的根节点root。
 * root的左子树将被构建为Construct([a[0], a[1], ..., a[i - 1]]) 。
 * root的右子树将被构建为Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]) 。
 * 返回root 。
 * 请注意，题目没有直接给出a，只是给出一个根节点root = Construct(a) 。
 * 假设b是a的副本，并在末尾附加值val。题目数据保证b中的值互不相同。
 * 返回Construct(b)。
 *
 * 示例 1：
 * 输入：root = [4,1,3,null,null,2], val = 5
 * 输出：[5,4,null,1,3,null,null,2]
 * 解释：a = [1,4,2,3], b = [1,4,2,3,5]
 *
 * 示例 2：
 * 输入：root = [5,2,4,null,1], val = 3
 * 输出：[5,2,4,null,1,null,3]
 * 解释：a = [2,1,5,4], b = [2,1,5,4,3]
 *
 * 示例 3：
 * 输入：root = [5,2,3,null,1], val = 4
 * 输出：[5,2,4,null,1,3]
 * 解释：a = [2,1,5,3], b = [2,1,5,3,4]
 *
 * 提示：
 * 树中节点数目在范围[1, 100]内
 * 1 <= Node.val <= 100
 * 树中的所有值 互不相同
 * 1 <= val <= 100
 */
public class InsertIntoMaxTree {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }
        //1，val大于所有值时，val为根节点
        if (root.val < val) {
            node.left = root;
            return node;
        }
        //2，val加入到a的最后，则val在root的右边节点。依次递归右节点判断大小
        TreeNode parent = root;
        TreeNode right = root.right;
        while (right != null && right.val > val) {
            parent = right;
            right = right.right;
        }
        parent.right = node;
        node.left = right;
        return root;
    }
}
