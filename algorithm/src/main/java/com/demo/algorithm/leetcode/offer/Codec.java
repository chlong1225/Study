package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/25.
 * description : 剑指Offer37. 序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列/反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示：输入输出格式与LeetCode目前使用的方式一致，详情请参阅LeetCode序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 示例：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        List<TreeNode> dates = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        dates.add(root);
        while (dates.size() > 0) {
            int count = 0;
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if (node == null) {
                    builder.append("null,");
                } else {
                    builder.append(node.val + ",");
                    if (node.left != null) {
                        count++;
                    }
                    if (node.right != null) {
                        count++;
                    }
                    next.add(node.left);
                    next.add(node.right);
                }
            }
            dates.clear();
            if (count > 0) {
                dates.addAll(next);
            }
            next.clear();
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("]");
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || "[]".equals(data)) {
            return null;
        }
        //去掉前后空格
        String[] split = data.substring(1, data.length() - 1).split(",");
        int length = split.length;
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(split[index]));
        index++;
        List<TreeNode> dates = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        dates.add(root);
        while (index < length) {
            for (int i = 0; i < dates.size(); i++) {
                TreeNode node = dates.get(i);
                if ("null".equals(split[index])) {
                    node.left = null;
                } else {
                    TreeNode tem = new TreeNode(Integer.parseInt(split[index]));
                    node.left = tem;
                    next.add(tem);
                }
                index++;
                if (index >= length) {
                    break;
                }
                if ("null".equals(split[index])) {
                    node.right = null;
                } else {
                    TreeNode tem = new TreeNode(Integer.parseInt(split[index]));
                    node.right = tem;
                    next.add(tem);
                }
                index++;
                if (index >= length) {
                    break;
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return root;
    }
}
