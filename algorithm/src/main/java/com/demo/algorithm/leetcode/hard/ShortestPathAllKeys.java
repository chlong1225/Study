package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/11/10
 * @author chenglong
 * description : 获取所有钥匙的最短路径
 *
 * 给定一个二维网格grid，其中：
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@'是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * 假设k为钥匙/锁的个数，且满足1<=k<=6，字母表中的前k个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。
 * 另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回-1。
 *
 * 示例 1：
 * 输入：grid = ["@.a.#","###.#","b.A.B"]
 * 输出：8
 * 解释：目标是获得所有钥匙，而不是打开所有锁。
 *
 * 示例 2：
 * 输入：grid = ["@..aA","..B#.","....b"]
 * 输出：6
 *
 * 示例 3:
 * 输入: grid = ["@Aa"]
 * 输出: -1
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j]只含有'.','#','@','a'-'f'以及'A'-'F'
 * 钥匙的数目范围是[1, 6]
 * 每个钥匙都对应一个不同的字母
 * 每个钥匙正好打开一个对应的锁
 */
public class ShortestPathAllKeys {

    //分别对应左上右下的偏移
    private static final int[][] OFFSET = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int shortestPathAllKeys(String[] grid) {
        int min = Integer.MAX_VALUE;
        int m = grid.length;
        int n = grid[0].length();
        //1，统计钥匙的数量与起点
        int target = 0;
        List<int[]> dates = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c >= 'a' && c <= 'f') {
                    target |= (1 << (c - 'a'));
                }
                if (c == '@') {
                    //当前位置为起点
                    dates.add(new int[]{i, j, 0, 0});
                }
            }
        }
        //分别对应位置(i,j),钥匙数k。marks[i][j][k]:移动的步数
        int[][][] marks = new int[m][n][target + 1];
        for (int i = 0; i < dates.size(); i++) {
            int[] cur = dates.get(i);
            marks[cur[0]][cur[1]][cur[2]] = cur[3];
        }
        List<int[]> next = new ArrayList<>();
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                //当前位置(x,y),当前步数step，当前钥匙数，使用二进制数表示
                int x = dates.get(i)[0];
                int y = dates.get(i)[1];
                int step = dates.get(i)[2];
                int num = dates.get(i)[3];
                if (step + 1 >= min) {
                    //下一步的数量已经超过最小步数时，后面的数据无效
                    continue;
                }
                for (int j = 0; j < OFFSET.length; j++) {
                    int nx = x + OFFSET[j][0];
                    int ny = y + OFFSET[j][1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        //移动越界，当前移动无效
                        continue;
                    }
                    char c = grid[nx].charAt(ny);
                    if (c == '#' || c == '@') {
                        //移动到墙壁或再次回到起点，当前移动无效
                        continue;
                    }
                    if (c >= 'a' && c <= 'f') {
                        //当前为钥匙
                        int nextNum = num | (1 << (c - 'a'));
                        if (marks[nx][ny][num] != 0 && marks[nx][ny][nextNum] <= step + 1) {
                            //之前搜索过
                            continue;
                        }
                        marks[nx][ny][nextNum] = step + 1;
                        next.add(new int[]{nx, ny, step + 1, nextNum});
                        if (nextNum == target) {
                            if (min > target + 1) {
                                min = target + 1;
                            }
                        }
                    }
                    if (c >= 'A' && c <= 'F') {
                        //当前位置为锁，需要判断是否找到钥匙
                        int tem = 1 << (c - 'A');
                        if ((tem & num) == 0) {
                            //没有找到钥匙
                            continue;
                        }
                        //下一步数据（nx，ny，num，step+1）
                        if (marks[nx][ny][num] != 0 && marks[nx][ny][num] <= step + 1) {
                            continue;
                        }
                        marks[nx][ny][num] = step + 1;
                        next.add(new int[]{nx, ny, step + 1, num});
                    }
                    if (c == '.') {
                        //空房间
                        if (marks[nx][ny][num] != 0 && marks[nx][ny][num] <= step + 1) {
                            continue;
                        }
                        marks[nx][ny][num] = step + 1;
                        next.add(new int[]{nx, ny, step + 1, num});
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
