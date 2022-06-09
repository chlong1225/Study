package com.demo.algorithm.leetcode.medium;

import java.util.Random;

/**
 * create on 2022/6/9
 *
 * @author chenglong
 * description : 非重叠矩形中的随机点
 *
 * 给定一个由非重叠的轴对齐矩形的数组rects，其中rects[i]=[ai, bi, xi, yi]表示(ai, bi)是第i个矩形的左下角点，(xi, yi)是第i个矩形的右上角点。
 * 设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 *
 * 请注意，整数点是具有整数坐标的点。
 * 实现Solution类:
 * Solution(int[][] rects)用给定的矩形数组rects初始化对象。
 * int[] pick()返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 *
 * 示例 1：
 * 输入:
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * 输出:
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 * 解释：
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // 返回 [1, -2]
 * solution.pick(); // 返回 [1, -1]
 * solution.pick(); // 返回 [-1, -2]
 * solution.pick(); // 返回 [-2, -2]
 * solution.pick(); // 返回 [0, 0]
 *
 * 提示：
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -10^9<= ai< xi<= 10^9
 * -10^9<= bi< yi<= 10^9
 * xi- ai<= 2000
 * yi- bi<= 2000
 * 所有的矩形不重叠。
 * pick最多被调用10^4次。
 */
public class Solution3 {

    private int[][] mRects;
    private Random random;
    private int max;
    //记录当前矩形点的起始位置与结束位置
    private int[][] dates;

    public Solution3(int[][] rects) {
        mRects = rects;
        random = new Random();
        /**
         * 分析：统计所有可能出现点的数量，将点的位置转换为一唯数据，依次排列
         */
        int length = rects.length;
        dates = new int[length][2];
        for (int i = 0; i < length; i++) {
            int[] rect = rects[i];
            int count = (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            dates[i] = new int[]{max, max + count};
            max += count;
        }
    }

    public int[] pick() {
        int[] result = new int[2];
        int num = random.nextInt(max);
        //1，查找当前位置对应的矩形
        int index = findIndex(num, dates);
        int[] rect = mRects[index];
        //2，在矩形中的第几个点，从0开始计算
        num -= dates[index][0];
        //记录一行的点数
        int row = rect[2] - rect[0] + 1;
        result[0] = rect[0] + num % row;
        result[1] = rect[1] + num / row;
        return result;
    }

    private int findIndex(int num, int[][] dates) {
        int start = 0;
        int end = dates.length - 1;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            int[] date = dates[middle];
            if (num >= date[0] && num < date[1]) {
                //num在date区间中
                return middle;
            }
            if (num >= date[1]) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return start;
    }
}
