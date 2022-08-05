package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/5
 * @author chenglong
 * description : 在二叉树中增加一行
 *
 * 给定一个二叉树的根root和两个整数val和depth，在给定的深度depth处添加一个值为val的节点行。
 * 注意，根节点root位于深度1。
 *
 * 加法规则如下:
 * 给定整数depth，对于深度为depth-1的每个非空树节点cur，创建两个值为val的树节点作为cur的左子树根和右子树根。
 * cur原来的左子树应该是新的左子树根的左子树。
 * cur原来的右子树应该是新的右子树根的右子树。
 * 如果depth == 1意味着depth - 1根本没有深度，那么创建一个树节点，值val作为整个原始树的新根，而原始树就是新根的左子树。
 *
 * 示例 1:
 * 输入: root = [4,2,6,3,1,5], val = 1, depth = 2
 * 输出: [4,1,1,2,null,null,6,3,1,5]
 *
 * 示例 2:
 * 输入: root = [4,2,null,3,1], val = 1, depth = 3
 * 输出:  [4,2,null,1,1,3,null,null,1]
 *
 * 提示:
 * 节点数在[1, 10^4]范围内
 * 树的深度在[1, 10^4]范围内
 * -100 <= Node.val <= 100
 * -10^5<= val <= 10^5
 * 1 <= depth <= the depth of tree + 1
 */
public class AddOneRow {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        List<TreeNode> dates = new ArrayList<>();
        dates.add(root);
        List<TreeNode> next = new ArrayList<>();
        int step = 1;
        while (dates.size() > 0) {
            if (step + 1 == depth) {
                //插入到当前层
                for (int i = 0; i < dates.size(); i++) {
                    TreeNode cur = dates.get(i);
                    TreeNode left = cur.left;
                    TreeNode right = cur.right;
                    cur.left = new TreeNode(val);
                    cur.left.left = left;
                    cur.right = new TreeNode(val);
                    cur.right.right = right;
                }
                break;
            }
            for (int i = 0; i < dates.size(); i++) {
                TreeNode tem = dates.get(i);
                if (tem.left != null) {
                    next.add(tem.left);
                }
                if (tem.right != null) {
                    next.add(tem.right);
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
            step++;
        }
        return root;
    }
}
