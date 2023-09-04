package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.TreeNode;

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
        builder.append(dfs(root));
        builder.append("]");
        return builder.toString();
    }

    private String dfs(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        if (root.left != null) {
            builder.append(",");
            builder.append(dfs(root.left));
        }
        if (root.right != null) {
            builder.append(",");
            builder.append(dfs(root.right));
        }
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || "[]".equals(data)) {
            return null;
        }
        //获取树节点的值
        String[] split = data.substring(1, data.length() - 1).split(",");
        int n = split.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        TreeNode root = new TreeNode(nums[0]);
        if (n > 1) {
            buildTree(root, 1, n - 1, nums);
        }
        return root;
    }

    private void buildTree(TreeNode root, int start, int end, int[] nums) {
        if (start > end) {
            return;
        }
        int compare = root.val;
        if (start == end) {
            //此时只有一个节点
            if (nums[start] < compare) {
                root.left = new TreeNode(nums[start]);
            } else {
                root.right = new TreeNode(nums[start]);
            }
        } else {
            if (compare < nums[start]) {
                //此时只有右节点
                root.right = new TreeNode(nums[start]);
                buildTree(root.right, start + 1, end, nums);
            } else if (compare > nums[end]) {
                //此时只有左节点
                root.left = new TreeNode(nums[start]);
                buildTree(root.left, start + 1, end, nums);
            } else {
                //此时左右节点都有，查找右节点的起始位置
                int findIndex = -1;
                for (int i = start + 1; i <= end; i++) {
                    if (nums[i] > compare) {
                        findIndex = i;
                        break;
                    }
                }
                //此时左节点区间[start,findIndex-1]，右节点区间[findIndex,end]
                root.left = new TreeNode(nums[start]);
                buildTree(root.left, start + 1, findIndex - 1, nums);
                root.right = new TreeNode(nums[findIndex]);
                buildTree(root.right, findIndex + 1, end, nums);
            }
        }
    }
}
