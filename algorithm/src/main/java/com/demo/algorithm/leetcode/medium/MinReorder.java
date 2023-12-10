package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/7
 * @author chenglong
 * description : 重新规划路线
 *
 * n座城市，从0到n-1编号，其间共有n-1条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * 路线用connections表示，其中connections[i]=[a,b]表示从城市a到b的一条有向路线。
 * 今年，城市0将会举办一场大型比赛，很多游客都想前往城市0。
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市0。返回需要变更方向的最小路线数。
 * 题目数据保证每个城市在重新规划路线方向后都能到达城市0。
 *
 * 示例 1：
 * 输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * 输出：3
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市0。
 *
 * 示例 2：
 * 输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * 输出：2
 * 解释：更改以红色显示的路线的方向，使每个城市都可以到达城市0。
 *
 * 示例 3：
 * 输入：n = 3, connections = [[1,0],[2,0]]
 * 输出：0
 *
 * 提示：
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 */
public class MinReorder {

    public int minReorder(int n, int[][] connections) {
        //1，构建城市之间关联的数据。dates1记录正向联通的关系，dates2记录反向联通，便于查找后续改变方向的路线
        List<List<Integer>> dates1 = new ArrayList<>(n);
        List<List<Integer>> dates2 = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dates1.add(new ArrayList<>());
            dates2.add(new ArrayList<>());
        }
        for (int i = 0; i < connections.length; i++) {
            dates1.get(connections[i][1]).add(connections[i][0]);
            dates2.get(connections[i][0]).add(connections[i][1]);
        }
        int count = 0;
        //使用bfs扫描所有的城市
        List<Integer> curs = new ArrayList<>();
        curs.add(0);
        boolean[] marks = new boolean[n];
        marks[0] = true;
        List<Integer> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                //从dates1中获取的数据是可以正常访问，不需要更改
                List<Integer> items1 = dates1.get(curs.get(i));
                if (items1.size() > 0) {
                    for (int j = 0; j < items1.size(); j++) {
                        int next = items1.get(j);
                        if (!marks[next]) {
                            nexts.add(next);
                            marks[next] = true;
                        }
                    }
                }
                //从dates2中获取的数据都是反向不可访问，需要更改
                List<Integer> items2 = dates2.get(curs.get(i));
                if (items2.size() > 0) {
                    for (int j = 0; j < items2.size(); j++) {
                        int next = items2.get(j);
                        if (!marks[next]) {
                            count++;
                            marks[next] = true;
                            nexts.add(next);
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return count;
    }
}
