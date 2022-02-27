package com.demo.algorithm.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2022/2/22.
 * description : 好子集的数目
 *
 * 给你一个整数数组nums。如果nums的一个子集中，所有元素的乘积可以表示为一个或多个互不相同的质数的乘积，那么我们称它为好子集。
 * 比方说，如果nums = [1, 2, 3, 4]：
 * [2, 3]，[1, 2, 3]和[1, 3]是好子集，乘积分别为6 = 2*3，6 = 2*3和3 = 3。
 * [1, 4] 和[4]不是好子集，因为乘积分别为4=2*2和4=2*2。
 * 请你返回nums中不同的好子集的数目对10^9 + 7取余的结果。
 *
 * nums中的子集是通过删除nums中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：6
 * 解释：好子集为：
 * - [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 *
 * 示例 2：
 * 输入：nums = [4,2,3,15]
 * 输出：5
 * 解释：好子集为：
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
 * - [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 30
 */
public class GoodSubCount {

    private static final int MOD = 1000000007;

    public int numberOfGoodSubsets(int[] nums) {
        //1，统计每个数字出现的次数
        int[] counts = new int[31];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            counts[nums[i]]++;
        }
        if (counts[1] == length) {
            //特殊场景：全部数
            return 0;
        }
        //2，对数据进行分类：
        /**
         * 1单独处理
         * 2,3,5,7,11,13,17,19,23,29为质数
         * 6,10,14,15,21,22,26,30均为好数
         * 4，8,9,12,16,18,20,24,25,27,28带有平方因子，不能出现
         * 好数都可以转成质数组合。这里可以使用10个二进制表示
         */
        //定义数据对照表。这个就是常见的状态压缩
        Map<Integer, Integer> dates = new HashMap<>();
        //添加质数
        dates.put(2, 1);
        dates.put(3, 1 << 1);
        dates.put(5, 1 << 2);
        dates.put(7, 1 << 3);
        dates.put(11, 1 << 4);
        dates.put(13, 1 << 5);
        dates.put(17, 1 << 6);
        dates.put(19, 1 << 7);
        dates.put(23, 1 << 8);
        dates.put(29, 1 << 9);
        //添加非质数的好数
        //6 = 2*3
        dates.put(6, dates.get(2) + dates.get(3));
        dates.put(10, dates.get(2) + dates.get(5));
        dates.put(14, dates.get(2) + dates.get(7));
        dates.put(15, dates.get(3) + dates.get(5));
        dates.put(21, dates.get(3) + dates.get(7));
        dates.put(22, dates.get(2) + dates.get(11));
        dates.put(26, dates.get(2) + dates.get(13));
        dates.put(30, dates.get(2) + dates.get(3) + dates.get(5));
        //3，遍历数据源统计
        Map<Integer, Integer> marks = new HashMap<>();
        Map<Integer, Integer> next = new HashMap<>();
        for (int i = 2; i <= 30; i++) {
            if (i % 4 == 0 || i % 9 == 0 || i % 25 == 0) {
                continue;
            }
            if (counts[i] == 0) {
                continue;
            }
            if (marks.isEmpty()) {
                marks.put(dates.get(i), counts[i]);
                next.putAll(marks);
            } else {
                //遍历marks中的值，看看能不能将当前的i添加进去
                for (int key : marks.keySet()) {
                    if ((key & dates.get(i)) == 0) {
                        int tem = key | dates.get(i);
                        long count1 = marks.get(key) * 1L * counts[i];
                        int count = (int) (count1 % MOD);
                        if (next.get(tem) == null) {
                            next.put(tem, count);
                        } else {
                            next.put(tem, (next.get(tem) + count) % MOD);
                        }
                    }
                }
                //当前好数需要添加到集合中
                if (next.get(dates.get(i)) == null) {
                    next.put(dates.get(i), counts[i]);
                } else {
                    next.put(dates.get(i), (next.get(dates.get(i)) + counts[i]) % MOD);
                }
                marks.clear();
                marks.putAll(next);
            }
        }
        int sum = 0;
        for (int key : marks.keySet()) {
            sum += marks.get(key);
            sum %= MOD;
        }
        //4，添加对1的统计
        for (int i = 0; i < counts[1]; i++) {
            sum *= 2;
            sum %= MOD;
        }
        return sum;
    }

    public int numberOfGoodSubsets2(int[] nums) {
        //1，统计数字出现的次数
        int[] counts = new int[31];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            counts[nums[i]]++;
        }
        //2，数字进行分类并枚举
        //2.1统计质数：2,3,5,7,11,13,17,19,23,29
        long sum = 0;
        long a = 1;
        a = a * (counts[2] + 1) % MOD;
        a = a * (counts[3] + 1) % MOD;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a--;
        sum += a;
        //2.2统计其它好数
        //6
        a = 1;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[6];
        sum = (sum + a) % MOD;
        //10
        a = 1;
        a = a * (counts[3] + 1) % MOD;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[10];
        sum = (sum + a) % MOD;
        //14
        a = 1;
        a = a * (counts[3] + 1) % MOD;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[14];
        sum = (sum + a) % MOD;
        //15
        a = 1;
        a = a * (counts[2] + 1) % MOD;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[15];
        sum = (sum + a) % MOD;
        //21
        a = 1;
        a = a * (counts[2] + 1) % MOD;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[21];
        sum = (sum + a) % MOD;
        //22
        a = 1;
        a = a * (counts[3] + 1) % MOD;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[22];
        sum = (sum + a) % MOD;
        //26
        a = 1;
        a = a * (counts[3] + 1) % MOD;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[26];
        sum = (sum + a) % MOD;
        //30
        a = 1;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a *= counts[30];
        sum = (sum + a) % MOD;
        //两个好数组合: 10,21；14,15；15,22；15,26；21,22；21,26
        //10,21
        a = 1;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a = a * counts[10] % MOD;
        a *= counts[21];
        sum = (sum + a) % MOD;
        //14,15
        a = 1;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a = a * counts[14] % MOD;
        a *= counts[15];
        sum = (sum + a) % MOD;
        //15,22
        a = 1;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a = a * counts[15] % MOD;
        a *= counts[22];
        sum = (sum + a) % MOD;
        //15,26
        a = 1;
        a = a * (counts[7] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a = a * counts[15] % MOD;
        a *= counts[26];
        sum = (sum + a) % MOD;
        //21,22
        a = 1;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[13] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a = a * counts[21] % MOD;
        a *= counts[22];
        sum = (sum + a) % MOD;
        //21,26
        a = 1;
        a = a * (counts[5] + 1) % MOD;
        a = a * (counts[11] + 1) % MOD;
        a = a * (counts[17] + 1) % MOD;
        a = a * (counts[19] + 1) % MOD;
        a = a * (counts[23] + 1) % MOD;
        a = a * (counts[29] + 1) % MOD;
        a = a * counts[21] % MOD;
        a *= counts[26];
        sum = (sum + a) % MOD;
        //枚举三个以上好数组合不存在
        //2.3：统计数字1
        for (int i = 0; i < counts[1]; i++) {
            sum *= 2;
            sum %= MOD;
        }
        return (int) sum;
    }
}
