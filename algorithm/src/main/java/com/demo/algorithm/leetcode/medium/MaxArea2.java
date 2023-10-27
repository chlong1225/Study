package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/10/27
 * @author chenglong
 * description : 切割后面积最大的蛋糕
 *
 * 矩形蛋糕的高度为h且宽度为w，给你两个整数数组horizontalCuts和verticalCuts，其中：
 * horizontalCuts[i]是从矩形蛋糕顶部到第i个水平切口的距离
 * verticalCuts[j]是从矩形蛋糕的左侧到第j个竖直切口的距离
 * 请你按数组horizontalCuts和verticalCuts中提供的水平和竖直位置切割后，请你找出面积最大的那份蛋糕，并返回其面积。
 * 由于答案可能是一个很大的数字，因此需要将结果对10^9 + 7 取余后返回。
 *
 * 示例 1：
 * 输入：h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * 输出：4
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
 *
 * 示例 2：
 * 输入：h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * 输出：6
 * 解释：上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。
 *
 * 示例 3：
 * 输入：h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * 输出：9
 *
 * 提示：
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length <= min(h - 1, 10^5)
 * 1 <= verticalCuts.length <= min(w - 1, 10^5)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * 题目数据保证 horizontalCuts 中的所有元素各不相同
 * 题目数据保证 verticalCuts 中的所有元素各不相同
 */
public class MaxArea2 {

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        int maxHeight = horizontalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            int tem = horizontalCuts[i] - horizontalCuts[i - 1];
            if (tem > maxHeight) {
                maxHeight = tem;
            }
        }
        if (h - horizontalCuts[horizontalCuts.length - 1] > maxHeight) {
            maxHeight = h - horizontalCuts[horizontalCuts.length - 1];
        }
        Arrays.sort(verticalCuts);
        int maxWidth = verticalCuts[0];
        for (int i = 1; i < verticalCuts.length; i++) {
            int tem = verticalCuts[i] - verticalCuts[i - 1];
            if (tem > maxWidth) {
                maxWidth = tem;
            }
        }
        if (w - verticalCuts[verticalCuts.length - 1] > maxWidth) {
            maxWidth = w - verticalCuts[verticalCuts.length - 1];
        }
        long base = 1;
        long area = base * maxWidth * maxHeight;
        int mod = 1000000007;
        return (int) (area % mod);
    }
}
