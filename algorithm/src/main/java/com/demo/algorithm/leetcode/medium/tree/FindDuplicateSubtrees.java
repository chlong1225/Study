package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/9/5
 * @author chenglong
 * description : 寻找重复的子树
 *
 * 给定一棵二叉树root，返回所有重复的子树。
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 *
 * 示例 2：
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 *
 * 提示：
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 */
public class FindDuplicateSubtrees {

    private final Map<String, Integer> marks = new HashMap<>();
    private final List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        marks.clear();
        result.clear();
        dfs(root);
        return result;
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        builder.append("_");
        builder.append(dfs(root.left));
        builder.append("_");
        builder.append(dfs(root.right));
        String key = builder.toString();
        if (marks.containsKey(key)) {
            marks.put(key, marks.get(key) + 1);
            if (marks.get(key) == 2) {
                result.add(root);
            }
        } else {
            marks.put(key, 1);
        }
        return key;
    }
}
