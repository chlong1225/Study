package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/5/11
 * @author chenglong
 * description : 序列化和反序列化二叉搜索树
 *
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数范围是 [0, 10^4]
 * 0 <= Node.val <= 10^4
 * 题目数据保证输入的树是一棵二叉搜索树。
 */
public class Codec {

    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        dfs(root, builder);
        builder.delete(builder.length() - 1, builder.length());
        builder.append("]");
        return builder.toString();
    }

    private void dfs(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        builder.append(root.val);
        builder.append(",");
        dfs(root.left, builder);
        dfs(root.right, builder);
    }

    public TreeNode deserialize(String data) {
        if ("[]".equals(data)) {
            return null;
        }
        String[] split = data.substring(1, data.length() - 1).split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        return buildTree(0, split.length - 1, nums);
    }

    private TreeNode buildTree(int start, int end, int[] data) {
        TreeNode node = new TreeNode(data[start]);
        if (start == end) {
            return node;
        }
        int left = start + 1;
        int right = end;
        while (left < right) {
            int middle = (right - left) / 2 + left;
            if (data[middle] > node.val) {
                right = middle - 1;
            } else {
                left = middle;
                if (data[left + 1] > node.val) {
                    break;
                }
            }
        }
        if (start + 1 <= left) {
            node.left = buildTree(start + 1, left, data);
        }
        if (left + 1 <= end) {
            node.right = buildTree(left + 1, end, data);
        }
        return node;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }
}
