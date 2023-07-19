package com.demo.algorithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2023/7/19
 *
 * @author chenglong
 * description : 模拟行走机器人
 *
 * 机器人在一个无限大小的XY网格平面上行走，从点(0,0)处开始出发，面向北方。该机器人可以接收以下三种类型的命令commands：
 * -2 ：向左转90度
 * -1 ：向右转90度
 * 1 <= x <= 9 ：向前移动x个单位长度
 * 在网格上有一些格子被视为障碍物obstacles。第i个障碍物位于网格点obstacles[i] = (xi, yi)。
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
 *
 * 注意：
 * 北表示 +Y 方向。
 * 东表示 +X 方向。
 * 南表示 -Y 方向。
 * 西表示 -X 方向。
 *
 * 示例1：
 * 输入：commands = [4,-1,3], obstacles = []
 * 输出：25
 * 解释：
 * 机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 3 个单位，到达 (3, 4)
 * 距离原点最远的是 (3, 4) ，距离为 32 + 42 = 25
 *
 * 示例2：
 * 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出：65
 * 解释：机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
 * 4. 左转
 * 5. 向北走 4 个单位，到达 (1, 8)
 * 距离原点最远的是 (1, 8) ，距离为 12 + 82 = 65
 *
 * 提示：
 * 1 <= commands.length <= 10^4
 * commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
 * 0 <= obstacles.length <= 10^4
 * -3 * 10^4 <= xi, yi <= 3 * 10^4
 * 答案保证小于 2^31
 */
public class RobotSim {

    private static final int mod = 100000;
    private static final int[][] offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int robotSim(int[] commands, int[][] obstacles) {
        int[] points = {0, 0};
        //1，使用hashSet统计障碍物位置，便于查找
        Set<Integer> marks = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            marks.add(obstacles[i][0] * mod + obstacles[i][1]);
        }
        //方向分别对应：东：0，北：1，西：2，南：3
        int dir = 1;
        int max = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -2) {
                dir = (dir + 1) % 4;
            } else if (commands[i] == -1) {
                dir = (dir + 3) % 4;
            } else {
                int size = commands[i];
                for (int j = 0; j < size; j++) {
                    int nx = points[0] + offsets[dir][0];
                    int ny = points[1] + offsets[dir][1];
                    if (marks.contains(nx * mod + ny)) {
                        break;
                    }
                    points[0] = nx;
                    points[1] = ny;
                    int tem = nx * nx + ny * ny;
                    if (tem > max) {
                        max = tem;
                    }
                }
            }
        }
        return max;
    }
}
