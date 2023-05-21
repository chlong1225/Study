package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/5/17
 * @author chenglong
 * description : 判断两个事件是否存在冲突
 *
 * 给你两个字符串数组event1和event2，表示发生在同一天的两个闭区间时间段事件，其中：
 * event1 = [startTime1, endTime1] 且 event2 = [startTime2, endTime2]
 * 事件的时间为有效的24小时制且按HH:MM格式给出。
 * 当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现冲突。
 * 如果两个事件之间存在冲突，返回true；否则，返回false 。
 *
 * 示例 1：
 * 输入：event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
 * 输出：true
 * 解释：两个事件在 2:00 出现交集。
 * 示例 2：
 *
 * 输入：event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
 * 输出：true
 * 解释：两个事件的交集从 01:20 开始，到 02:00 结束。
 * 示例 3：
 *
 * 输入：event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
 * 输出：false
 * 解释：两个事件不存在交集。
 *
 * 提示：
 * evnet1.length == event2.length == 2.
 * event1[i].length == event2[i].length == 5
 * startTime1 <= endTime1
 * startTime2 <= endTime2
 * 所有事件的时间都按照HH:MM格式给出
 */
public class HaveConflict {

    public boolean haveConflict(String[] event1, String[] event2) {
        int start1 = getTime(event1[0]);
        int end1 = getTime(event1[1]);
        int start2 = getTime(event2[0]);
        int end2 = getTime(event2[1]);
        if (end1 < start2 || start1 > end2) {
            return false;
        }
        return true;
    }

    private int getTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3));
        return hour * 60 + min;
    }
}
