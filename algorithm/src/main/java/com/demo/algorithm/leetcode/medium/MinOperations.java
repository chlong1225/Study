package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/2
 * @author chenglong
 * description : 移动所有球到每个盒子所需的最小操作数
 *
 * 有n个盒子。给你一个长度为n的二进制字符串boxes，其中boxes[i]的值为'0'表示第i个盒子是空的，而boxes[i]的值为'1'表示盒子里有一个小球。
 * 在一步操作中，你可以将一个小球从某个盒子移动到一个与之相邻的盒子中。第i个盒子和第j个盒子相邻需满足abs(i-j)==1。注意，操作执行后，某些盒子中可能会存在不止一个小球。
 * 返回一个长度为n的数组answer，其中answer[i]是将所有小球移动到第i个盒子所需的最小操作数。
 * 每个answer[i]都需要根据盒子的初始状态进行计算。
 *
 * 示例 1：
 * 输入：boxes = "110"
 * 输出：[1,1,3]
 * 解释：每个盒子对应的最小操作数如下：
 * 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
 * 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
 * 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
 *
 * 示例 2：
 * 输入：boxes = "001011"
 * 输出：[11,8,5,4,3,4]
 *
 * 提示：
 * n == boxes.length
 * 1 <= n <= 2000
 * boxes[i] 为 '0' 或 '1'
 */
public class MinOperations {

    public int[] minOperations(String boxes) {
        int n = boxes.length();
        boolean[] marks = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                marks[i] = true;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (marks[j]) {
                    sum += Math.abs(j - i);
                }
            }
            ans[i] = sum;
        }
        return ans;
    }
}
