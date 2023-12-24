package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/12/24
 * description : 收集足够苹果的最小花园周长
 *
 * 给你一个用无限二维网格表示的花园，每一个整数坐标处都有一棵苹果树。整数坐标(i,j)处的苹果树有|i|+|j|个苹果。
 * 你将会买下正中心坐标是(0,0)的一块正方形土地，且每条边都与两条坐标轴之一平行。
 * 给你一个整数neededApples，请你返回土地的最小周长，使得至少有neededApples个苹果在土地里面或者边缘上。
 * |x| 的值定义为：
 * 如果 x >= 0 ，那么值为 x
 * 如果 x < 0 ，那么值为 -x
 *
 * 示例 1：
 * 输入：neededApples = 1
 * 输出：8
 * 解释：边长长度为 1 的正方形不包含任何苹果。
 * 但是边长为 2 的正方形包含 12 个苹果（如上图所示）。
 * 周长为 2 * 4 = 8 。
 *
 * 示例 2：
 * 输入：neededApples = 13
 * 输出：16
 *
 * 示例 3：
 * 输入：neededApples = 1000000000
 * 输出：5040
 *
 * 提示：
 * 1 <= neededApples <= 10^15
 */
public class MinimumPerimeter {

    public long minimumPerimeter(long neededApples) {
        if (neededApples <= 12) {
            return 8;
        }
        //单边的长度
        long min = 1;
        long max = 100000;
        while (min < max) {
            long middle = (max - min) / 2 + min;
            //对于左上角坐标为（i，i）。总数为：2*i*(i+1)*(2*i+1)。周长为：8*i
            long sum = 2 * middle * (middle + 1) * (2 * middle + 1);
            if (sum == neededApples) {
                min = middle;
                break;
            } else if (sum > neededApples) {
                max = middle;
            } else {
                min = middle + 1;
            }
        }
        return 8 * min;
    }
}
