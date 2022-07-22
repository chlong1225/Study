package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2022/7/22
 * @author chenglong
 * description : 设置交集大小至少为2
 *
 * 一个整数区间[a, b](a < b) 代表着从a到b的所有连续整数，包括a和b。
 * 给你一组整数区间intervals，请找到一个最小的集合S，使得S里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 *
 * 示例 2:
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 *
 * intervals的长度范围为[1, 3000]。
 * intervals[i]长度为2，分别代表左、右边界。
 * intervals[i][j] 的值是[0, 10^8]范围内的整数。
 */
public class IntersectionSizeTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        //1，对区间进行排序
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        //2，遍历区间，过滤无效区间。如果[a,b],[c,d]两个区间，a<c,b>d。此时[c,d]中任意两个整数在[a,b]中都能找到。只需要满足[c,d]区间，此时[a,b]属于无效区间
        List<int[]> dates = new ArrayList<>();
        int length = intervals.length;
        for (int i = 0; i < length; i++) {
            boolean isAdd = true;
            while (dates.size() > 0) {
                int[] pre = dates.get(dates.size() - 1);
                int[] cur = intervals[i];
                if (cur[0] == pre[0]) {
                    isAdd = false;
                    break;
                } else {
                    if (cur[1] <= pre[1]) {
                        //此时pre无效
                        dates.remove(dates.size() - 1);
                    } else {
                        break;
                    }
                }
            }
            if (isAdd) {
                dates.add(intervals[i]);
            }
        }
        //3，遍历区间查找满足条件的区间
        int count = 0;
        int preEnd = -1;
        int end = dates.get(0)[1];
        int start = end - 1;
        int preIndex = 0;
        for (int i = 1; i < dates.size(); i++) {
            if (dates.get(i)[0] > dates.get(preIndex)[1]) {
                //两个区间没有交集，此时重置初始位置
                count += (end - start + 1);
                preEnd = end;
                end = dates.get(i)[1];
                start = end - 1;
                preIndex = i;
            } else if (dates.get(i)[0] == dates.get(preIndex)[1]) {
                //两个区间相连
                count += (end - start + 1);
                preIndex = i;
                preEnd = end;
                end = dates.get(i)[1];
                start = end;
            } else {
                if (start == end) {
                    if (preEnd < dates.get(i)[0]) {
                        count += 1;
                        preIndex = i;
                        preEnd = end;
                        end = dates.get(i)[1];
                        start = end;
                    }
                }
            }
        }
        count += (end - start + 1);
        return count;
    }
}
