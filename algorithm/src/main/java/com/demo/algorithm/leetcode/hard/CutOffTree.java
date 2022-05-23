package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chl on 2022/5/23.
 * description : 为高尔夫比赛砍树
 *
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个m x n的矩阵表示，在这个矩阵中：
 * 0表示障碍，无法触碰
 * 1表示地面，可以行走
 * 比1大的数表示有树的单元格，可以行走，数值表示树的高度
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
 * 你将从(0,0)点开始工作，返回你砍完所有树需要走的最小步数。如果你无法砍完所有的树，返回 -1 。
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 *
 * 示例 1：
 * 输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
 * 输出：6
 * 解释：沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。
 *
 * 示例 2：
 * 输入：forest = [[1,2,3],[0,0,0],[7,6,5]]
 * 输出：-1
 * 解释：由于中间一行被障碍阻塞，无法访问最下面一行中的树。
 *
 * 示例 3：
 * 输入：forest = [[2,3,4],[0,0,5],[8,7,6]]
 * 输出：6
 * 解释：可以按与示例 1 相同的路径来砍掉所有的树。
 * (0,0) 位置的树，可以直接砍去，不用算步数。
 *
 * 提示：
 * m == forest.length
 * n == forest[i].length
 * 1 <= m, n <= 50
 * 0 <= forest[i][j] <= 10^9
 */
public class CutOffTree {

    private int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {
        //1，统计所有的树
        List<int[]> trees = new ArrayList<>();
        int m = forest.size();
        int n = forest.get(0).size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        //2，对树的高度进行排序
        if (trees.size() > 1) {
            Collections.sort(trees, (o1, o2) -> forest.get(o1[0]).get(o1[1]) - forest.get(o2[0]).get(o2[1]));
        }
        int sum = 0;
        //3，依次砍树
        int[] start = {0, 0};
        for (int i = 0; i < trees.size(); i++) {
            int[] find = trees.get(i);
            int step = getMinStep(start, find, forest);
            if (step == -1) {
                return -1;
            }
            //砍掉树并重置起点，扫描下一棵树
            forest.get(find[0]).set(find[1], 1);
            start = find;
            sum += step;
        }
        return sum;
    }

    private int getMinStep(int[] start, int[] find, List<List<Integer>> forest) {
        if (start[0] == find[0] && start[1] == find[1]) {
            return 0;
        }
        int m = forest.size();
        int n = forest.get(0).size();
        //查找树的高度
        boolean[][] marks = new boolean[m][n];
        List<int[]> dates = new ArrayList<>();
        dates.add(start);
        marks[start[0]][start[1]] = true;
        List<int[]> next = new ArrayList<>();
        int step = 0;
        while (dates.size() > 0) {
            step++;
            for (int i = 0; i < dates.size(); i++) {
                int[] cur = dates.get(i);
                for (int j = 0; j < offsets.length; j++) {
                    int nx = cur[0] + offsets[j][0];
                    int ny = cur[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        //防止偏移越界
                        if (nx == find[0] && ny == find[1]) {
                            //刚好扫描到目标位置
                            return step;
                        }
                        //之前已经扫描过
                        if (marks[nx][ny]) {
                            continue;
                        }
                        //当前位置为障碍，无法到达
                        if (forest.get(nx).get(ny) == 0) {
                            continue;
                        }
                        next.add(new int[]{nx, ny});
                        marks[nx][ny] = true;
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return -1;
    }
}
