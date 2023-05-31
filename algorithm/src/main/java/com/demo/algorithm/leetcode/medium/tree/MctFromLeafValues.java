package com.demo.algorithm.leetcode.medium.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2023/5/31
 * @author chenglong
 * description : 叶值的最小代价生成树
 *
 * 给你一个正整数数组arr，考虑所有满足以下条件的二叉树：
 *
 * 每个节点都有0个或是2个子节点。
 * 数组arr中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个32位整数。
 * 如果一个节点有0个子节点，那么该节点为叶节点。
 *
 * 示例 1：
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。
 *
 * 示例 2：
 * 输入：arr = [4,11]
 * 输出：44
 *
 * 提示：
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个32位带符号整数，即小于2^31 。
 */
public class MctFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        int sum = 0;
        Deque<Integer> dates = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!dates.isEmpty() && dates.peek() <= arr[i]) {
                int y = dates.pop();
                if (dates.isEmpty() || dates.peek() > arr[i]) {
                    sum += (y * arr[i]);
                } else {
                    sum += (y * dates.peek());
                }
            }
            dates.push(arr[i]);
        }
        while (dates.size() >= 2) {
            int x = dates.pop();
            sum += (x * dates.peek());
        }
        return sum;
    }
}
