package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/11/2
 * @author chenglong
 * description : 网络信号最好的坐标
 *
 * 给你一个数组towers和一个整数radius。
 * 数组towers中包含一些网络信号塔，其中towers[i] = [xi, yi, qi]表示第i个网络信号塔的坐标是(xi, yi)且信号强度参数为qi。所有坐标都是在X-Y坐标系内的整数坐标。
 * 两个坐标之间的距离用欧几里得距离计算。
 * 整数radius表示一个塔能到达的最远距离。如果一个坐标跟塔的距离在radius以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以radius以外的距离该塔是不能到达的。
 * 如果第i个塔能到达(x, y)，那么该塔在此处的信号为⌊qi / (1 + d)⌋，其中d是塔跟此坐标的距离。一个坐标的信号强度是所有能到达该坐标的塔的信号强度之和。
 * 请你返回数组[cx, cy]，表示信号强度最大的整数坐标点(cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的非负坐标。
 *
 * 注意：
 * 坐标(x1, y1)字典序比另一个坐标(x2, y2)小，需满足以下条件之一：
 * 要么x1 < x2，
 * 要么x1 == x2 且y1 < y2。
 * ⌊val⌋表示小于等于val的最大整数（向下取整函数）。
 *
 * 示例 1：
 * 输入：towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
 * 输出：[2,1]
 * 解释：
 * 坐标 (2, 1) 信号强度之和为 13
 * - 塔 (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7
 * - 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 + sqrt(2)⌋ = ⌊2.07⌋ = 2
 * - 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
 * 没有别的坐标有更大的信号强度。
 *
 * 示例 2：
 * 输入：towers = [[23,11,21]], radius = 9
 * 输出：[23,11]
 * 解释：由于仅存在一座信号塔，所以塔的位置信号强度最大。
 *
 * 示例 3：
 * 输入：towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
 * 输出：[1,2]
 * 解释：坐标 (1, 2) 的信号强度最大。
 *
 * 提示：
 * 1 <= towers.length <= 50
 * towers[i].length == 3
 * 0 <= xi, yi, qi <= 50
 * 1 <= radius <= 50
 */
public class BestCoordinate {

    public int[] bestCoordinate(int[][] towers, int radius) {
        int length = towers.length;
        if (length == 1) {
            if (towers[0][2] == 0) {
                return new int[]{0, 0};
            } else {
                return new int[]{towers[0][0], towers[0][1]};
            }
        }
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < length; i++) {
            if (towers[i][0] > maxX) {
                maxX = towers[i][0];
            }
            if (towers[i][1] > maxY) {
                maxY = towers[i][1];
            }
        }
        maxX += radius;
        maxY += radius;
        int max = -1;
        radius *= radius;
        int[] result = new int[2];
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                //当前位置：(i,j)
                int sum = 0;
                for (int k = 0; k < length; k++) {
                    int x = towers[k][0] - i;
                    int y = towers[k][1] - j;
                    if (x == 0 && y == 0) {
                        sum += towers[k][2];
                    } else {
                        int space = x * x + y * y;
                        if (space <= radius) {
                            sum += (towers[k][2] / (1 + Math.sqrt(space)));
                        }
                    }
                }
                if (sum > max) {
                    max = sum;
                    result[0] = i;
                    result[1] = j;
                } else if (sum == max) {
                    //比较字典顺序
                    if (result[0] > i) {
                        result[0] = i;
                        result[1] = j;
                    } else if (result[0] == i) {
                        if (result[1] > j) {
                            result[1] = j;
                        }
                    }
                }
            }
        }
        return result;
    }
}
