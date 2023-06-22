package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chl on 2023/6/22.
 * description : 面试题 16.19.水域大小
 *
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
 * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
 * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 */
public class PondSizes {

    private final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};

    public int[] pondSizes(int[][] land) {
        int m = land.length;
        int n = land[0].length;
        List<Integer> dates = new ArrayList<>();
        boolean[][] marks = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0 && (!marks[i][j])) {
                    List<int[]> paths = getPath(i, j, land, marks);
                    dates.add(paths.size());
                }
            }
        }
        Collections.sort(dates);
        int[] result = new int[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            result[i] = dates.get(i);
        }
        return result;
    }

    private List<int[]> getPath(int startX, int startY, int[][] land, boolean[][] marks) {
        int m = land.length;
        int n = land[0].length;
        List<int[]> paths = new ArrayList<>();
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        paths.add(new int[]{startX, startY});
        marks[startX][startY] = true;
        curs.add(new int[]{startX, startY});
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int[] cur = curs.get(i);
                for (int j = 0; j < offsets.length; j++) {
                    int nx = cur[0] + offsets[j][0];
                    int ny = cur[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (land[nx][ny] == 0 && (!marks[nx][ny])) {
                            nexts.add(new int[]{nx, ny});
                            paths.add(new int[]{nx, ny});
                            marks[nx][ny] = true;
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return paths;
    }
}

