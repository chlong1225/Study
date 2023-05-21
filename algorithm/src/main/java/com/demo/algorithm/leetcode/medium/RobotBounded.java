package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/11
 * @author chenglong
 * description : 困于环中的机器人
 *
 * 在无限的平面上，机器人最初位于(0,0)处，面朝北方。注意:
 * 北方向是y轴的正方向。
 * 南方向是y轴的负方向。
 * 东方向是x轴的正方向。
 * 西方向是x轴的负方向。
 * 机器人可以接受下列三条指令之一：
 * "G"：直走1个单位
 * "L"：左转90度
 * "R"：右转90度
 * 机器人按顺序执行指令instructions，并一直重复它们。
 * 只有在平面中存在环使得机器人永远无法离开时，返回true。否则，返回false。
 *
 * 示例 1：
 * 输入：instructions = "GGLLGG"
 * 输出：true
 * 解释：机器人最初在(0,0)处，面向北方。
 * “G”:移动一步。位置:(0,1)方向:北。
 * “G”:移动一步。位置:(0,2).方向:北。
 * “L”:逆时针旋转90度。位置:(0,2).方向:西。
 * “L”:逆时针旋转90度。位置:(0,2)方向:南。
 * “G”:移动一步。位置:(0,1)方向:南。
 * “G”:移动一步。位置:(0,0)方向:南。
 * 重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。
 * 在此基础上，我们返回true。
 *
 * 示例 2：
 * 输入：instructions = "GG"
 * 输出：false
 * 解释：机器人最初在(0,0)处，面向北方。
 * “G”:移动一步。位置:(0,1)方向:北。
 * “G”:移动一步。位置:(0,2).方向:北。
 * 重复这些指示，继续朝北前进，不会进入循环。
 * 在此基础上，返回false。
 *
 * 示例 3：
 * 输入：instructions = "GL"
 * 输出：true
 * 解释：机器人最初在(0,0)处，面向北方。
 * “G”:移动一步。位置:(0,1)方向:北。
 * “L”:逆时针旋转90度。位置:(0,1).方向:西。
 * “G”:移动一步。位置:(- 1,1)方向:西。
 * “L”:逆时针旋转90度。位置:(- 1,1)方向:南。
 * “G”:移动一步。位置:(- 1,0)方向:南。
 * “L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
 * “G”:移动一步。位置:(0,0)方向:东方。
 * “L”:逆时针旋转90度。位置:(0,0)方向:北。
 * 重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
 * 在此基础上，我们返回true。
 *
 * 提示：
 * 1 <= instructions.length <= 100
 * instructions[i]仅包含'G', 'L', 'R'
 */
public class RobotBounded {

    //分别对应向北，向西，向南，向东偏移
    private int[][] offsets = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public boolean isRobotBounded(String instructions) {
        //偏移量
        int direction = 0;
        //起点
        int[] position = {0, 0};
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'G') {
                position[0] += offsets[direction][0];
                position[1] += offsets[direction][1];
            } else if (instructions.charAt(i) == 'L') {
                direction = (direction + 1) % 4;
            } else {
                direction = (direction + 3) % 4;
            }
        }
        return direction != 0 || (position[0] == 0 && position[1] == 0);
    }
}
