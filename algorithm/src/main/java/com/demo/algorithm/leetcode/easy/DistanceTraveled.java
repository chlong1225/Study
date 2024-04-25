package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/4/25
 * @author chenglong
 * description : 总行驶距离
 *
 * 卡车有两个油箱。给你两个整数，mainTank表示主油箱中的燃料（以升为单位），additionalTank表示副油箱中的燃料（以升为单位）。
 * 该卡车每耗费1升燃料都可以行驶10km。每当主油箱使用了5升燃料时，如果副油箱至少有1升燃料，则会将1升燃料从副油箱转移到主油箱。
 * 返回卡车可以行驶的最大距离。
 * 注意：从副油箱向主油箱注入燃料不是连续行为。这一事件会在每消耗5升燃料时突然且立即发生。
 *
 * 示例 1：
 * 输入：mainTank = 5, additionalTank = 10
 * 输出：60
 * 解释：
 * 在用掉 5 升燃料后，主油箱中燃料还剩下 (5 - 5 + 1) = 1 升，行驶距离为 50km 。
 * 在用掉剩下的 1 升燃料后，没有新的燃料注入到主油箱中，主油箱变为空。
 * 总行驶距离为 60km 。
 *
 * 示例 2：
 * 输入：mainTank = 1, additionalTank = 2
 * 输出：10
 * 解释：
 * 在用掉 1 升燃料后，主油箱变为空。
 * 总行驶距离为 10km 。
 *
 * 提示：
 * 1 <= mainTank, additionalTank <= 100
 */
public class DistanceTraveled {

    public int distanceTraveled(int mainTank, int additionalTank) {
        int sum = 0;
        while (mainTank > 0) {
            int k = mainTank / 5;
            if (k == 0) {
                sum += mainTank;
                break;
            }
            sum += 5 * k;
            mainTank -= 5 * k;
            if (additionalTank >= k) {
                mainTank += k;
                additionalTank -= k;
            } else {
                mainTank += additionalTank;
                sum += mainTank;
                break;
            }
        }
        return sum * 10;
    }
}
