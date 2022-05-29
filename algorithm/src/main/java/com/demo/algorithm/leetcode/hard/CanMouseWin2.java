package com.demo.algorithm.leetcode.hard;

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


    /**
     * 分别记录结果：未知；老鼠获胜；猫获胜
     */
    private static final int UN_KNOW = 0;
    private static final int MOUSE_WIN = 1;
    private static final int CAT_WIN = -1;

    /**
     * 最大的限制步数
     */
    private static final int MAX_STEP = 1000;

    private static final int MOD = 8;

    private static final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private String[] mGrid;
    private int m;
    private int n;
    private int mCatJump;
    private int mMouseJump;
    //记录老鼠和猫位置对应的执行次数。
    private int[][][] marks;
    private int compare;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        mGrid = grid;
        m = grid.length;
        n = grid[0].length();
        mCatJump = catJump;
        mMouseJump = mouseJump;
        int max = m * MOD | n;
        marks = new int[max][max][MAX_STEP];
        //1，查找食物的位置，猫和老鼠的初始位置
        int cat = 0;
        int mouse = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char tem = grid[i].charAt(j);
                if (tem == 'C') {
                    cat = i * MOD + j;
                } else if (tem == 'M') {
                    mouse = i * MOD + j;
                } else if (tem == 'F') {
                    compare = i * MOD + j;
                }
            }
        }
        return dfs(mouse, cat, 0) == MOUSE_WIN;
    }

    private int dfs(int mouse, int cat, int step) {
        if (step == MAX_STEP - 1) {
            marks[mouse][cat][step] = CAT_WIN;
            return CAT_WIN;
        }
        if (mouse == cat) {
            marks[mouse][cat][step] = CAT_WIN;
            return CAT_WIN;
        }
        if (mouse == compare) {
            marks[mouse][cat][step] = MOUSE_WIN;
            return MOUSE_WIN;
        }
        if (cat == compare) {
            marks[mouse][cat][step] = CAT_WIN;
            return CAT_WIN;
        }
        if (marks[mouse][cat][step] != UN_KNOW) {
            return marks[mouse][cat][step];
        }
        if (step % 2 == 0) {
            return mouseJum(mouse, cat, step);
        } else {
            return catJump(mouse, cat, step);
        }
    }

    private int catJump(int mouse, int cat, int step) {
        int x = cat / MOD;
        int y = cat % MOD;
        //原地不动
        if (dfs(mouse, cat, step + 1) == CAT_WIN) {
            marks[mouse][cat][step] = CAT_WIN;
            return CAT_WIN;
        }
        for (int i = 0; i < offsets.length; i++) {
            for (int j = 1; j <= mCatJump; j++) {
                int nx = x + offsets[i][0] * j;
                int ny = y + offsets[i][1] * j;
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (mGrid[nx].charAt(ny) == '#') {
                        //移动到的位置为障碍物，无法继续跳跃
                        break;
                    }
                    if (dfs(mouse, nx * MOD + ny, step + 1) == CAT_WIN) {
                        marks[mouse][cat][step] = CAT_WIN;
                        return CAT_WIN;
                    }
                } else {
                    break;
                }
            }
        }
        marks[mouse][cat][step] = MOUSE_WIN;
        return MOUSE_WIN;
    }

    private int mouseJum(int mouse, int cat, int step) {
        int x = mouse / MOD;
        int y = mouse % MOD;
        //原地不动
        if (dfs(mouse, cat, step + 1) == MOUSE_WIN) {
            marks[mouse][cat][step] = MOUSE_WIN;
            return MOUSE_WIN;
        }
        for (int i = 0; i < offsets.length; i++) {
            for (int j = 1; j <= mMouseJump; j++) {
                int nx = x + offsets[i][0] * j;
                int ny = y + offsets[i][1] * j;
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (mGrid[nx].charAt(ny) == '#') {
                        //移动到的位置为障碍物，无法继续跳跃
                        break;
                    }
                    if (dfs(nx * MOD + ny, cat, step + 1) == MOUSE_WIN) {
                        marks[mouse][cat][step] = MOUSE_WIN;
                        return MOUSE_WIN;
                    }
                } else {
                    break;
                }
            }
        }
        marks[mouse][cat][step] = CAT_WIN;
        return CAT_WIN;
    }
}
