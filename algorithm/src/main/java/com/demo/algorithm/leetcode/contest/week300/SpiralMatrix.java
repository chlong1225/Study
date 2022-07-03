package com.demo.algorithm.leetcode.contest.week300;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/7/3.
 * description :  螺旋矩阵IV
 *
 * 给你两个整数：m和n，表示矩阵的维数。
 * 另给你一个整数链表的头节点 head 。
 * 请你生成一个大小为 m x n 的螺旋矩阵，矩阵包含链表中的所有整数。链表中的整数从矩阵左上角开始、顺时针按螺旋顺序填充。如果还存在剩余的空格，则用-1填充。
 * 返回生成的矩阵。
 *
 * 示例 1：
 * 输入：m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
 * 输出：[[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
 * 解释：上图展示了链表中的整数在矩阵中是如何排布的。
 * 注意，矩阵中剩下的空格用 -1 填充。
 *
 * 示例 2：
 * 输入：m = 1, n = 4, head = [0,1,2]
 * 输出：[[0,1,2,-1]]
 * 解释：上图展示了链表中的整数在矩阵中是如何从左到右排布的。
 * 注意，矩阵中剩下的空格用 -1 填充。
 *
 * 提示：
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 链表中节点数目在范围 [1, m * n] 内
 * 0 <= Node.val <= 1000
 */
public class SpiralMatrix {

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] result = new int[m][n];
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        int total = m * n;
        int count = 0;
        while (left <= right && top <= bottom) {
            //顶部从左到右填充
            for (int i = left; i <= right; i++) {
                count++;
                if (head == null) {
                    result[top][i] = -1;
                } else {
                    result[top][i] = head.val;
                    head = head.next;
                }
            }
            if (count >= total) {
                break;
            }
            //右边从上到下填充
            for (int i = top + 1; i <= bottom; i++) {
                count++;
                if (head == null) {
                    result[i][right] = -1;
                } else {
                    result[i][right] = head.val;
                    head = head.next;
                }
            }
            if (count >= total) {
                break;
            }
            //底部从右到左填充
            for (int i = right - 1; i >= left; i--) {
                count++;
                if (head == null) {
                    result[bottom][i] = -1;
                } else {
                    result[bottom][i] = head.val;
                    head = head.next;
                }
            }
            if (count >= total) {
                break;
            }
            //左边从下到上填充
            for (int i = bottom - 1; i >= top + 1; i--) {
                count++;
                if (head == null) {
                    result[i][left] = -1;
                } else {
                    result[i][left] = head.val;
                    head = head.next;
                }
            }
            if (count >= total) {
                break;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }
}
