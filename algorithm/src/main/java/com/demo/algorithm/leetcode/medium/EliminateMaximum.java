package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/9/4
 * @author chenglong
 * description : 消灭怪物的最大数量
 *
 * 你正在玩一款电子游戏，在游戏中你需要保护城市免受怪物侵袭。给你一个下标从0开始且长度为n的整数数组dist，其中dist[i]是第i个怪物与城市的初始距离（单位：米）。
 * 怪物以恒定的速度走向城市。给你一个长度为n的整数数组speed表示每个怪物的速度，其中speed[i]是第i个怪物的速度（单位：米/分）。
 * 怪物从第0分钟时开始移动。你有一把武器，并可以选择在每一分钟的开始时使用，包括第0分钟。但是你无法在一分钟的中间使用武器。这种武器威力惊人，一次可以消灭任一还活着的怪物。
 * 一旦任一怪物到达城市，你就输掉了这场游戏。如果某个怪物 恰 在某一分钟开始时到达城市，这会被视为输掉游戏，在你可以使用武器之前，游戏就会结束。
 * 返回在你输掉游戏前可以消灭的怪物的最大数量。如果你可以在所有怪物到达城市前将它们全部消灭，返回 n。
 *
 * 示例 1：
 * 输入：dist = [1,3,4], speed = [1,1,1]
 * 输出：3
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [1,3,4]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,2,3]，你没有消灭任何怪物。
 * 第 2 分钟开始时，怪物的距离是 [X,1,2]，你消灭了第二个怪物。
 * 第 3 分钟开始时，怪物的距离是 [X,X,1]，你消灭了第三个怪物。
 * 所有 3 个怪物都可以被消灭。
 *
 * 示例 2：
 * 输入：dist = [1,1,2,3], speed = [1,1,1,1]
 * 输出：1
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [1,1,2,3]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,0,1,2]，你输掉了游戏。
 * 你只能消灭 1 个怪物。
 *
 * 示例 3：
 * 输入：dist = [3,2,4], speed = [5,3,2]
 * 输出：1
 * 解释：
 * 第 0 分钟开始时，怪物的距离是 [3,2,4]，你消灭了第一个怪物。
 * 第 1 分钟开始时，怪物的距离是 [X,0,2]，你输掉了游戏。
 * 你只能消灭 1 个怪物。
 *
 * 提示：
 * n == dist.length == speed.length
 * 1 <= n <= 10^5
 * 1 <= dist[i], speed[i] <= 10^5
 */
public class EliminateMaximum {

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        //统计必须在i分钟消灭的怪物数量
        int[] marks = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int time = dist[i] / speed[i];
            if (dist[i] % speed[i] == 0) {
                time--;
            }
            //防止越界
            if (time > n) {
                time = n;
            }
            marks[time]++;
        }
        int sum = 0;
        for (int i = 0; i < n + 1; i++) {
            sum += marks[i];
            if (sum > i + 1) {
                return i + 1;
            }
        }
        return sum;
    }
}
