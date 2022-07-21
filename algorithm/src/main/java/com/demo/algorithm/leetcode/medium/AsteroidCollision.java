package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/7/13
 * @author chenglong
 * description : 行星碰撞
 *
 * 给定一个整数数组asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10和-5碰撞后只剩下10 。 5和10永远不会发生碰撞。
 *
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8和-8碰撞后，两者都发生爆炸。
 *
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释2和-5发生碰撞后剩下-5 。10和-5发生碰撞后剩下10 。
 *
 * 提示：
 * 2 <= asteroids.length<= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> dates = new ArrayList<>();
        int length = asteroids.length;
        for (int i = 0; i < length; i++) {
            int cur = asteroids[i];
            boolean isAdd = true;
            while (dates.size() > 0) {
                int last = dates.get(dates.size() - 1);
                if (last > 0 && cur < 0) {
                    //方向相反，必须前一个向右，后一个向左才能碰撞
                    if (last > Math.abs(cur)) {
                        isAdd = false;
                        break;
                    } else if (last == Math.abs(cur)) {
                        isAdd = false;
                        dates.remove(dates.size() - 1);
                        break;
                    } else {
                        dates.remove(dates.size() - 1);
                    }
                } else {
                    //方向相同，直接添加
                    break;
                }
            }
            if (isAdd) {
                dates.add(cur);
            }
        }
        int[] result = new int[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            result[i] = dates.get(i);
        }
        return result;
    }
}
