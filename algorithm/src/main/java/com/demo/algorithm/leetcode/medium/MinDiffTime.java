package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;
import java.util.List;

/**
 * create on 2022/1/18
 * @author chenglong
 * description : 最小时间差
 *
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 * 示例 1：
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 *
 * 示例 2：
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *
 * 提示：
 * 2 <= timePoints <= 2 * 10^4
 * timePoints[i] 格式为 "HH:MM"
 */
public class MinDiffTime {

    public int findMinDifference(List<String> timePoints) {
        int size = timePoints.size();
        //最多不同时间数
        int maxSize = 24 * 60;
        if (size > maxSize) {
            //超过了最大不同数，肯定存在相同的时间，返回0
            return 0;
        }
        //使用空间换时间，减少时间转换的次数
        int[] datas = new int[size];
        for (int i = 0; i < size; i++) {
            String[] time = timePoints.get(i).split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            datas[i] = hour * 60 + minute;
        }
        Arrays.sort(datas);
        int diff = datas[0] + maxSize - datas[size - 1];
        for (int i = 1; i < size; i++) {
            int tem = datas[i] - datas[i - 1];
            if (tem < diff) {
                diff = tem;
            }
        }
        return diff;
    }
}
