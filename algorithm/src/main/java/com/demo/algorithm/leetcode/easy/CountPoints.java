package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/2
 * @author chenglong
 * description : 环和杆
 *
 * 总计有n个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在10根编号为0到9的杆上。
 * 给你一个长度为2n的字符串rings，表示这n个环在杆上的分布。rings中每两个字符形成一个颜色位置对，用于描述每个环：
 * 第i对中的第一个字符表示第i个环的 颜色（'R'、'G'、'B'）。
 * 第i对中的第二个字符表示第i个环的 位置，也就是位于哪根杆上（'0' 到 '9'）。
 * 例如，"R3G2B1" 表示：共有n==3个环，红色的环在编号为3的杆上，绿色的环在编号为2的杆上，蓝色的环在编号为1的杆上。
 * 找出所有集齐全部三种颜色环的杆，并返回这种杆的数量。
 *
 * 示例 1：
 * 输入：rings = "B0B6G0R6R0R6G9"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 3 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 6 的杆上有 3 个环，但只有红、蓝两种颜色。
 * - 编号 9 的杆上只有 1 个绿色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 *
 * 示例 2：
 * 输入：rings = "B0R0G0R9R0B0G0"
 * 输出：1
 * 解释：
 * - 编号 0 的杆上有 6 个环，集齐全部颜色：红、绿、蓝。
 * - 编号 9 的杆上只有 1 个红色环。
 * 因此，集齐全部三种颜色环的杆的数目为 1 。
 *
 * 示例 3：
 * 输入：rings = "G4"
 * 输出：0
 * 解释：
 * 只给了一个环，因此，不存在集齐全部三种颜色环的杆。
 *
 * 提示：
 * rings.length == 2 * n
 * 1 <= n <= 100
 * 如 i 是 偶数 ，则 rings[i] 的值可以取 'R'、'G' 或 'B'（下标从 0 开始计数）
 * 如 i 是 奇数 ，则 rings[i] 的值可以取 '0' 到 '9' 中的一个数字（下标从 0 开始计数）
 */
public class CountPoints {

    public int countPoints(String rings) {
        boolean[][] marks = new boolean[10][3];
        for (int i = 0; i < rings.length(); i += 2) {
            int index = rings.charAt(i + 1) - '0';
            if (rings.charAt(i) == 'R') {
                marks[index][0] = true;
            } else if (rings.charAt(i) == 'G') {
                marks[index][1] = true;
            } else {
                marks[index][2] = true;
            }
        }
        int count = 0;
        for (int i = 0; i < marks.length; i++) {
            if (marks[i][0] && marks[i][1] && marks[i][2]) {
                count++;
            }
        }
        return count;
    }
}
