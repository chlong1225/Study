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
     * 用于标记当前状态的跳跃情况，分别对应：
     * 老鼠跳跃
     * 猫跳跃
     * 两者都跳跃
     */
    private static final int MOUSE_JUMP = 1;
    private static final int CAT_JUMP = 2;
    private static final int DOUBLE_JUMP = 3;

    /**
     * 分别记录结果：未知；老鼠获胜；猫获胜
     */
    private static final int UN_KNOW = 0;
    private static final int MOUSE_WIN = 101;
    private static final int CAT_WIN = 102;

    /**
     * 最大的限制步数
     */
    private static final int MAX_STEP = 1000;
    private String[] mGrid;
    private int m;
    private int n;
    private int mCatJump;
    private int mMouseJump;
    //记录老鼠和猫位置对应的执行次数。
    private int[][][][] marks;
    private final int[] food = new int[2];
    private final int[] bounds = new int[4];

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        mGrid = grid;
        m = grid.length;
        n = grid[0].length();
        mCatJump = catJump;
        mMouseJump = mouseJump;
        marks = new int[m][n][m][n];
        //1，查找食物的位置，猫和老鼠的初始位置
        int[] cat = new int[2];
        int[] mouse = new int[2];
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
        checkFoodBounds(food, grid);
        //3，模拟老鼠和猫依次跳跃
        int state = mouseJump(1, mouse, cat);
        if (state == MOUSE_WIN) {
            return true;
        }
        return false;
    }

    //深度搜索老鼠跳跃
    private int mouseJump(int step, int[] mouse, int[] cat) {
        //如果老鼠跳跃次数超过MAX_STEP，判断猫获胜
        if (step > MAX_STEP) {
            return CAT_WIN;
        }
        int result = UN_KNOW;
        if (marks[mouse[0]][mouse[1]][cat[0]][cat[1]] == MOUSE_JUMP || marks[mouse[0]][mouse[1]][cat[0]][cat[1]] == DOUBLE_JUMP) {
            return result;
        }
        marks[mouse[0]][mouse[1]][cat[0]][cat[1]] += MOUSE_JUMP;
        //1，先判断老鼠是否可以直接跳跃到食物
        if (mouse[0] == food[0]) {
            //老鼠和食物在同一水平方向上
            if (mouse[1] >= bounds[0] && mouse[1] <= bounds[2]) {
                if (Math.abs(mouse[1] - food[1]) <= mMouseJump) {
                    return MOUSE_WIN;
                }
            }
        }
        if (mouse[1] == food[1]) {
            //老鼠和食物在同一垂直方向上
            if (mouse[0] >= bounds[1] && mouse[0] <= bounds[3]) {
                if (Math.abs(mouse[0] - food[0]) <= mMouseJump) {
                    return MOUSE_WIN;
                }
            }
        }
        //2，遍历老鼠跳跃的所有场景
        //2.1，向左边跳跃
        int end = Math.max(0, mouse[1] - mMouseJump);
        for (int i = mouse[1]; i >= end; i--) {
            if (mGrid[mouse[0]].charAt(i) == '#') {
                break;
            } else {
                int state = catJump(step, cat, new int[]{mouse[0], i});
                if (state == CAT_WIN) {
                    result = CAT_WIN;
                }
            }
        }
        //2.2，向右边跳跃
        end = Math.min(n - 1, mouse[1] + mMouseJump);
        for (int i = mouse[1] + 1; i <= end; i++) {
            if (mGrid[mouse[0]].charAt(i) == '#') {
                break;
            } else {
                int state = catJump(step, cat, new int[]{mouse[0], i});
                if (state == CAT_WIN) {
                    result = CAT_WIN;
                }
            }
        }
        //2.3，向上方跳跃
        end = Math.max(0, mouse[0] - mMouseJump);
        for (int i = mouse[0] - 1; i >= end; i--) {
            if (mGrid[i].charAt(mouse[1]) == '#') {
                break;
            } else {
                int state = catJump(step, cat, new int[]{i, mouse[1]});
                if (state == CAT_WIN) {
                    result = CAT_WIN;
                }
            }
        }
        //2.4，向下方跳跃
        end = Math.min(m - 1, mouse[0] + mMouseJump);
        for (int i = mouse[0] + 1; i <= end; i++) {
            if (mGrid[i].charAt(mouse[1]) == '#') {
                break;
            } else {
                int state = catJump(step, cat, new int[]{i, mouse[1]});
                if (state == CAT_WIN) {
                    result = CAT_WIN;
                }
            }
        }
        return result;
    }

    private int catJump(int step, int[] cat, int[] mouse) {
        int result = UN_KNOW;
        if (marks[mouse[0]][mouse[1]][cat[0]][cat[1]] == CAT_JUMP || marks[mouse[0]][mouse[1]][cat[0]][cat[1]] == DOUBLE_JUMP) {
            return result;
        }
        marks[mouse[0]][mouse[1]][cat[0]][cat[1]] += CAT_JUMP;
        //1，先判断猫是否可以直接跳跃到食物
        if (cat[0] == food[0]) {
            //猫和食物在同一水平方向上
            if (cat[1] >= bounds[0] && cat[1] <= bounds[2]) {
                if (Math.abs(cat[1] - food[1]) <= mCatJump) {
                    return CAT_WIN;
                }
            }
        }
        if (cat[1] == food[1]) {
            //猫和食物在同一垂直方向上
            if (cat[0] >= bounds[1] && cat[0] <= bounds[3]) {
                if (Math.abs(cat[0] - food[0]) <= mCatJump) {
                    return CAT_WIN;
                }
            }
        }
        //2，遍历猫跳跃的所有场景
        //2.1，向左边跳跃
        int end = Math.max(0, cat[1] - mCatJump);
        for (int i = cat[1]; i >= end; i--) {
            if (mGrid[cat[0]].charAt(i) == '#') {
                break;
            } else {
                int state = mouseJump(step + 1, mouse, new int[]{cat[0], i});
                if (state == MOUSE_WIN) {
                    result = MOUSE_WIN;
                }
            }
        }
        //2.2，向右边跳跃
        end = Math.min(n - 1, cat[1] + mCatJump);
        for (int i = cat[1] + 1; i <= end; i++) {
            if (mGrid[cat[0]].charAt(i) == '#') {
                break;
            } else {
                int state = mouseJump(step + 1, mouse, new int[]{cat[0], i});
                if (state == MOUSE_WIN) {
                    result = MOUSE_WIN;
                }
            }
        }
        //2.3，向上方跳跃
        end = Math.max(0, cat[0] - mCatJump);
        for (int i = cat[0] - 1; i >= end; i--) {
            if (mGrid[i].charAt(cat[1]) == '#') {
                break;
            } else {
                int state = mouseJump(step + 1, mouse, new int[]{i, cat[1]});
                if (state == MOUSE_WIN) {
                    result = MOUSE_WIN;
                }
            }
        }
        //2.4，向下方跳跃
        end = Math.min(m - 1, cat[0] + mCatJump);
        for (int i = cat[0] + 1; i <= end; i++) {
            if (mGrid[i].charAt(cat[1]) == '#') {
                break;
            } else {
                int state = mouseJump(step + 1, mouse, new int[]{i, cat[1]});
                if (state == MOUSE_WIN) {
                    result = MOUSE_WIN;
                }
            }
        }
        return result;
    }

    private void checkFoodBounds(int[] food, String[] grid) {
        bounds[0] = food[1];
        for (int i = food[1] - 1; i >= 0; i--) {
            if (grid[food[0]].charAt(i) == '#') {
                break;
            } else {
                bounds[0] = i;
            }
        }
        bounds[1] = food[0];
        for (int i = food[0] - 1; i >= 0; i--) {
            if (grid[i].charAt(food[1]) == '#') {
                break;
            } else {
                bounds[1] = i;
            }
        }
        bounds[2] = food[1];
        for (int i = food[1] + 1; i < n; i++) {
            if (grid[food[0]].charAt(i) == '#') {
                break;
            } else {
                bounds[2] = i;
            }
        }
        bounds[3] = food[0];
        for (int i = food[0] + 1; i < m; i++) {
            if (grid[i].charAt(food[1]) == '#') {
                break;
            } else {
                bounds[3] = i;
            }
        }
    }
}
