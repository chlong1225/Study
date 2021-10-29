package com.demo.algorithm.leetcode.medium;

/**
 * create on 10/29/21
 * @author chenglong
 * description : 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeInterval {

    public static int[][] merge(int[][] intervals) {
        //1，将区间进行排序,使用冒泡原地排序
        int length = intervals.length;
        if (length == 1) {
            return intervals;
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (intervals[i][0] > intervals[j][0]) {
                    int[] tem = intervals[i];
                    intervals[i] = intervals[j];
                    intervals[j] = tem;
                }
            }
        }
        //2，合并覆盖相同区间
        int startIndex = 0;
        int[] result = intervals[0];
        for (int i = 1; i < length; i++) {
            if (intervals[i][0] <= result[1]) {
                //需要进行合并
                result[1] = Math.max(result[1], intervals[i][1]);
            } else {
                startIndex++;
                result = intervals[i];
                intervals[startIndex] = result;
            }
        }
        int[][] data = new int[startIndex + 1][intervals[0].length];
        for (int i = 0; i <= startIndex; i++) {
            data[i] = intervals[i];
        }
        return data;
    }
}
