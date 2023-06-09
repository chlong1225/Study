package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/6/9
 * @author chenglong
 * description : 修改图中的边权
 *
 * 给你一个n个节点的无向带权连通图，节点编号为0到n-1，再给你一个整数数组edges，其中edges[i]=[ai, bi, wi]表示节点ai和bi之间有一条边权为wi的边。
 * 部分边的边权为-1（wi = -1），其他边的边权都为正数（wi > 0）。
 * 你需要将所有边权为-1的边都修改为范围[1, 2 * 109]中的正整数，使得从节点source到节点destination的最短距离为整数target。
 * 如果有多种修改方案可以使source和destination之间的最短距离等于target，你可以返回任意一种方案。
 * 如果存在使source到destination最短距离为target的方案，请你按任意顺序返回包含所有边的数组（包括未修改边权的边）。如果不存在这样的方案，请你返回一个空数组。
 * 注意：你不能修改一开始边权为正数的边。
 *
 * 示例 1：
 * 输入：n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
 * 输出：[[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
 * 解释：上图展示了一个满足题意的修改方案，从 0 到 1 的最短距离为 5 。
 *
 * 示例 2：
 * 输入：n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
 * 输出：[]
 * 解释：上图是一开始的图。没有办法通过修改边权为 -1 的边，使得 0 到 2 的最短距离等于 6 ，所以返回一个空数组。
 *
 * 示例 3：
 * 输入：n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
 * 输出：[[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
 * 解释：上图展示了一个满足题意的修改方案，从 0 到 2 的最短距离为 6 。
 *
 * 提示：
 * 1 <= n <= 100
 * 1 <= edges.length <= n * (n - 1)/2
 * edges[i].length == 3
 * 0 <= ai, bi<n
 * wi=-1 或者 1 <= wi<= 10^7
 * ai!=bi
 * 0 <= source, destination < n
 * source != destination
 * 1 <= target <= 10^9
 * 输入的图是连通图，且没有自环和重边。
 */
public class ModifiedGraphEdges {

    private int[][] distances;
    private int mTarget;
    List<int[][]> result = new ArrayList<>();

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        int length = edges.length;
        //1，构建图的数据
        List<List<Integer>> dates = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates.add(new ArrayList<>());
        }
        distances = new int[n][n];
        mTarget = target;
        for (int i = 0; i < length; i++) {
            distances[edges[i][0]][edges[i][1]] = edges[i][2];
            distances[edges[i][1]][edges[i][0]] = edges[i][2];
            dates.get(edges[i][0]).add(edges[i][1]);
            dates.get(edges[i][1]).add(edges[i][0]);
        }
        //2，深度搜索可达路径
        List<Integer> path = new ArrayList<>();
        path.add(source);
        //标记已经走过的路径
        boolean[] marks = new boolean[n];
        marks[source] = true;
        dfs(path, dates, marks, destination);
        if (result.isEmpty()) {
            return new int[0][0];
        }
        //需要包含未修改的边
        int[][] tem = result.get(0);
        for (int i = 0; i < tem.length; i++) {
            distances[tem[i][0]][tem[i][1]] = tem[i][2];
            distances[tem[i][1]][tem[i][0]] = tem[i][2];
        }
        for (int i = 0; i < length; i++) {
            edges[i][2] = distances[edges[i][0]][edges[i][1]];
            if (edges[i][2] < 0) {
                edges[i][2] = 1;
            }
        }
        return edges;
    }

    private void dfs(List<Integer> path, List<List<Integer>> dates, boolean[] marks, int destination) {
        //当前位置
        int cur = path.get(path.size() - 1);
        //下一步可达的位置
        List<Integer> nexts = dates.get(cur);
        if (nexts.size() > 0) {
            for (int i = 0; i < nexts.size(); i++) {
                if (result.size() > 0) {
                    return;
                }
                int next = nexts.get(i);
                if (marks[next]) {
                    continue;
                }
                if (next == destination) {
                    List<Integer> tem = new ArrayList<>(path);
                    tem.add(destination);
                    checkPath(tem);
                    continue;
                }
                path.add(next);
                marks[next] = true;
                dfs(path, dates, marks, destination);
                //回溯
                path.remove(path.size() - 1);
                marks[next] = false;
            }
        }
    }

    private void checkPath(List<Integer> path) {
        int sum = 0;
        int count = 0;
        int size = path.size();
        for (int i = 0; i < size - 1; i++) {
            int distance = distances[path.get(i)][path.get(i + 1)];
            if (distance > 0) {
                sum += distance;
                count++;
            }
        }
        if (sum == mTarget) {
            if (count == size - 1) {
                //此时所有的权重都不需要修改，并且求和满足
                int[][] dates = new int[size - 1][3];
                for (int i = 0; i < size - 1; i++) {
                    dates[i] = new int[]{path.get(i), path.get(i + 1), distances[path.get(i)][path.get(i + 1)]};
                }
                result.add(dates);
            }
        } else if (sum < mTarget) {
            if (count < size - 1) {
                //需要修改添加的数
                int addAll = mTarget - sum;
                //修改的边
                int line = size - 1 - count;
                if (addAll >= line) {
                    //依次改为1，最后一个改为1+addAll-line
                    int[][] dates = new int[size - 1][3];
                    for (int i = 0; i < size - 1; i++) {
                        int distance = distances[path.get(i)][path.get(i + 1)];
                        if (distance > 0) {
                            dates[i] = new int[]{path.get(i), path.get(i + 1), distance};
                        } else {
                            if (line == 1) {
                                dates[i] = new int[]{path.get(i), path.get(i + 1), addAll};
                            } else {
                                dates[i] = new int[]{path.get(i), path.get(i + 1), 1};
                                line--;
                                addAll--;
                            }
                        }
                    }
                    result.add(dates);
                }
            }
        }
    }
}
