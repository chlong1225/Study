package com.demo.algorithm.leetcode.contest.week301;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/8/3
 * @author chenglong
 * description : 统计理想数组的数目
 *
 * 给你两个整数n和maxValue，用于描述一个理想数组 。
 * 对于下标从0开始、长度为n的整数数组arr，如果满足以下条件，则认为该数组是一个理想数组 ：
 * 每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。
 * 每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。
 * 返回长度为n的不同理想数组的数目。由于答案可能很大，返回对10^9+7取余的结  果。
 *
 * 示例 1：
 * 输入：n = 2, maxValue = 5
 * 输出：10
 * 解释：存在以下理想数组：
 * - 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
 * - 以 2 开头的数组（2 个）：[2,2]、[2,4]
 * - 以 3 开头的数组（1 个）：[3,3]
 * - 以 4 开头的数组（1 个）：[4,4]
 * - 以 5 开头的数组（1 个）：[5,5]
 * 共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
 *
 * 示例 2：
 * 输入：n = 5, maxValue = 3
 * 输出：11
 * 解释：存在以下理想数组：
 * - 以 1 开头的数组（9 个）：
 *    - 不含其他不同值（1 个）：[1,1,1,1,1]
 *    - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
 *    - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
 * - 以 2 开头的数组（1 个）：[2,2,2,2,2]
 * - 以 3 开头的数组（1 个）：[3,3,3,3,3]
 * 共计 9 + 1 + 1 = 11 个不同理想数组。
 *
 * 提示：
 * 2 <= n <= 10^4
 * 1 <= maxValue <= 10^4
 */
public class IdealArrays {

    private static final int MOD = 1000000007;
    private static final int MAX = 10001;
    private static final boolean[] marks = new boolean[MAX];
    private static final List<Integer> nums = new ArrayList<>();
    private static final List<Integer>[] dates = new ArrayList[MAX];
    //记录最多质因数的个数
    private static int maxCount = 1;
    //组合数
    private static int[][] counts;

    static {
        //1，获取MAX内所有的质数
        for (int i = 1; i < MAX; i++) {
            marks[i] = true;
        }
        for (int i = 2; i < MAX; i++) {
            if (marks[i]) {
                for (int j = 2; j * i < MAX; j++) {
                    marks[i * j] = false;
                }
            }
        }
        for (int i = 2; i < MAX; i++) {
            if (marks[i]) {
                nums.add(i);
            }
        }
        //2，预处理拆分质因数
        for (int i = 1; i < MAX; i++) {
            dates[i] = new ArrayList();
            //当前值
            int cur = i;
            if (marks[cur]) {
                //当前值为质数，无法拆分
                dates[i].add(cur);
            } else {
                //拆分质因数
                for (int j = 0; j < nums.size(); j++) {
                    if (cur % nums.get(j) == 0) {
                        dates[i].add(nums.get(j));
                        dates[i].addAll(dates[cur / nums.get(j)]);
                        if (dates[i].size() > maxCount) {
                            maxCount = dates[i].size();
                        }
                        break;
                    }
                }
            }
        }
        //统计分解质数的个数
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 1; i < dates.length; i++) {
            List<Integer> tem = dates[i];
            if (i == 1) {
                tem.clear();
            } else {
                if (tem.size() > 0) {
                    for (int j = 0; j < tem.size(); j++) {
                        int cur = tem.get(j);
                        if (mp.containsKey(cur)) {
                            mp.put(cur, mp.get(cur) + 1);
                        } else {
                            mp.put(cur, 1);
                        }
                    }
                    tem.clear();
                    for (int key : mp.keySet()) {
                        tem.add(mp.get(key));
                    }
                    mp.clear();
                }
            }
        }
        //预处理组合数，其实就是杨辉三角
        counts = new int[maxCount + MAX][maxCount + 1];
        counts[0][0] = 1;
        for (int i = 1; i < maxCount + MAX; i++) {
            counts[i][0] = 1;
            for (int j = 1; j <= Math.min(i, maxCount); j++) {
                counts[i][j] = (counts[i - 1][j] + counts[i - 1][j - 1]) % MOD;
            }
        }
    }

    public int idealArrays(int n, int maxValue) {
        long sum = 0;
        for (int i = 1; i <= maxValue; i++) {
            long mul = 1;
            for (int j = 0; j < dates[i].size(); j++) {
                mul = mul * counts[n + dates[i].get(j) - 1][dates[i].get(j)] % MOD;
            }
            sum += mul;
            sum %= MOD;
        }
        return (int) sum;
    }
}
