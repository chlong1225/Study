package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/1/23
 * @author chenglong
 * description : 统计最大组的数目
 *
 * 给你一个整数n。请你先求出从1到n的每个整数10进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
 * 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
 *
 * 示例 1：
 * 输入：n = 13
 * 输出：4
 * 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
 * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：2
 * 解释：总共有 2 个大小为 1 的组 [1]，[2]。
 *
 * 示例 3：
 * 输入：n = 15
 * 输出：6
 *
 * 示例 4：
 * 输入：n = 24
 * 输出：5
 *
 * 提示：
 * 1 <= n <= 10^4
 */
public class CountLargestGroup {

    public int countLargestGroup(int n) {
        int[] counts = new int[37];
        for (int i = 1; i <= n; i++) {
            int sum = getSum(i);
            counts[sum]++;
        }
        int answer = 0;
        int maxCount = 0;
        for (int i = 0; i < 37; i++) {
            if (counts[i] > 0) {
                if (counts[i] > maxCount) {
                    maxCount = counts[i];
                    answer = 1;
                } else if (counts[i] == maxCount) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private int getSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }
}
