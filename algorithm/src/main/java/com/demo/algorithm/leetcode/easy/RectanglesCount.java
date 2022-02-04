package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/2/4.
 * description : 可以形成最大正方形的矩形数目
 *
 * 给你一个数组rectangles ，其中rectangles[i]=[li, wi]表示第i个矩形的长度为li、宽度为wi。
 * 如果存在k同时满足k<=li和k<= wi ，就可以将第i个矩形切成边长为k的正方形。
 * 例如，矩形[4,6]可以切成边长最大为4的正方形。
 * 设maxLen为可以从矩形数组rectangles切分得到的最大正方形的边长。
 * 请你统计有多少个矩形能够切出边长为maxLen的正方形，并返回矩形数目 。
 *
 * 示例 1：
 * 输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
 * 输出：3
 * 解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
 * 最大正方形的边长为5，可以由3个矩形切分得到。
 *
 * 示例 2：
 * 输入：rectangles=[[2,3],[3,7],[4,3],[3,7]]
 * 输出：3
 *
 * 提示：
 * 1 <= rectangles.length <= 1000
 * rectangles[i].length == 2
 * 1 <= li, wi <= 10^9
 * li != wi
 */
public class RectanglesCount {

    public int countGoodRectangles(int[][] rectangles) {
        int length = rectangles.length;
        int max = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            int size = Math.min(rectangles[i][0], rectangles[i][1]);
            if (max < size) {
                max = size;
                count = 1;
            } else if (max == size) {
                count++;
            }
        }
        return count;
    }

}
