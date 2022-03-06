package com.demo.algorithm.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chl on 2022/3/6.
 * description : 网格照明
 *
 * 在大小为n x n的网格grid上，每个单元格都有一盏灯，最初灯都处于关闭状态。
 * 给你一个由灯的位置组成的二维数组lamps，其中lamps[i]=[rowi,coli]表示打开位于grid[rowi][coli]的灯。即便同一盏灯可能在lamps中多次列出，不会影响这盏灯处于打开状态。
 * 当一盏灯处于打开状态，它将会照亮自身所在单元格以及同一行、同一列和两条对角线上的所有其他单元格 。
 * 另给你一个二维数组queries，其中queries[j]=[rowj, colj]。对于第j个查询，如果单元格[rowj, colj]是被照亮的，则查询结果为1 ，否则为0 。
 * 在第j次查询之后[按照查询的顺序]关闭位于单元格grid[rowj][colj]上及相邻8个方向上（与单元格 grid[rowi][coli]共享角或边）的任何灯。
 * 返回一个整数数组ans作为答案，ans[j]应等于第j次查询queries[j]的结果，1表示照亮，0表示未照亮。
 *
 * 示例 1：
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * 输出：[1,0]
 * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于[0, 0]和[4, 4]的灯。第0次查询检查 grid[1][1]是否被照亮（蓝色方框）。
 * 该单元格被照亮，所以 ans[0]=1 。然后关闭红色方框中的所有灯。
 * 第1次查询检查grid[1][0]是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1]=0 。然后关闭红色矩形中的所有灯。
 *
 * 示例 2：
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * 输出：[1,1]
 *
 * 示例 3：
 * 输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * 输出：[1,1,0]
 *
 * 提示：
 * 1 <= n <= 10^9
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == 2
 * 0 <= rowi, coli < n
 * queries[j].length == 2
 * 0 <= rowj, colj < n
 */
public class GridIllumination {

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        /**
         * 分析：由于n特别大，使用二维数组统计灯开关的状态会造成内存占用很大。
         * 而lamps很小，故直接构建位置与灯的对照表，每次都进行查询。
         */
        Map<Integer, Set<Integer>> rowmarks = new HashMap<>();
        Map<Integer,Set<Integer>> colMarks = new HashMap<>();
        Map<Integer, Set<Integer>> leftMarks = new HashMap<>();
        Map<Integer, Set<Integer>> rightMarks = new HashMap<>();
        int length = lamps.length;
        for (int i = 0; i < length; i++) {
            int row = lamps[i][0];
            int col = lamps[i][1];
            if (rowmarks.get(row) == null) {
                Set<Integer> item = new HashSet<>();
                item.add(col);
                rowmarks.put(row, item);
            } else {
                rowmarks.get(row).add(col);
            }
            if (colMarks.get(col) == null) {
                Set<Integer> item = new HashSet<>();
                item.add(row);
                colMarks.put(col, item);
            } else {
                colMarks.get(col).add(row);
            }
            int reduce = row - col;
            int add = row + col;
            if (leftMarks.get(reduce) == null) {
                Set<Integer> item = new HashSet<>();
                item.add(row);
                leftMarks.put(reduce, item);
            } else {
                leftMarks.get(reduce).add(row);
            }
            if (rightMarks.get(add) == null) {
                Set<Integer> item = new HashSet<>();
                item.add(row);
                rightMarks.put(add, item);
            } else {
                rightMarks.get(add).add(row);
            }
        }
        length = queries.length;
        int[] result = new int[length];
        //遍历查询
        for (int i = 0; i < length; i++) {
            int row = queries[i][0];
            int col = queries[i][1];
            //判断点：(row,col)是否与灯在同一行或同一列
            if (rowmarks.get(row) != null || colMarks.get(col) != null) {
                result[i] = 1;
            } else {
                //判断是否在对角线上
                int reduce = row - col;
                int add = row + col;
                if (leftMarks.get(reduce) != null || rightMarks.get(add) != null) {
                    result[i] = 1;
                } else {
                    result[i] = 0;
                }
            }
            //关闭(row,col)对应的9个位置上的灯
            for (int j = row - 1; j <= row + 1; j++) {
                for (int k = col - 1; k <= col + 1; k++) {
                    if (j >= 0 && j < n && k >= 0 && k < n) {
                        //防止越界无效数据
                        if (rowmarks.get(j) != null) {
                            if (rowmarks.get(j).contains(k)) {
                                rowmarks.get(j).remove(k);
                                if (rowmarks.get(j).size() == 0) {
                                    rowmarks.remove(j);
                                }
                            }
                        }
                        if (colMarks.get(k) != null) {
                            if (colMarks.get(k).contains(j)) {
                                colMarks.get(k).remove(j);
                                if (colMarks.get(k).size() == 0) {
                                    colMarks.remove(k);
                                }
                            }
                        }
                        int reduce = j - k;
                        int add = j + k;
                        if (leftMarks.get(reduce) != null) {
                            if (leftMarks.get(reduce).contains(j)) {
                                leftMarks.get(reduce).remove(j);
                                if (leftMarks.get(reduce).size() == 0) {
                                    leftMarks.remove(reduce);
                                }
                            }
                        }
                        if (rightMarks.get(add) != null) {
                            if (rightMarks.get(add).contains(j)) {
                                rightMarks.get(add).remove(j);
                                if (rightMarks.get(add).size() == 0) {
                                    rightMarks.remove(add);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
