package com.demo.algorithm.leetcode.lcp;

/**
 * create on 2023/12/30
 * @author chenglong
 * description : LCP 61. 气温变化趋势
 *
 * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第i~(i+1)天的气温变化趋势，将根据以下规则判断：
 * 若第i+1天的气温高于第i天，为上升趋势
 * 若第i+1天的气温等于第i天，为平稳趋势
 * 若第i+1天的气温低于第i天，为下降趋势
 * 已知temperatureA[i]和temperatureB[i]分别表示第i天两地区的气温。 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。
 * 请分析并返回两地气温变化趋势相同的最大连续天数。
 * 即最大的n，使得第i~i+n天之间，两地气温变化趋势相同
 *
 * 示例 1：
 * 输入： temperatureA = [21,18,18,18,31] temperatureB = [34,32,16,16,17]
 * 输出：2
 * 解释：如下表所示， 第 2～4 天两地气温变化趋势相同，且持续时间最长，因此返回 4-2=2
 *
 * 示例 2：
 * 输入： temperatureA = [5,10,16,-6,15,11,3] temperatureB = [16,22,23,23,25,3,-16]
 * 输出：3
 *
 * 提示：
 * 2 <= temperatureA.length == temperatureB.length <= 1000
 * -20 <= temperatureA[i], temperatureB[i] <= 40
 */
public class TemperatureTrend {

    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        //1，预处理变化趋势。上升记录为1，平稳记录为0，下降记录为-1
        int n = temperatureA.length;
        for (int i = 1; i < n; i++) {
            if (temperatureA[i] == temperatureA[i - 1]) {
                temperatureA[i - 1] = 0;
            } else if (temperatureA[i] > temperatureA[i - 1]) {
                temperatureA[i - 1] = 1;
            } else {
                temperatureA[i - 1] = -1;
            }
            if (temperatureB[i] == temperatureB[i - 1]) {
                temperatureB[i - 1] = 0;
            } else if (temperatureB[i] > temperatureB[i - 1]) {
                temperatureB[i - 1] = 1;
            } else {
                temperatureB[i - 1] = -1;
            }
        }
        int max = 0;
        int sameCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (temperatureA[i] == temperatureB[i]) {
                sameCount++;
            } else {
                if (sameCount > max) {
                    max = sameCount;
                }
                sameCount = 0;
            }
        }
        if (sameCount > max) {
            max = sameCount;
        }
        return max;
    }
}
