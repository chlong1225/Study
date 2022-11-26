package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * create on 2022/11/15
 * @author chenglong
 * description : 卡车上的最大单元数
 *
 * 请你将一些箱子装在一辆卡车 上。给你一个二维数组boxTypes，其中boxTypes[i]=[numberOfBoxesi, numberOfUnitsPerBoxi] ：
 * numberOfBoxesi是类型i的箱子的数量。
 * numberOfUnitsPerBoxi是类型i每个箱子可以装载的单元数量。
 * 整数truckSize表示卡车上可以装载箱子的最大数量。只要箱子数量不超过truckSize，你就可以选择任意箱子装到卡车上。
 * 返回卡车可以装载单元的最大总数。
 *
 * 示例 1：
 * 输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * 输出：8
 * 解释：箱子的情况如下：
 * - 1 个第一类的箱子，里面含 3 个单元。
 * - 2 个第二类的箱子，每个里面含 2 个单元。
 * - 3 个第三类的箱子，每个里面含 1 个单元。
 * 可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
 * 单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
 *
 * 示例 2：
 * 输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * 输出：91
 *
 * 提示：
 * 1 <= boxTypes.length <= 1000
 * 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 * 1 <= truckSize <= 10^6
 */
public class MaximumUnits {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        //1，按照箱子大小进行排序
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        //2，依次装载
        int total = 0;
        int index = 0;
        int length = boxTypes.length;
        while (index < length && truckSize > 0) {
            int count = boxTypes[index][0];
            if (truckSize > count) {
                total += (count * boxTypes[index][1]);
                truckSize -= count;
            } else {
                total += (truckSize * boxTypes[index][1]);
                truckSize = 0;
            }
            index++;
        }
        return total;
    }
}
