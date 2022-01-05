package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/1/3.
 * description : 猫和老鼠
 *
 * 两位玩家分别扮演猫和老鼠，在一张无向图上进行游戏，两人轮流行动。
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点b组成。
 * 老鼠从节点1开始，第一个出发；猫从节点2开始，第二个出发。在节点0处有一个洞。
 * 在每个玩家的行动中，他们必须沿着图中与所在当前位置连通的一条边移动。
 * 例如，如果老鼠在节点1，那么它必须移动到 graph[1] 中的任一节点。
 * 此外，猫无法移动到洞中（节点 0）。
 * 然后，游戏在出现以下三种情形之一时结束：
 * 如果猫和老鼠出现在同一个节点，猫获胜。
 * 如果老鼠到达洞中，老鼠获胜。
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
 * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 * 如果老鼠获胜，则返回 1；
 * 如果猫获胜，则返回 2；
 * 如果平局，则返回 0 。
 *
 * 示例 1：
 * 输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * 输出：0
 *
 * 示例 2：
 * 输入：graph = [[1,3],[0],[3],[0,2]]
 * 输出：1
 *
 * 提示：
 * 3 <= graph.length <= 50
 * 1<= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] 互不相同
 * 猫和老鼠在游戏中总是移动
 */
public class CatAndMouse {

    //老鼠获胜
    private static final int MOUSE_WIN = 1;
    //猫获胜
    private static final int CAT_WIN = 2;
    //平局
    private static final int DRAW = 0;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        //使用深度遍历+记忆
        /**
         * i：老鼠的位置
         * j：猫的位置
         * k：移动的次数，最大2*n
         * marks[i][j][k]：记录当前位置，当亲移动次数的结果
         */
        int[][][] marks = new int[n][n][2 * n];
        //1，初始化状态
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2 * n; k++) {
                    if (i == 0) {
                        marks[i][j][k] = MOUSE_WIN;
                    } else if (i == j) {
                        marks[i][j][k] = CAT_WIN;
                    } else {
                        marks[i][j][k] = -1;
                    }
                }
            }
        }
        //深度dfs搜索
        return dfs(1, 2, 0, n, graph, marks);
    }

    private int dfs(int mouse, int cat, int step, int n, int[][] graph, int[][][] marks) {
        if (step >= 2 * n) {
            return DRAW;
        }
        if (marks[mouse][cat][step] != -1) {
            return marks[mouse][cat][step];
        }
        if (step % 2 == 0) {
            //偶数次数移动，此时老鼠移动
            //老鼠可以移动的位置
            int[] datas = graph[mouse];
            int length = datas.length;
            int result = CAT_WIN;
            for (int i = 0; i < length; i++) {
                //尝试走下一个节点。遍历时遇到老鼠获胜时直接返回老鼠获胜，遇到平局赋值后继续遍历。
                int next = dfs(datas[i], cat, step + 1, n, graph, marks);
                if (next == MOUSE_WIN) {
                    marks[mouse][cat][step] = MOUSE_WIN;
                    return MOUSE_WIN;
                }
                if (next == DRAW) {
                    result = DRAW;
                }
            }
            marks[mouse][cat][step] = result;
            return result;
        } else {
            //奇数次猫移动
            int[] datas = graph[cat];
            int length = datas.length;
            int result = MOUSE_WIN;
            for (int i = 0; i < length; i++) {
                if (datas[i] == 0) {
                    continue;
                }
                int next = dfs(mouse, datas[i], step + 1, n, graph, marks);
                if (next == CAT_WIN) {
                    marks[mouse][cat][step] = CAT_WIN;
                    return CAT_WIN;
                }
                if (next == DRAW) {
                    result = DRAW;
                }
            }
            marks[mouse][cat][step] = result;
            return result;
        }
    }
}
