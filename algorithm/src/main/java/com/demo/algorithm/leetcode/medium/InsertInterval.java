package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/31.
 * description : 插入区间
 *
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 *
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 *
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *  
 * 提示：
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 */
public class InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int length = intervals.length;
        if (length == 0) {
            int[][] result = new int[1][2];
            result[0] = newInterval;
            return result;
        }
        int index = length;
        //是否需要合并,否则插入
        boolean isMerge = false;
        //1,遍历区间并插入.
        for (int i = 0; i < length; i++) {
            int[] compare = intervals[i];
            if (newInterval[0] < compare[0]) {
                if (newInterval[1] < compare[0]) {
                    //插入index位置,之后的都需要移动
                    index = i;
                    break;
                } else {
                    //合并
                    if (newInterval[1] <= compare[1]) {
                        compare[0] = newInterval[0];
                        return intervals;
                    } else {
                        compare[0] = newInterval[0];
                        compare[1] = newInterval[1];
                        //需要从位置i检查后续是否需要合并
                        index = i;
                        isMerge = true;
                        break;
                    }
                }
            } else {
                if (newInterval[0] > compare[1]) {
                    continue;
                } else {
                    if (newInterval[1] <= compare[1]) {
                        return intervals;
                    } else {
                        compare[1] = newInterval[1];
                        //从i位置开始检查
                        index = i;
                        isMerge = true;
                        break;
                    }
                }
            }
        }
        if (isMerge) {
            int count = 0;
            int[] pre = intervals[index];
            for (int i = index + 1; i < length; i++) {
                if (pre[1] >= intervals[i][0]) {
                    count++;
                    if (intervals[i][1] < pre[1]) {
                        intervals[i] = null;
                    } else {
                        pre[1] = intervals[i][1];
                        intervals[i] = null;
                        break;
                    }
                }
            }
            if (count == 0) {
                return intervals;
            }
            int[][] result = new int[length - count][2];
            int j = 0;
            for (int i = 0; i < length; i++) {
                if (intervals[i] != null) {
                    result[j] = intervals[i];
                    j++;
                }
            }
            return result;
        } else {
            //插入新区间
            int[][] result = new int[length + 1][2];
            for (int i = 0; i < length + 1; i++) {
                if (i < index) {
                    result[i] = intervals[i];
                } else if (i == index) {
                    result[i] = newInterval;
                } else {
                    result[i] = intervals[i - 1];
                }
            }
            return result;
        }
    }
}
