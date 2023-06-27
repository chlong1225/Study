package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * Created by chl on 2022/12/10.
 * description :  堆叠长方体的最大高度
 *
 * 给你n个长方体cuboids，其中第i个长方体的长宽高表示为cuboids[i]=[widthi, lengthi, heighti]（下标从0开始）。请你从cuboids选出一个子集，并将它们堆叠起来。
 * 如果widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体i堆叠在长方体j上。
 * 你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
 * 返回堆叠长方体cuboids可以得到的最大高度 。
 *
 * 示例 1：
 * 输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * 输出：190
 * 解释：
 * 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
 * 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
 * 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
 * 总高度是 95 + 50 + 45 = 190 。
 *
 * 示例 2：
 * 输入：cuboids = [[38,25,45],[76,35,3]]
 * 输出：76
 * 解释：
 * 无法将任何长方体放在另一个上面。
 * 选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
 *
 * 示例 3：
 * 输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * 输出：102
 * 解释：
 * 重新排列长方体后，可以看到所有长方体的尺寸都相同。
 * 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
 * 堆叠长方体的最大高度为 6 * 17 = 102 。
 *
 * 提示：
 * n == cuboids.length
 * 1 <= n <= 100
 * 1 <= widthi, lengthi, heighti <= 100
 */
public class MaxHeight {

    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int i = 0; i < n; i++) {
            Arrays.sort(cuboids[i]);
        }
        Arrays.sort(cuboids, (a, b) -> a[0] + a[1] + a[2] - b[0] - b[1] - b[2]);
        int max = 0;
        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            marks[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[i][0] >= cuboids[j][0] && cuboids[i][1] >= cuboids[j][1] && cuboids[i][2] >= cuboids[j][2]) {
                    marks[i] = Math.max(marks[i], marks[j] + cuboids[i][2]);
                }
            }
            max = Math.max(max, marks[i]);
        }
        return max;
    }
}
