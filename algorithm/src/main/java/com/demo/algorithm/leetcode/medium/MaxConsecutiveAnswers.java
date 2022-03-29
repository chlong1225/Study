package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/29.
 * description : 考试的最大困扰度
 *
 * 一位老师正在出一场由n道判断题构成的考试，每道题的答案为true（用'T'表示）或者false（用'F'表示）。老师想增加学生对自己做出答案的不确定性，
 * 方法是最大化有连续相同结果的题数。（也就是连续出现true 或者连续出现false）。
 * 给你一个字符串answerKey，其中answerKey[i]是第i个问题的正确结果。除此以外，还给你一个整数k，表示你能进行以下操作的最多次数：
 * 每次操作中，将问题的正确答案改为'T'或者'F'（也就是将answerKey[i]改为'T'或者'F'）。
 * 请你返回在不超过k次操作的情况下，最大连续'T'或者'F'的数目。
 *
 * 示例 1：
 * 输入：answerKey = "TTFF", k = 2
 * 输出：4
 * 解释：我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "TTTT" 。
 * 总共有四个连续的 'T' 。
 *
 * 示例 2：
 * 输入：answerKey = "TFFT", k = 1
 * 输出：3
 * 解释：我们可以将最前面的 'T' 换成 'F' ，得到 answerKey = "FFFT" 。
 * 或者，我们可以将第二个 'T' 换成 'F' ，得到 answerKey = "TFFF" 。
 * 两种情况下，都有三个连续的 'F' 。
 *
 * 示例 3：
 * 输入：answerKey = "TTFTTFTT", k = 1
 * 输出：5
 * 解释：我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "TTTTTFTT" 。
 * 或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTFTTTTT" 。
 * 两种情况下，都有五个连续的 'T' 。
 *
 * 提示：
 * n == answerKey.length
 * 1 <= n <= 5 * 10^4
 * answerKey[i]要么是'T'要么是'F'
 * 1 <= k <= n
 */
public class MaxConsecutiveAnswers {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(getMaxLength(answerKey, 'T', k), getMaxLength(answerKey, 'F', k));
    }

    private int getMaxLength(String answerKey, char c, int k) {
        int length = answerKey.length();
        //1，获取最长的T
        int max = 0;
        int start = 0;
        int end = 0;
        int diff = 0;
        while (end < length) {
            if (answerKey.charAt(end) == c) {
                //记录start~end区间F的数量
                diff++;
                if (diff > k) {
                    int num = end - start;
                    if (max < num) {
                        max = num;
                    }
                    //start需要向右偏移一个F
                    for (int i = start; i < end; i++) {
                        if (answerKey.charAt(i) == c) {
                            start = i + 1;
                            break;
                        }
                    }
                }
            }
            end++;
        }
        if (end - start > max) {
            max = end - start;
        }
        return max;
    }
}