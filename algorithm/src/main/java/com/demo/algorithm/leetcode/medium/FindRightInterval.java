package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/5/20
 * @author chenglong
 * description : 寻找右区间
 *
 * 给你一个区间数组intervals，其中intervals[i] = [starti, endi]，且每个starti都不同 。
 * 区间i的右侧区间可以记作区间j，并满足startj>=endi，且startj最小化 。
 * 返回一个由每个区间i的右侧区间的最小起始位置组成的数组。如果某个区间i不存在对应的右侧区间，则下标i处的值设为-1 。
 *
 * 示例 1：
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 *
 * 示例 2：
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1,0,1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 *
 * 示例 3：
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1,2,-1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 *
 * 提示：
 * 1 <=intervals.length <= 2 * 10^4
 * intervals[i].length == 2
 * -10^6 <= starti <= endi <= 10^6
 * 每个间隔的起点都不相同
 */
public class FindRightInterval {

    public int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;
        int[] result = new int[length];
        if (length == 1) {
            result[0] = -1;
            return result;
        }
        //1，记录起始点与对应的位置
        int[][] marks = new int[length][2];
        for (int i = 0; i < length; i++) {
            marks[i][0] = intervals[i][0];
            marks[i][1] = i;
        }
        //2，对起始点进行排序
        Arrays.sort(marks, (o1, o2) -> o1[0] - o2[0]);
        //3，使用二分查找刚好大于的起始点
        for (int i = 0; i < length; i++) {
            int end = intervals[i][1];
            result[i] = findIndex(end, marks);
        }
        return result;
    }

    private int findIndex(int target, int[][] marks) {
        int left = 0;
        int right = marks.length - 1;
        //1，先比较边界场景
        if (target > marks[right][0]) {
            return -1;
        }
        if (target == marks[right][0]) {
            return marks[right][1];
        }
        if (target <= marks[left][0]) {
            return marks[left][1];
        }
        //2，二分查找
        while (left < right) {
            int middle = (right - left) / 2 + left;
            if (marks[middle][0] == target) {
                return marks[middle][1];
            }
            if (marks[middle][0] > target) {
                right = middle;
            } else {
                left = middle;
                if (marks[left + 1][0] > target) {
                    return marks[left + 1][1];
                }
            }
        }
        return 0;
    }
}
