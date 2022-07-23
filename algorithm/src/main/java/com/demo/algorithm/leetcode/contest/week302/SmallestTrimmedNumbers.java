package com.demo.algorithm.leetcode.contest.week302;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/7/23
 * @author chenglong
 * description : 裁剪数字后查询第K小的数字
 *
 * 给你一个下标从0开始的字符串数组nums，其中每个字符串长度相等且只包含数字。
 * 再给你一个下标从0开始的二维整数数组queries，其中queries[i] = [ki, trimi]。对于每个queries[i]，你需要：
 * 将nums中每个数字裁剪到剩下最右边trimi个数位。
 * 在裁剪过后的数字中，找到num中第ki小数字对应的下标。如果两个裁剪后数字一样大，那么下标更小的数字视为更小的数字。
 * 将nums中每个数字恢复到原本字符串。
 * 请你返回一个长度与queries相等的数组answer，其中answer[i]是第i次查询的结果。
 *
 * 提示：
 * 裁剪到剩下x个数位的意思是不断删除最左边的数位，直到剩下x个数位。
 * nums中的字符串可能会有前0 。
 *
 * 示例 1：
 * 输入：nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
 * 输出：[2,2,1,0]
 * 解释：
 * 1. 裁剪到只剩 1 个数位后，nums = ["2","3","1","4"] 。最小的数字是 1 ，下标为 2 。
 * 2. 裁剪到剩 3 个数位后，nums 没有变化。第 2 小的数字是 251 ，下标为 2 。
 * 3. 裁剪到剩 2 个数位后，nums = ["02","73","51","14"] 。第 4 小的数字是 73 ，下标为 1 。
 * 4. 裁剪到剩 2 个数位后，最小数字是 2 ，下标为 0 。
 *    注意，裁剪后数字 "02" 值为 2 。
 *
 * 示例 2：
 * 输入：nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
 * 输出：[3,0]
 * 解释：
 * 1. 裁剪到剩 1 个数位，nums = ["4","7","6","4"] 。第 2 小的数字是 4 ，下标为 3 。
 *    有两个 4 ，下标为 0 的 4 视为小于下标为 3 的 4 。
 * 2. 裁剪到剩 2 个数位，nums 不变。第二小的数字是 24 ，下标为 0 。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 1 <= nums[i].length <= 100
 * nums[i]只包含数字。
 * 所有nums[i].length的长度相同。
 * 1 <= queries.length <= 100
 * queries[i].length == 2
 * 1 <= ki <= nums.length
 * 1 <= trimi <= nums[0].length
 */
public class SmallestTrimmedNumbers {

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        Map<Integer, String[][]> marks = new HashMap<>();
        int length = queries.length;
        int n = nums.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int k = queries[i][0];
            int size = queries[i][1];
            if (marks.containsKey(size)) {
                result[i] = Integer.parseInt(marks.get(size)[k - 1][1]);
            } else {
                //1，构建截取后的数据源
                String[][] dates = new String[n][2];
                for (int j = 0; j < n; j++) {
                    int m = nums[j].length();
                    dates[j][0] = nums[j].substring(m - size);
                    dates[j][1] = "" + j;
                }
                //2，对数据源进行排序
                Arrays.sort(dates, (o1, o2) -> {
                    int length1 = o1[0].length();
                    for (int i1 = 0; i1 < length1; i1++) {
                        if (o1[0].charAt(i1) != o2[0].charAt(i1)) {
                            return o1[0].charAt(i1) - o2[0].charAt(i1);
                        }
                    }
                    return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
                });
                result[i] = Integer.parseInt(dates[k - 1][1]);
                marks.put(size, dates);
            }
        }
        return result;
    }
}
