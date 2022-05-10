package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2022/5/10
 * @author chenglong
 * description : 猫和老鼠II
 *
 * 一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
 * 它们所处的环境设定是一个rows x cols的方格grid，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
 *
 * 玩家由字符'C'（代表猫）和'M'（代表老鼠）表示。
 * 地板由字符'.'表示玩家可以通过这个格子。
 * 墙用字符'#'表示玩家不能通过这个格子。
 * 食物用字符'F'表示玩家可以通过这个格子。
 * 字符'C'，'M'和'F'在grid中都只会出现一次。
 * 猫和老鼠按照如下规则移动：
 * 老鼠先移动，然后两名玩家轮流移动。
 * 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出grid。
 * catJump和mouseJump是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
 * 它们可以停留在原地。
 * 老鼠可以跳跃过猫的位置。
 *
 * 游戏有4种方式会结束：
 * 如果猫跟老鼠处在相同的位置，那么猫获胜。
 * 如果猫先到达食物，那么猫获胜。
 * 如果老鼠先到达食物，那么老鼠获胜。
 * 如果老鼠不能在1000次操作以内到达食物，那么猫获胜。
 * 给你rows x cols的矩阵grid和两个整数catJump和mouseJump，双方都采取最优策略，如果老鼠获胜，那么请你返回true，否则返回 false。
 *
 * 示例 1：
 * 输入：grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * 输出：true
 * 解释：猫无法抓到老鼠，也没法比老鼠先到达食物。
 *
 * 示例 2：
 * 输入：grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * 输出：true
 *
 * 示例 3：
 * 输入：grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * 输出：false
 *
 * 示例 4：
 * 输入：grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
 * 输出：false
 *
 * 示例 5：
 * 输入：grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
 * 输出：true
 *
 * 提示：
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] 只包含字符'C'，'M'，'F'，'.'和'#'。
 * grid中只包含一个'C'，'M'和'F'。
 * 1 <= catJump, mouseJump <= 8
 */
public class CanMouseWin2 {

