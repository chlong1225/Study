package com.demo.algorithm.leetcode.interview;

import java.util.Arrays;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题08.13. 堆箱子
 *
 * 堆箱子。给你一堆n个箱子，箱子宽wi、深di、高hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。
 * 实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
 * 输入使用数组[wi, di, hi]表示每个箱子。
 *
 * 示例1:
 *  输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
 *  输出：6
 *
 * 示例2:
 *  输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
 *  输出：10
 *
 * 提示:
 * 箱子的数目不大于3000个。
 */
public class PileBox {

    public int pileBox(int[][] box) {
        if (box == null || box.length == 0) {
            return 0;
        }
        Arrays.sort(box, (o1, o2) -> o1[0] - o2[0]);
        int length = box.length;
        //记录包含当前箱子的最大值，对应最大边界box[i]
        int[] marks = new int[length];
        marks[0] = box[0][2];
        for (int i = 1; i < length; i++) {
            //当前箱子的大小。marks[i]必须包含当前箱子
            int[] cur = box[i];
            int max = cur[2];
            for (int j = i - 1; j >= 0; j--) {
                int[] pre = box[j];
                if (cur[0] > pre[0] && cur[1] > pre[1] && cur[2] > pre[2]) {
                    if (max < cur[2] + marks[j]) {
                        max = cur[2] + marks[j];
                    }
                }
            }
            //包含当前箱子的最大高度
            marks[i] = max;
        }
        //最大高度时，底部可能不是最大宽度的箱子
        int max = marks[0];
        for (int i = 0; i < length; i++) {
            if (marks[i] > max) {
                max = marks[i];
            }
        }
        return max;
    }
}
