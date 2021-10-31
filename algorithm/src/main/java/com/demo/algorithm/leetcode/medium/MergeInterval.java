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
        //1，合并加排序
        int length = intervals.length;
        if (length == 1) {
            return intervals;
        }
        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            if (intervals[i] == null) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (intervals[j] == null) {
                    continue;
                }
                if (intervals[i][0] <= intervals[j][0]) {
                    if (intervals[j][0] <= intervals[i][1]) {
                        //合并
                        intervals[i][1] = Math.max(intervals[i][1], intervals[j][1]);
                        intervals[j] = null;
                        count++;
                    }
                } else {
                    if (intervals[i][0] <= intervals[j][1]) {
                        //合并
                        intervals[i][0] = intervals[j][0];
                        intervals[i][1] = Math.max(intervals[i][1], intervals[j][1]);
                        intervals[j] = null;
                        count++;
                    } else {
                        //交换
                        int[] tem = intervals[j];
                        intervals[j] = intervals[i];
                        intervals[i] = tem;
                    }
                }
            }
        }
        //2,再次检查合并.之前存在遍历时无法合并,当前位置数据交换后可以合并,但当前数据之前已经比较过
        int[] pre = intervals[0];
        for (int i = 1; i < length; i++) {
            if (intervals[i] != null) {
                if (pre[1] >= intervals[i][0]) {
                    pre[1] = Math.max(pre[1], intervals[i][1]);
                    intervals[i] = null;
                    count++;
                } else {
                    pre = intervals[i];
                }
            }
        }
        int[][] result = new int[length - count][2];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (intervals[i] != null) {
                result[index] = intervals[i];
                index++;
            }
        }
        return result;
    }
}
