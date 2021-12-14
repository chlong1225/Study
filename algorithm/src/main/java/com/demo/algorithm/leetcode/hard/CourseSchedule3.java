
package com.demo.algorithm.leetcode.hard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by chl on 2021/12/14.
 * description : 课程表III
 *
 * 这里有n门不同的在线课程，按从1到n编号。给你一个数组courses ，
 * 其中 courses[i] = [durationi, lastDayi] 表示第i门课将会持续上durationi天课，并且必须在不晚于lastDayi的时候完成。
 * 你的学期从第1天开始。且不能同时修读两门及两门以上的课程。
 * 返回你最多可以修读的课程数目。
 *
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 *
 * 示例 2：
 * 输入：courses = [[1,2]]
 * 输出：1
 *
 * 示例 3：
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 *  
 * 提示:
 * 1 <= courses.length <= 104
 * 1 <= durationi, lastDayi <= 104
 */
public class CourseSchedule3 {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int length = courses.length;
        int total = 0;
        for (int i = 0; i < length; i++) {
            if (total + courses[i][0] <= courses[i][1]) {
                total += courses[i][0];
                queue.offer(courses[i][0]);
            } else if (!queue.isEmpty() && queue.peek() > courses[i][0]) {
                total -= queue.poll() - courses[i][0];
                queue.offer(courses[i][0]);
            }
        }
        return queue.size();
    }
}
