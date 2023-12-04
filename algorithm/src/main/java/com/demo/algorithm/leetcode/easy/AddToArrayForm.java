package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 数组形式的整数加法
 *
 * 整数的数组形式num是按照从左到右的顺序表示其数字的数组。
 * 例如，对于num=1321，数组形式是[1,3,2,1]。
 * 给定num，整数的数组形式，和整数k，返回整数num+k的数组形式。
 *
 * 示例 1：
 * 输入：num = [1,2,0,0], k = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 *
 * 示例 2：
 * 输入：num = [2,7,4], k = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 *
 * 示例 3：
 * 输入：num = [2,1,5], k = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 *
 * 提示：
 * 1 <= num.length <= 10^4
 * 0 <= num[i] <= 9
 * num 不包含任何前导零，除了零本身
 * 1 <= k <= 10^4
 */
public class AddToArrayForm {

    public List<Integer> addToArrayForm(int[] num, int k) {
        int n = num.length;
        List<Integer> dates = new ArrayList<>(n);
        int isAdd = 0;
        for (int i = n - 1; i >= 0; i--) {
            int sum = num[i] + isAdd;
            if (k > 0) {
                sum += (k % 10);
                k /= 10;
            }
            if (sum >= 10) {
                isAdd = 1;
                sum -= 10;
            } else {
                isAdd = 0;
            }
            dates.add(sum);
        }
        while (k > 0) {
            int sum = k % 10 + isAdd;
            k /= 10;
            if (sum >= 10) {
                sum -= 10;
                isAdd = 1;
            } else {
                isAdd = 0;
            }
            dates.add(sum);
        }
        if (isAdd > 0) {
            dates.add(isAdd);
        }
        Collections.reverse(dates);
        return dates;
    }
}
