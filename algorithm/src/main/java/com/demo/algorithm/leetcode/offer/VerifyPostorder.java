package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/14.
 * description : 剑指Offer33. 二叉搜索树的后序遍历序列
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 *
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 *
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 * 提示：
 * 数组长度 <= 1000
 */
public class VerifyPostorder {

    private int[] mPostorder;

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return false;
        }
        int length = postorder.length;
        if (length < 2) {
            return true;
        }
        mPostorder = postorder;
        /**
         * 后序遍历时。根节点在最后
         */
        int end = length - 1;
        int root = postorder[end];
        int middle = -1;
        for (int i = end - 1; i >= 0; i--) {
            if (mPostorder[i] < root) {
                middle = i;
                break;
            }
        }
        if (middle == -1) {
            //此时不存在比root更小的数。0~end-1都在右子树
            return dfs(0, end - 1, root, Integer.MAX_VALUE);
        }
        if (middle == end - 1) {
            //此时0~end-1都应该在左子树中
            return dfs(0, middle, Integer.MIN_VALUE, root);
        }
        return dfs(0, middle, Integer.MIN_VALUE, root) && dfs(middle + 1, end - 1, root, Integer.MAX_VALUE);
    }

    //判断start~end之间是否为二叉搜索树。取值限定范围：min~max
    private boolean dfs(int start, int end, int min, int max) {
        if (start > end) {
            return true;
        }
        if (start == end) {
            return mPostorder[start] > min && mPostorder[start] < max;
        }
        int root = mPostorder[end];
        if (root < min || root > max) {
            return false;
        }
        int middle = start - 1;
        for (int i = end - 1; i >= start; i--) {
            if (mPostorder[i] < root) {
                middle = i;
                break;
            }
        }
        if (middle == start - 1) {
            return dfs(start, end - 1, root, max);
        }
        if (middle == end - 1) {
            return dfs(start, middle, min, root);
        }
        return dfs(start, middle, min, root) && dfs(middle + 1, end - 1, root, max);
    }
}
