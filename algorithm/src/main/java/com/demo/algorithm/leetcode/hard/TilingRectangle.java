package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/6/8
 * @author chenglong
 * description : 铺瓷砖
 *
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 * 房子的客厅大小为n x m，为保持极简的风格，需要使用尽可能少的正方形瓷砖来铺盖地面。
 * 假设正方形瓷砖的规格不限，边长都是整数。
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 *
 * 示例 1：
 * 输入：n = 2, m = 3
 * 输出：3
 * 解释：3 块地砖就可以铺满卧室。
 *      2 块 1x1 地砖
 *      1 块 2x2 地砖
 *
 * 示例 2：
 * 输入：n = 5, m = 8
 * 输出：5
 *
 * 示例 3：
 * 输入：n = 11, m = 13
 * 输出：6
 *
 * 提示：
 * 1 <= n <= 13
 * 1 <= m<= 13
 */
public class TilingRectangle {

    private int min;

    public int tilingRectangle(int n, int m) {
        min = m * n;
        //用于记录当前位置是否被铺满
        boolean[][] marks = new boolean[m][n];
        dfs(0, 0, marks, 0);
        return min;
    }

    private void dfs(int startX, int startY, boolean[][] marks, int count) {
        int m = marks.length;
        int n = marks[0].length;
        if (count >= min) {
            return;
        }
        if (startY == n) {
            //此时当前行已经平铺完了，需要搜索下一行
            boolean hasFind = false;
            for (int i = startX + 1; i < m; i++) {
                if (hasFind) {
                    break;
                }
                for (int j = 0; j < n; j++) {
                    if (!marks[i][j]) {
                        hasFind = true;
                        startX = i;
                        startY = j;
                        break;
                    }
                }
            }
            if (!hasFind) {
                min = count;
                return;
            }
        }
        //从点(startX,startY)开始搜索可以覆盖的最大瓷砖
        int size = getMaxSize(startX, startY, marks);
        for (int i = size; i >= 1; i--) {
            //依次遍历瓷砖的大小
            for (int j = startX; j < startX + i; j++) {
                for (int k = startY; k < startY + i; k++) {
                    marks[j][k] = true;
                }
            }
            dfs(startX, startY + i, marks, count + 1);
            //状态回溯
            for (int j = startX; j < startX + i; j++) {
                for (int k = startY; k < startY + i; k++) {
                    marks[j][k] = false;
                }
            }
        }
    }

    private int getMaxSize(int startX, int startY, boolean[][] marks) {
        int m = marks.length;
        int n = marks[0].length;
        int lastX = m - startX;
        int lastY = n - startY;
        int max = Math.min(lastX, lastY);
        int count = 1;
        for (int i = 1; i < max; i++) {
            int endX = startX + i;
            int endY = startY + i;
            boolean has = false;
            for (int j = startX; j <= endX; j++) {
                if (marks[j][endY]) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                for (int j = startY; j <= endY; j++) {
                    if (marks[endX][j]) {
                        has = true;
                        break;
                    }
                }
            }
            if (has) {
                return count;
            } else {
                count++;
            }
        }
        return count;
    }
}
