package com.demo.algorithm.leetcode.medium;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * create on 2023/2/20
 * @author chenglong
 * description : 最大平均通过率
 *
 * 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。给你一个二维数组classes，其中classes[i]=[passi, totali]，表示你提前知道了第i个班级总共有totali个学生，其中只有passi个学生可以通过考试。
 * 给你一个整数extraStudents，表示额外有extraStudents个聪明的学生，他们一定能通过任何班级的期末考。你需要给这extraStudents个学生每人都安排一个班级，使得所有班级的平均通过率最大。
 * 一个班级的通过率等于这个班级通过考试的学生人数除以这个班级的总人数。平均通过率是所有班级的通过率之和除以班级数目。
 * 请你返回在安排这extraStudents个学生去对应班级后的最大平均通过率。与标准答案误差范围在10-5以内的结果都会视为正确结果。
 *
 * 示例 1：
 * 输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 输出：0.78333
 * 解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
 *
 * 示例 2：
 * 输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * 输出：0.53485
 *
 * 提示：
 * 1 <= classes.length <= 10^5
 * classes[i].length == 2
 * 1 <= passi <= totali <= 10^5
 * 1 <= extraStudents <= 10^5
 */
public class MaxAverageRatio {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        long base = 1;
        PriorityQueue<int[]> dates = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                long sum1 = base * (o2[1] - o2[0]) * (o1[1] + 1) * o1[1];
                long sum2 = base * (o1[1] - o1[0]) * (o2[1] + 1) * o2[1];
                if (sum1 == sum2) {
                    return 0;
                }
                return sum1 > sum2 ? 1 : -1;
            }
        });
        int length = classes.length;
        double total = 0;
        for (int i = 0; i < length; i++) {
            if (classes[i][0] == classes[i][1]) {
                total++;
            } else {
                dates.add(classes[i]);
            }
        }
        while (!dates.isEmpty() && extraStudents > 0) {
            int[] poll = dates.poll();
            poll[0]++;
            poll[1]++;
            dates.add(poll);
            extraStudents--;
        }
        while (!dates.isEmpty()) {
            int[] tem = dates.poll();
            total += (tem[0] * 1.0 / tem[1]);
        }
        return total / length;
    }
}
