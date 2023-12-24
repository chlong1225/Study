package com.demo.algorithm.leetcode.lcp;

/**
 * create on 2023/12/19
 * @author chenglong
 * description : LCP 03. 机器人大冒险
 *
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0,0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在xy平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * 给定终点坐标(x,y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 *
 * 示例 2：
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 *
 * 示例 3：
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *
 * 限制：
 * 2 <= command的长度 <= 1000
 * command由U，R构成，且至少有一个U，至少有一个R
 * 0 <= x <= 1e9, 0 <= y <= 1e9
 * 0 <= obstacles的长度 <= 1000
 * obstacles[i]不为原点或者终点
 */
public class Robot {

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        //1，统一一次执行指令移动的范围
        int[] ones = new int[]{0, 0};
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'U') {
                ones[1]++;
            } else {
                ones[0]++;
            }
        }
        //此时：位置的范围为(0,0) -> (ones[0],ones[1])构成的矩形
        //2，统计完全重复执行指令的次数(不包含最后一次)
        int countX = x / ones[0];
        int countY = y / ones[1];
        int count = Math.min(countX, countY);
        if (Math.abs(countX - countY) > 1) {
            return false;
        }
        //3，统计一次执行的路径
        int[][] visits = new int[ones[0] + 1][ones[1] + 1];
        int positionX = 0;
        int positionY = 0;
        visits[positionX][positionY] = 1;
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'U') {
                positionY++;
            } else {
                positionX++;
            }
            visits[positionX][positionY] = i + 2;
        }
        //4，判断终点是否在路径上，在不考虑障碍物时是否可以到达
        int lastX = x - ones[0] * count;
        int lastY = y - ones[1] * count;
        if (lastX > ones[0] || lastY > ones[1] || visits[lastX][lastY] == 0) {
            return false;
        }
        //5，判断障碍物是否会阻挡
        for (int i = 0; i < obstacles.length; i++) {
            int blockX = obstacles[i][0];
            int blockY = obstacles[i][1];
            if (blockX > x || blockY > y) {
                //此时只能是到终点之后才可能碰到，无效
                continue;
            }
            //判断当前障碍物是否在路径上
            int blockCountX = blockX / ones[0];
            int blockCountY = blockY / ones[1];
            int blockCount = Math.min(blockCountX, blockCountY);
            if (Math.abs(blockCountX - blockCountY) > 1 || blockCount > countX) {
                continue;
            }
            int lastBlockX = blockX - ones[0] * blockCount;
            int lastBlockY = blockY - ones[1] * blockCount;
            if (lastBlockX > ones[0] || lastBlockY > ones[1]) {
                continue;
            }
            if (visits[lastBlockX][lastBlockY] > 0) {
                if (blockCount < count) {
                    return false;
                } else {
                    if (visits[lastBlockX][lastBlockY] < visits[lastX][lastY]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
