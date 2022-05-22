package com.demo.algorithm.leetcode.contest.week294;

import java.util.Arrays;

/**
 * Created by chl on 2022/5/22.
 * description : 装满石头的背包的最大数量
 *
 * 现有编号从0到n-1的n个背包。给你两个下标从0开始的整数数组capacity和rocks。第i个背包最大可以装capacity[i]块石头，当前已经装了rocks[i]块石头。
 * 另给你一个整数additionalRocks ，表示你可以放置的额外石头数量，石头可以往任意背包中放置。
 * 请你将额外的石头放入一些背包中，并返回放置后装满石头的背包的最大数量。
 *
 * 示例 1：
 * 输入：capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
 * 输出：3
 * 解释：
 * 1 块石头放入背包 0 ，1 块石头放入背包 1 。
 * 每个背包中的石头总数是 [2,3,4,4] 。
 * 背包 0 、背包 1 和 背包 2 都装满石头。
 * 总计 3 个背包装满石头，所以返回 3 。
 * 可以证明不存在超过 3 个背包装满石头的情况。
 * 注意，可能存在其他放置石头的方案同样能够得到 3 这个结果。
 *
 * 示例 2：
 * 输入：capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
 * 输出：3
 * 解释：
 * 8 块石头放入背包 0 ，2 块石头放入背包 2 。
 * 每个背包中的石头总数是 [10,2,2] 。
 * 背包 0 、背包 1 和背包 2 都装满石头。
 * 总计 3 个背包装满石头，所以返回 3 。
 * 可以证明不存在超过 3 个背包装满石头的情况。
 * 注意，不必用完所有的额外石头。
 *
 * 提示：
 * n == capacity.length == rocks.length
 * 1 <= n <= 5 * 10^4
 * 1 <= capacity[i] <= 10^9
 * 0 <= rocks[i] <= capacity[i]
 * 1 <= additionalRocks <= 10^9
 */
public class MaximumBags {

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int length = capacity.length;
        //背包剩余空间
        int[] last = new int[length];
        for (int i = 0; i < length; i++) {
            last[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(last);
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (last[i] == 0) {
                count++;
            } else {
                if (last[i] <= additionalRocks) {
                    count++;
                    additionalRocks -= last[i];
                } else {
                    break;
                }
            }
        }
        return count;
    }
}
