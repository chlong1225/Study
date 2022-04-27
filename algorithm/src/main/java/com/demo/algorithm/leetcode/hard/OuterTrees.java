package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chl on 2022/4/23.
 * description : 安装栅栏
 *
 * 在一个二维的花园中，有一些用(x, y)坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 *
 * 示例 1:
 * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * 解释:
 *
 * 示例 2:
 * 输入: [[1,2],[2,2],[4,2]]
 * 输出: [[1,2],[2,2],[4,2]]
 * 解释:
 * 即使树都在一条直线上，你也需要先用绳子包围它们。
 *
 * 注意:
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在0到100之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 */
public class OuterTrees {

    public int[][] outerTrees(int[][] trees) {
        if (trees == null || trees.length < 4) {
            return trees;
        }
        //1，对x进行排序
        Arrays.sort(trees, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int length = trees.length;
        List<Integer> heap = new ArrayList<>();
        heap.add(0);
        boolean[] marks = new boolean[length];
        //2，遍历获取凸包下半部分
        for (int i = 1; i < length; i++) {
            while (heap.size() > 1 && getArea(trees[heap.get(heap.size() - 2)], trees[heap.get(heap.size() - 1)], trees[i]) < 0) {
                marks[heap.get(heap.size() - 1)] = false;
                heap.remove(heap.size() - 1);
            }
            marks[i] = true;
            heap.add(i);
        }
        int m = heap.size();
        //3，遍历获取凸包上半部分
        for (int i = length - 2; i >= 0; i--) {
            if (marks[i]) {
                continue;
            }
            while (heap.size() > m && getArea(trees[heap.get(heap.size() - 2)], trees[heap.get(heap.size() - 1)], trees[i]) < 0) {
                marks[heap.get(heap.size() - 1)] = true;
                heap.remove(heap.size() - 1);
            }
            marks[i] = true;
            heap.add(i);
        }
        heap.remove(heap.size() - 1);
        int[][] result = new int[heap.size()][2];
        for (int i = 0; i < heap.size(); i++) {
            result[i] = trees[heap.get(i)];
        }
        return result;
    }

    private int getArea(int[] p, int[] q, int[] r) {
        return cross(subtraction(q, p), subtraction(r, q));
    }

    //向量相减
    private int[] subtraction(int[] a, int[] b) {
        return new int[]{a[0] - b[0], a[1] - b[1]};
    }

    //向量相乘
    private int cross(int[] a, int[] b) {
        return a[0] * b[1] - a[1] * b[0];
    }


}
