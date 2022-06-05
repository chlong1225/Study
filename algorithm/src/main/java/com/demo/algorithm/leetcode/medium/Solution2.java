package com.demo.algorithm.leetcode.medium;

import java.util.Random;

/**
 * Created by chl on 2022/6/5.
 * description : 在圆内随机生成点
 * <p>
 * 给定圆的半径和圆心的位置，实现函数randPoint，在圆中产生均匀随机点。
 * 实现Solution类:
 * Solution(double radius, double x_center, double y_center)用圆的半径radius和圆心的位置(x_center, y_center)初始化对象
 * randPoint()返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 * <p>
 * 示例 1：
 * 输入:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1.0, 0.0, 0.0], [], [], []]
 * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
 * 解释:
 * Solution solution = new Solution(1.0, 0.0, 0.0);
 * solution.randPoint ();//返回[-0.02493，-0.38077]
 * solution.randPoint ();//返回[0.82314,0.38945]
 * solution.randPoint ();//返回[0.36572,0.17248]
 * <p>
 * 提示：
 * 0<radius<=10^8
 * -10^7<= x_center, y_center <= 10^7
 * randPoint 最多被调用3 * 10^4次
 */

public class Solution2 {

    private double mRadius;
    private double[] center = new double[2];
    private Random random = new Random();

    public Solution2(double radius, double x_center, double y_center) {
        mRadius = radius;
        center[0] = x_center;
        center[1] = y_center;
    }

    public double[] randPoint() {
        double[] result = new double[2];
        double angle = random.nextDouble() * 2 * Math.PI;
        double tem = Math.sqrt(random.nextDouble());
        double size = mRadius * tem;
        result[0] = center[0] + Math.cos(angle) * size;
        result[1] = center[1] + Math.sin(angle) * size;
        return result;
    }
}
