package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/1/11.
 * description : 逃离大迷宫
 *
 * 在一个10^6 x 10^6的网格中，每个网格上方格的坐标为(x, y) 。
 * 现在从源方格source = [sx, sy]开始出发，意图赶往目标方格target = [tx, ty] 。
 * 数组 blocked 是封锁的方格列表，其中每个blocked[i] =[xi, yi]表示坐标为(xi, yi)的方格是禁止通行的。
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格不在给出的封锁列表blocked上。同时，不允许走出网格。
 * 只有在可以通过一系列的移动从源方格source 到达目标方格target 时才返回true。否则，返回 false。
 *
 * 示例 1：
 * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * 输出：false
 * 解释：
 * 从源方格无法到达目标方格，因为我们无法在网格中移动。
 * 无法向北或者向东移动是因为方格禁止通行。
 * 无法向南或者向西移动是因为不能走出网格。
 *
 * 示例 2：
 * 输入：blocked = [], source = [0,0], target = [999999,999999]
 * 输出：true
 * 解释：
 * 因为没有方格被封锁，所以一定可以到达目标方格。
 *
 * 提示：
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 106
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 106
 * source != target
 * 题目数据保证 source 和 target 不在封锁列表内
 */
public class EscapeMaze {

    private static final long MOD = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        /**
         * 分析：常规使用记忆化深度优先搜索dfs，最坏常见搜索次数的数量级为10^6*10^6,会造成超时。
         * 而障碍物对多200，数量级相对较小，可以考虑从障碍物出发。
         * 方案1：给障碍物x，y坐标排序后，查询障碍物做成的圈(考虑边界)，
         * 然后判断是否包含了两个节点，如果包含则不可能到底。
         * 方案2：从节点出发，如果被包围，最大当前节点最大可以访问的节点数量为：n*(n-1)-1。
         * 如果超出当前节点数，则出发节点不可能被包围。
         */
        int length = blocked.length;
        //1，如果障碍物少于2个，不可能包围节点，即肯定可以到底目标节点
        if (length < 2) {
            return true;
        }
        //2，最大节点访问数
        int max = length * (length - 1);
        //3，将blocked数据转换为hash，便于查找
        Set<Long> blocks = new HashSet<>();
        for (int i = 0; i < length; i++) {
            blocks.add(blocked[i][0] * MOD + blocked[i][1]);
        }
        //4，将source作为起点进行搜索
        //记录节点source已经被搜索过的位置
        Set<Long> marks1 = new HashSet<>();
        long find = target[0] * MOD + target[1];
        int count = 0;
        List<int[]> points = new ArrayList<>();
        List<int[]> next = new ArrayList<>();
        points.add(source);
        //分别对应左上右下坐标的偏移
        int[][] addPoint = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        out:while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                int[] poin = points.get(i);
                for (int j = 0; j < 4; j++) {
                    int dx = poin[0] + addPoint[j][0];
                    int dy = poin[1] + addPoint[j][1];
                    if (dx >= 0 && dx < MOD && dy >= 0 && dy < MOD) {
                        long tem = dx * MOD + dy;
                        if (tem == find) {
                            //搜索过程中找到了targe节点,直接返回true
                            return true;
                        }
                        if (blocks.contains(tem) || marks1.contains(tem)) {
                            continue;
                        }
                        //如果tem不是障碍物并且没有被访问
                        marks1.add(tem);
                        next.add(new int[]{dx, dy});
                        count++;
                        if (count >= max) {
                            //如果访问节点的数量大于等于max,代表source节点不可能被包围，结束搜索访问。改为搜索校验targe节点
                            break out;
                        }
                    }
                }
            }
            points.clear();
            points.addAll(next);
            next.clear();
        }
        //5，搜索访问结束后，如果次数小于max，则节点source被包围，返回false
        if (count < max) {
            return false;
        }
        //6，搜索访问节点targe
        marks1.add(source[0] * MOD + source[1]);
        Set<Long> marks2 = new HashSet<>();
        count = 0;
        next.clear();
        points.clear();
        points.add(target);
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                int[] poin = points.get(i);
                for (int j = 0; j < 4; j++) {
                    int dx = poin[0] + addPoint[j][0];
                    int dy = poin[1] + addPoint[j][1];
                    if (dx >= 0 && dx < MOD && dy >= 0 && dy < MOD) {
                        long tem = dx * MOD + dy;
                        if (marks1.contains(tem)) {
                            //访问的节点在source访问时被标记，肯定存在路径可以到达
                            return true;
                        }
                        if (blocks.contains(tem) || marks2.contains(tem)) {
                            continue;
                        }
                        //如果tem不是障碍物并且没有被访问
                        marks2.add(tem);
                        next.add(new int[]{dx, dy});
                        count++;
                        if (count >= max) {
                            return true;
                        }
                    }
                }
            }
            points.clear();
            points.addAll(next);
            next.clear();
        }
        return false;
    }
}
