package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 独一无二的出现次数
 *
 * 给你一个整数数组arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回true；否则返回false。
 *
 * 示例 1：
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了3次，2 出现了2次，3只出现了1次。没有两个数的出现次数相同。
 *
 * 示例 2：
 * 输入：arr = [1,2]
 * 输出：false
 *
 * 示例 3：
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 *
 * 提示：
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 */
public class UniqueOccurrences {

    public boolean uniqueOccurrences(int[] arr) {
        //1，统计每个数出现的次数
        int[] counts = new int[2001];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] + 1000]++;
        }
        //2，检查是否出现了相同的次数
        boolean[] marks = new boolean[1001];
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                if (marks[counts[i]]) {
                    return false;
                }
                marks[counts[i]] = true;
            }
        }
        return true;
    }
}
