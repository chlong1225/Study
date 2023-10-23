package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2023/10/23
 * @author chenglong
 * description : 做菜顺序
 *
 * 一个厨师收集了他n道菜的满意程度satisfaction，这个厨师做出每道菜的时间都是1单位时间。
 * 一道菜的「like-time系数」定义为烹饪这道菜结束的时间（包含之前每道菜所花费的时间）乘以这道菜的满意程度，也就是time[i]*satisfaction[i]。
 * 返回厨师在准备了一定数量的菜肴后可以获得的最大like-time系数总和。
 * 你可以按任意顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
 *
 * 示例 1：
 * 输入：satisfaction = [-1,-8,0,5,-9]
 * 输出：14
 * 解释：去掉第二道和最后一道菜，最大的 like-time 系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
 *
 * 示例 2：
 * 输入：satisfaction = [4,3,2]
 * 输出：20
 * 解释：可以按照任意顺序做菜 (2*1 + 3*2 + 4*3 = 20)
 *
 * 示例 3：
 * 输入：satisfaction = [-1,-4,-5]
 * 输出：0
 * 解释：大家都不喜欢这些菜，所以不做任何菜就可以获得最大的 like-time 系数。
 *
 * 提示：
 * n == satisfaction.length
 * 1 <= n <= 500
 * -1000 <= satisfaction[i] <= 1000
 */
public class MaxSatisfaction {

    public int maxSatisfaction(int[] satisfaction) {
        //1，对满意度进行排序
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        //2，处理特殊场景
        if (satisfaction[n - 1] <= 0) {
            return 0;
        }
        int[] adds = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            adds[i] = adds[i + 1] + satisfaction[i + 1];
        }
        //3，依次统计叠加
        int total = 0;
        int time = 1;
        for (int i = 0; i < n; i++) {
            if (satisfaction[i] >= 0) {
                total += (time * satisfaction[i]);
                time++;
            } else {
                //需要判断当前菜是否有必要做
                //当前做菜会减少的live-time值，
                int reduce = satisfaction[i] * time;
                //做菜后time会增加的live-time值:adds[i]
                if (adds[i] + reduce >= 0) {
                    total += (time * satisfaction[i]);
                    time++;
                }
            }
        }
        return total;
    }
}
