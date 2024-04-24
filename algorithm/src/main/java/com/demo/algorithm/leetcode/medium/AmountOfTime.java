package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2024/4/24
 * @author chenglong
 * description : 感染二叉树需要的总时间
 *
 * 给你一棵二叉树的根节点root，二叉树中节点的值互不相同。另给你一个整数start。在第0分钟，感染将会从值为start的节点开始爆发。
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 *
 * 示例 1：
 * 输入：root = [1,5,3,null,4,10,6,9,2], start = 3
 * 输出：4
 * 解释：节点按以下过程被感染：
 * - 第 0 分钟：节点 3
 * - 第 1 分钟：节点 1、10、6
 * - 第 2 分钟：节点5
 * - 第 3 分钟：节点 4
 * - 第 4 分钟：节点 9 和 2
 * 感染整棵树需要 4 分钟，所以返回 4 。
 *
 * 示例 2：
 * 输入：root = [1], start = 1
 * 输出：0
 * 解释：第 0 分钟，树中唯一一个节点处于感染状态，返回 0 。
 *
 * 提示：
 * 树中节点的数目在范围 [1, 10^5] 内
 * 1 <= Node.val <= 10^5
 * 每个节点的值互不相同
 * 树中必定存在值为start的节点
 */
public class AmountOfTime {

    private TreeNode startNode;
    Map<TreeNode, TreeNode> marks = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        //1，记录父子节点关系并查找start对应的节点
        marks.clear();
        dfs(start, root, marks);
        //2，遍历节点startNode的深度
        List<TreeNode> curs = new ArrayList<>();
        curs.add(startNode);
        //使用赋值-1标记已经使用了
        startNode.val = -1;
        List<TreeNode> nexts = new ArrayList<>();
        int time = -1;
        while (curs.size() > 0) {
            time++;
            for (int i = 0; i < curs.size(); i++) {
                TreeNode cur = curs.get(i);
                if (cur.left != null && cur.left.val != -1) {
                    nexts.add(cur.left);
                    cur.left.val = -1;
                }
                if (cur.right != null && cur.right.val != -1) {
                    nexts.add(cur.right);
                    cur.right.val = -1;
                }
                if (marks.containsKey(cur)) {
                    TreeNode parent = marks.get(cur);
                    if (parent != null && parent.val != -1) {
                        nexts.add(parent);
                        parent.val = -1;
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return time;
    }

    private void dfs(int start, TreeNode root, Map<TreeNode, TreeNode> marks) {
        if (root.val == start) {
            startNode = root;
        }
        if (root.left != null) {
            marks.put(root.left, root);
            dfs(start, root.left, marks);
        }
        if (root.right != null) {
            marks.put(root.right, root);
            dfs(start, root.right, marks);
        }
    }
}
