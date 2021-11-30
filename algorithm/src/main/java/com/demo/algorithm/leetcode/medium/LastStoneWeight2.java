package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/29.
 * description : 最后一块石头的重量II
 *
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 * 示例 1：
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 示例 2：
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 *
 * 示例 3：
 * 输入：stones = [1,2]
 * 输出：1
 *  
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
public class LastStoneWeight2 {

    public int lastStoneWeightII(int[] stones) {
        int length = stones.length;
        if (length == 1) {
            return stones[0];
        }
        if (length == 2) {
            return Math.abs(stones[0] - stones[1]);
        }
        /**
         * 分析：两个石头粉粹相当于：+a1-a2，与石头a3相撞变成：+a3-(+a1-a2) = +a3-a1+a2 或 -a3+(+a1-a2) = -a3+a1-a2
         * 最终变成了在n个数之前加上+或-,是最终结果>=0并且最小，可以转换成类似表达式目标和的问题
         * min = 正数 - 负数
         * 正数 + 负数 = sum
         * 负数 = (sum - min)/2
         * min最小等于0时,相当于在num中找到背包sum的一半
         */
        int sum = stones[0];
        for (int i = 1; i < length; i++) {
            sum += stones[i];
        }
        int result = sum % 2;
        sum = sum >> 1;
        //1,定义状态变量。打表记录0~sum是否都可以组合
        boolean[] marks = new boolean[sum + 1];
        marks[0] = true;
        out:
        for (int i = 0; i < length; i++) {
            int stone = stones[i];
            if (stone > sum) {
                continue;
            }
            for (int j = sum; j >= stone; j--) {
                marks[j] = marks[j] || marks[j - stone];
                if (j == sum && marks[sum]) {
                    break out;
                }
            }
        }
        for (int i = sum; i >= 0; i--) {
            if (!marks[i]) {
                result += 2;
            } else {
                break;
            }
        }
        return result;
    }
}