    //最大的限制步数
    private static final int MAX_STEP = 1000;
    //用于将二维转换为一维
    private static final int MOD = 10;
    //记录老鼠和猫位置对应的执行次数
    private int[][][][] marks;


    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int m = grid.length;
        int n = grid[0].length();
        marks = new int[m][n][m][n];
        //1，查找食物的位置，猫和老鼠的初始位置
        int[] cat = new int[2];
        int[] mouse = new int[2];
        int[] food = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char tem = grid[i].charAt(j);
                if (tem == 'C') {
                    cat[0] = i;
                    cat[1] = j;
                } else if (tem == 'M') {
                    mouse[0] = i;
                    mouse[1] = j;
                } else if (tem == 'F') {
                    food[0] = i;
                    food[1] = j;
                }
            }
        }
        //2，遍历查找食物左上右下四个方向可达的位置，防止有墙壁挡住
        int top = food[0];
        int bottom = top;
        int left = food[1];
        int right = left;
        for (int i = food[0] - 1; i >= 0; i--) {
            if (grid[i].charAt(food[1]) == '#') {
                break;
            } else {
                top = i;
            }
        }
        for (int i = food[0] + 1; i < m; i++) {
            if (grid[i].charAt(food[1]) == '#') {
                break;
            } else {
                bottom = i;
            }
        }
        for (int i = food[1] - 1; i >= 0; i--) {
            if (grid[food[0]].charAt(i) == '#') {
                break;
            } else {
                left = i;
            }
        }
        for (int i = food[1] + 1; i < n; i++) {
            if (grid[food[0]].charAt(i) == '#') {
                break;
            } else {
                right = i;
            }
        }
        //3，广度优先搜索遍历所有场景
        int step = 0;
        List<int[][]> dates = new ArrayList<>();
        dates.add(new int[][]{mouse, cat});
        List<int[][]> next = new ArrayList<>();
        while (step <= MAX_STEP && dates.size() > 0) {
            //3.1，优先尝试判断老鼠是否可以获取食物
            for (int i = 0; i < dates.size(); i++) {
                //当前老鼠所在的位置
                int[] curMouse = dates.get(i)[0];
                if (food[0] == curMouse[0]) {
                    //食物与老鼠在同一水平方向
                    if (curMouse[1] >= left && curMouse[1] <= right) {
                        if (Math.abs(curMouse[1] - food[1]) <= mouseJump) {
                            return true;
                        }
                    }
                }
                if (food[1] == curMouse[1]) {
                    //食物与老鼠在同一垂直方向
                    if (curMouse[0] >= top && curMouse[0] <= bottom) {
                        if (Math.abs(curMouse[0] - food[0]) <= mouseJump) {
                            return true;
                        }
                    }
                }
            }
            //3.2，尝试判断猫是否可以获取食物
            for (int i = 0; i < dates.size(); i++) {
                //当前猫的位置
                int[] curCat = dates.get(i)[1];
                if (food[0] == curCat[0]) {
                    //食物与猫在同一水平方向
                    if (curCat[1] >= left && curCat[1] <= right) {
                        if (Math.abs(curCat[1] - food[1]) <= catJump) {
                            return false;
                        }
                    }
                }
                if (food[1] == curCat[1]) {
                    //食物与猫在同一垂直方向
                    if (curCat[0] >= top && curCat[0] <= bottom) {
                        if (Math.abs(curCat[0] - food[0]) <= catJump) {
                            return false;
                        }
                    }
                }
            }
            step++;
            //3.3，当前轮次猫和老鼠都不能够找到食物，遍历所有可能移动的场景
            for (int i = 0; i < dates.size(); i++) {
                int[] curMouse = dates.get(i)[0];
                int[] curCat = dates.get(i)[1];
                //3.4，先枚举mouses
                //向左边跳跃
                int end = Math.max(0, curMouse[1] - mouseJump);
                for (int j = curMouse[1]; j >= end; j--) {
                    if (grid[curMouse[0]].charAt(j) == '#') {
                        break;
                    } else {
                        next.addAll(jumpCat(step, curCat, catJump, grid, new int[]{curMouse[0], j}));
                    }
                }
                //向右边跳跃
                end = Math.min(n - 1, curMouse[1] + mouseJump);
                for (int j = curMouse[1] + 1; j <= end; j++) {
                    if (grid[curMouse[0]].charAt(j) == '#') {
                        break;
                    } else {
                        next.addAll(jumpCat(step, curCat, catJump, grid, new int[]{curMouse[0], j}));
                    }
                }
                //向上边跳跃
                end = Math.max(0, curMouse[0] - mouseJump);
                for (int j = curMouse[0] - 1; j >= end; j--) {
                    if (grid[j].charAt(curMouse[1]) == '#') {
                        break;
                    } else {
                        next.addAll(jumpCat(step, curCat, catJump, grid, new int[]{j, curMouse[1]}));
                    }
                }
                //向下边跳跃
                end = Math.min(m - 1, curMouse[0] + mouseJump);
                for (int j = curMouse[0] + 1; j <= end; j++) {
                    if (grid[j].charAt(curMouse[1]) == '#') {
                        break;
                    } else {
                        next.addAll(jumpCat(step, curCat, catJump, grid, new int[]{j, curMouse[1]}));
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }

        return false;
    }

    //循环遍历猫的跳跃
    private List<int[][]> jumpCat(int step, int[] curCat, int catJump, String[] grid, int[] mouse) {
        List<int[][]> result = new ArrayList<>();
        //枚举猫的跳跃
        //1，向左边跳跃
        int end = Math.max(0, curCat[1] - catJump);
        for (int i = curCat[1]; i >= end; i--) {
            if (grid[curCat[0]].charAt(i) == '#') {
                break;
            } else {
                //跳跃后的位置：{curCat[0],i}
                if (mouse[0] == curCat[0] && mouse[1] == i) {
                    return new ArrayList<>();
                }

            }
        }


        return result;
    }
}
