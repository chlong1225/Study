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
        int m = grid.length;
        int n = grid[0].length();
        //1，统计所有的钥匙的数量与起点并给钥匙进行编号：0～count-1
        int[] indexs = new int[26];
        int count = 0;
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'z') {
                    indexs[grid[i].charAt(j) - 'a'] = count;
                    count++;
                }
                if (grid[i].charAt(j) == '@') {
                    startX = i;
                    startY = j;
                }
            }
        }
        //2，使用二进制记录钥匙的出现。
        int target = (1 << count) - 1;
        int step = 0;
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        curs.add(new int[]{startX, startY, 0});
        List<Integer>[][] marks = new List[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = new ArrayList<>();
            }
        }
        marks[startX][startY].add(0);
        while (curs.size() > 0) {
            step++;
            for (int i = 0; i < curs.size(); i++) {
                int[] cur = curs.get(i);
                for (int j = 0; j < OFFSET.length; j++) {
                    int nx = cur[0] + OFFSET[j][0];
                    int ny = cur[1] + OFFSET[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        char c = grid[nx].charAt(ny);
                        if (c == '#') {
                            //此时为墙壁无法通过
                            continue;
                        }
                        if (c == '.' || c == '@') {
                            //空房间或回到起点
                            if (marks[nx][ny].size() == 0) {
                                //当前位置没有经过
                                marks[nx][ny].add(cur[2]);
                                nexts.add(new int[]{nx, ny, cur[2]});
                            } else {
                                List<Integer> keys = marks[nx][ny];
                                if (checkValidKey(keys, cur[2])) {
                                    addKey(keys, cur[2]);
                                    nexts.add(new int[]{nx, ny, cur[2]});
                                }
                            }
                        } else if (c >= 'a' && c <= 'z') {
                            //此时为钥匙
                            int index = indexs[c - 'a'];
                            int num = cur[2] | (1 << index);
                            if (num == target) {
                                return step;
                            }
                            if (marks[nx][ny].size() == 0) {
                                marks[nx][ny].add(num);
                                nexts.add(new int[]{nx, ny, num});
                            } else {
                                List<Integer> keys = marks[nx][ny];
                                if (checkValidKey(keys, num)) {
                                    addKey(keys, num);
                                    nexts.add(new int[]{nx, ny, num});
                                }
                            }
                        } else {
                            //此时为锁，需要判断是否可以打开通过
                            int findIndex = indexs[c - 'A'];
                            if ((cur[2] & (1 << findIndex)) != 0) {
                                if (marks[nx][ny].size() == 0) {
                                    //当前位置没有经过
                                    marks[nx][ny].add(cur[2]);
                                    nexts.add(new int[]{nx, ny, cur[2]});
                                } else {
                                    List<Integer> keys = marks[nx][ny];
                                    if (checkValidKey(keys, cur[2])) {
                                        addKey(keys, cur[2]);
                                        nexts.add(new int[]{nx, ny, cur[2]});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return -1;
    }

    private void addKey(List<Integer> keys, int key) {
        for (int i = keys.size() - 1; i >= 0; i--) {
            int cur = keys.get(i);
            if ((cur & key) == cur) {
                keys.remove(i);
            }
        }
        keys.add(key);
    }

    private boolean checkValidKey(List<Integer> keys, int compare) {
        for (int i = 0; i < keys.size(); i++) {
            int cur = keys.get(i);
            if ((compare & cur) == compare) {
                return false;
            }
        }
        return true;
    }
}
