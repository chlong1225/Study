package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2023/10/11
 * @author chenglong
 * description :  奖励最顶尖的K名学生
 *
 * 给你两个字符串数组positive_feedback和negative_feedback，分别包含表示正面的和负面的词汇。不会有单词同时是正面的和负面的。
 * 一开始，每位学生分数为0。每个正面的单词会给学生的分数加3分，每个负面的词会给学生的分数减1分。
 * 给你n个学生的评语，用一个下标从0开始的字符串数组report和一个下标从0开始的整数数组student_id 表示，其中student_id[i]表示这名学生的ID，这名学生的评语是report[i]。每名学生的ID互不相同。
 * 给你一个整数k，请你返回按照得分从高到低最顶尖的k名学生。如果有多名学生分数相同，ID越小排名越前。
 *
 * 示例 1：
 * 输入：positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
 * 输出：[1,2]
 * 解释：
 * 两名学生都有 1 个正面词汇，都得到 3 分，学生 1 的 ID 更小所以排名更前。
 *
 * 示例 2：
 * 输入：positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
 * 输出：[2,1]
 * 解释：
 * - ID 为 1 的学生有 1 个正面词汇和 1 个负面词汇，所以得分为 3-1=2 分。
 * - ID 为 2 的学生有 1 个正面词汇，得分为 3 分。
 * 学生 2 分数更高，所以返回 [2,1] 。
 *
 * 提示：
 * 1 <= positive_feedback.length, negative_feedback.length <= 10^4
 * 1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
 * positive_feedback[i] 和 negative_feedback[j] 都只包含小写英文字母。
 * positive_feedback 和 negative_feedback 中不会有相同单词。
 * n == report.length == student_id.length
 * 1 <= n <= 10^4
 * report[i] 只包含小写英文字母和空格 ' ' 。
 * report[i] 中连续单词之间有单个空格隔开。
 * 1 <= report[i].length <= 100
 * 1 <= student_id[i] <= 10^9
 * student_id[i] 的值 互不相同 。
 * 1 <= k <= n
 */
public class TopStudents {

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        //1，使用Hash存储正面或负面词汇，便于查找
        Set<String> positives = new HashSet<>();
        Set<String> negatives = new HashSet<>();
        for (int i = 0; i < positive_feedback.length; i++) {
            positives.add(positive_feedback[i]);
        }
        for (int i = 0; i < negative_feedback.length; i++) {
            negatives.add(negative_feedback[i]);
        }
        int n = student_id.length;
        int[][] dates = new int[n][2];
        //2，计算每个学生的得分
        for (int i = 0; i < n; i++) {
            String[] words = report[i].split(" ");
            int score = 0;
            for (int j = 0; j < words.length; j++) {
                if (positives.contains(words[j])) {
                    score += 3;
                } else if (negatives.contains(words[j])) {
                    score--;
                }
            }
            dates[i][0] = score;
            dates[i][1] = student_id[i];
        }
        //3，排序
        Arrays.sort(dates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        //4，从排序中提取前k个数据
        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(dates[i][1]);
        }
        return result;
    }
}
