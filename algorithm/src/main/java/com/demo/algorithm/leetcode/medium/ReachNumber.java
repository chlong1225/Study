package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/11/4
 * @author chenglong
 * description : 到达终点数字
 *
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * 你可以做一些数量的移动numMoves:
 * 每次你可以选择向左或向右移动。
 * 第i次移动（从i == 1开始，到i == numMoves ），在选择的方向上走i步。
 * 给定整数target，返回到达目标所需的最小移动次数(即最小numMoves)。
 *
 * 示例 1:
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 *
 * 示例 2:
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 *
 * 提示:
 * -10^9<= target <= 10^9
 * target != 0
 */
public class ReachNumber {

    public int reachNumber(int target) {
        if (target < 0) {
            target = - target;
        }
        /**
         * 分析：可以转换为：1+2+...+n - 2(a1+a2+ak) = target
         * 其中a1,a2....ak属于1～n中不同的数。其中a1,a2....ak表示向左边移动。对应-a1...
         */
        //1，找到最小满足1+2+n>=target的n
        if (target == 1) {
            return 1;
        }
        int minStep = findMinIndex(target);
        //2，如果1～n刚好等于target，则全部向右边移动
        int total = minStep * (minStep + 1) / 2;
        if (total == target) {
            return minStep;
        }
        int last = total - target;
        if (last % 2 != 0) {
            //last为奇数，必须再叠加一个奇数
            if (minStep % 2 == 0) {
                minStep++;
            } else {
                minStep += 2;
            }
        }
        return minStep;
    }

    private int findMinIndex(int target) {
        int start = 1;
        int end = target;
        long base = 1L;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            long total = base * middle * (middle + 1) / 2;
            if (total < target) {
                start = middle + 1;
            } else if (total == target) {
                return middle;
            } else {
                end = middle;
            }
        }
        return start;
    }
}
