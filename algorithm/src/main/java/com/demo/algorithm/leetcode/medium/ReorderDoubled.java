package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/4/1
 * @author chenglong
 * description : 二倍数对数组
 *
 * 给定一个长度为偶数的整数数组arr，只有对arr进行重组后可以满足“对于每个0<=i<len(arr)/2，都有arr[2 * i + 1] = 2 * arr[2 * i]”时，返回 true；否则，返回 false。
 *
 * 示例 1：
 * 输入：arr = [3,1,3,6]
 * 输出：false
 *
 * 示例 2：
 * 输入：arr = [2,1,2,6]
 * 输出：false
 *
 * 示例 3：
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *
 * 提示：
 * 0 <= arr.length <= 3 * 10^4
 * arr.length 是偶数
 * -10^5 <= arr[i] <= 10^5
 */
public class ReorderDoubled {

    public boolean canReorderDoubled(int[] arr) {
        /**
         * 分析：对数组进行拆分，负数的倍数肯定是负数，正数的倍数一定是倍数。同时需要先排序，然后一次查找。
         * 比如：2，4，1，8。2的倍数时4，当前组合使用完后剩下1，8无法组合。但实际可以的组合为：1，2；4，8
         */
        int length = arr.length;
        if (length == 0) {
            return true;
        }
        //统计负数的数量
        int negativeCount = 0;
        //统计0的数量
        int zeroCount = 0;
        //统计所有的负数
        List<Integer> negativeDates = new ArrayList<>();
        //统计所有的正数
        List<Integer> positiveDates = new ArrayList<>();
        //统计数字出现的次数
        Map<Integer, Integer> marks = new HashMap();
        //1，预处理arr数组
        for (int i = 0; i < length; i++) {
            if (arr[i] == 0) {
                zeroCount++;
            } else if (arr[i] < 0) {
                negativeCount++;
                if (marks.get(arr[i]) == null) {
                    negativeDates.add(arr[i]);
                    marks.put(arr[i], 1);
                } else {
                    marks.put(arr[i], marks.get(arr[i]) + 1);
                }
            } else {
                if (marks.get(arr[i]) == null) {
                    positiveDates.add(arr[i]);
                    marks.put(arr[i], 1);
                } else {
                    marks.put(arr[i], marks.get(arr[i]) + 1);
                }
            }
        }
        //2，处理特殊场景
        if ((negativeCount % 2 != 0) || (zeroCount % 2 != 0)) {
            //负数数量为奇数或0的数量为奇数时，都无法构成二倍数对数组
            return false;
        }
        //3，对负数，正数分别排序
        Collections.sort(negativeDates);
        Collections.sort(positiveDates);
        //记录构成二倍数对的数量
        int count = zeroCount;
        //4，判断负数是否构成二倍数对数组。负数的二倍数为：num/2
        for (int i = 0; i < negativeDates.size(); i++) {
            int cur = negativeDates.get(i);
            if (marks.get(cur) == 0) {
                continue;
            }
            //cur没有被使用时，必须为偶数，才会有
            if (cur % 2 != 0) {
                return false;
            }
            int find = cur / 2;
            //二倍数cur的数量必须小于等于find
            if (marks.get(find) == null || marks.get(cur) > marks.get(find)) {
                return false;
            }
            int num = marks.get(cur);
            count += (num * 2);
            marks.put(cur, 0);
            marks.put(find, marks.get(find) - num);
        }
        //5，判断正数是否构成二倍数对数组。正数的二倍数为：num*2
        for (int i = 0; i < positiveDates.size(); i++) {
            int cur = positiveDates.get(i);
            if (marks.get(cur) == 0) {
                continue;
            }
            int find = 2 * cur;
            if (marks.get(find) == null || marks.get(cur) > marks.get(find)) {
                return false;
            }
            int num = marks.get(cur);
            count += (2 * num);
            marks.put(cur, 0);
            marks.put(find, marks.get(find) - num);
        }
        return length == count;
    }
}
