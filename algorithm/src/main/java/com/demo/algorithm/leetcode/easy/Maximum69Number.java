package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create by chenglong on 2024/1/22
 * description : 6和9组成的最大数字
 *
 * 给你一个仅由数字6和9组成的正整数num。
 * 你最多只能翻转一位数字，将6变成9，或者把9变成6。
 * 请返回你可以得到的最大数字。
 *
 * 示例 1：
 * 输入：num = 9669
 * 输出：9969
 * 解释：
 * 改变第一位数字可以得到 6669 。
 * 改变第二位数字可以得到 9969 。
 * 改变第三位数字可以得到 9699 。
 * 改变第四位数字可以得到 9666 。
 * 其中最大的数字是 9969 。
 *
 * 示例 2：
 * 输入：num = 9996
 * 输出：9999
 * 解释：将最后一位从 6 变到 9，其结果 9999 是最大的数。
 *
 * 示例 3：
 * 输入：num = 9999
 * 输出：9999
 * 解释：无需改变就已经是最大的数字了。
 *
 * 提示：
 * 1 <= num <= 10^4
 * num 每一位上的数字都是6或者9。
 */
public class Maximum69Number {

    public int maximum69Number (int num) {
        //1，按照位拆分整数num
        List<Integer> dates = new ArrayList<>();
        int tem = num;
        while (tem > 0) {
            dates.add(tem % 10);
            tem /= 10;
        }
        Collections.reverse(dates);
        //2，查找变化的位置
        int findIndex = -1;
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i) == 6) {
                findIndex = i;
                break;
            }
        }
        if (findIndex == -1) {
            //没有找到6，不需要变化
            return num;
        }
        dates.set(findIndex, 9);
        int sum = dates.get(0);
        for (int i = 1; i < dates.size(); i++) {
            sum = sum * 10 + dates.get(i);
        }
        return sum;
    }
}
