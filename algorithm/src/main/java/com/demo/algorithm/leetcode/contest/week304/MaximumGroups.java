package com.demo.algorithm.leetcode.contest.week304;

/**
 * create on 2022/8/4
 * @author chenglong
 * description : 分组的最大数量
 *
 * 给你一个正整数数组grades表示大学中一些学生的成绩。你打算将所有学生分为一些有序的非空分组，其中分组间的顺序满足以下全部条件：
 * 第i个分组中的学生总成绩小于第 (i + 1) 个分组中的学生总成绩，对所有组均成立（除了最后一组）。
 * 第i个分组中的学生总数小于第 (i + 1) 个分组中的学生总数，对所有组均成立（除了最后一组）。
 * 返回可以形成的最大组数。
 *
 * 示例 1：
 * 输入：grades = [10,6,12,7,3,5]
 * 输出：3
 * 解释：下面是形成 3 个分组的一种可行方法：
 * - 第 1 个分组的学生成绩为 grades = [12] ，总成绩：12 ，学生数：1
 * - 第 2 个分组的学生成绩为 grades = [6,7] ，总成绩：6 + 7 = 13 ，学生数：2
 * - 第 3 个分组的学生成绩为 grades = [10,3,5] ，总成绩：10 + 3 + 5 = 18 ，学生数：3
 * 可以证明无法形成超过 3 个分组。
 *
 * 示例 2：
 * 输入：grades = [8,8]
 * 输出：1
 * 解释：只能形成 1 个分组，因为如果要形成 2 个分组的话，会导致每个分组中的学生数目相等。
 *
 * 提示：
 * 1 <= grades.length <= 10^5
 * 1 <= grades[i] <= 10^5
 */
public class MaximumGroups {

    public int maximumGroups(int[] grades) {
        //直接从小到大依次分配，数量依次为1，2，3...n
        int length = grades.length;
        //此时：(n)*(n+1)/2<=length。使用二分法
        int count = 1;
        for (int i = 1; i < length; i++) {
            int tem = (i + 1) * i / 2;
            if (tem < length) {
                count = i;
            } else if (tem == length) {
                return i;
            } else {
                break;
            }
        }
        return count;
    }

    public int maximumGroups2(int[] grades) {
        //直接从小到大依次分配，数量依次为1，2，3...n
        int length = grades.length;
        //此时：(n)*(n+1)/2<=length。使用二分法
        if (length < 3) {
            return 1;
        }
        int start = 1;
        int end = length;
        long base = 1;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            long tem = base * (middle + 1) * middle / 2;
            if (tem == length) {
                return middle;
            } else if (tem > length) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        long check = base * start * (start + 1) / 2;
        if (check > length) {
            return start - 1;
        }
        return start;
    }
}
