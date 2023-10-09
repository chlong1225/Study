package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2023/10/9
 * @author chenglong
 * description : 最小和分割
 *
 * 给你一个正整数num，请你将它分割成两个非负整数num1和num2，满足：
 * num1和num2直接连起来，得到num各数位的一个排列。
 * 换句话说，num1和num2中所有数字出现的次数之和等于num中所有数字出现的次数。
 * num1和num2可以包含前导0。
 * 请你返回num1和num2可以得到的和的最小值。
 *
 * 注意：
 * num保证没有前导 0 。
 * num1和num2中数位顺序可以与num中数位顺序不同。
 *
 * 示例 1：
 * 输入：num = 4325
 * 输出：59
 * 解释：我们可以将 4325 分割成 num1 = 24 和 num2 = 35 ，和为 59 ，59 是最小和。
 *
 * 示例 2：
 * 输入：num = 687
 * 输出：75
 * 解释：我们可以将 687 分割成 num1 = 68 和 num2 = 7 ，和为最优值 75 。
 *
 * 提示：
 * 10 <= num <= 10^9
 */
public class SplitNum {

    public int splitNum(int num) {
        //1，拆分数据获取数位值
        List<Integer> dates = new ArrayList<>();
        while (num > 0) {
            int tem = num % 10;
            if (tem > 0) {
                dates.add(tem);
            }
            num /= 10;
        }
        if (dates.size() == 1) {
            return dates.get(0);
        }
        //2，对数位进行排序
        Collections.sort(dates);
        //3，对数位值进行拆分
        int a = 0;
        int b = 0;
        for (int i = 0; i < dates.size(); i += 2) {
            a = a * 10 + dates.get(i);
        }
        for (int i = 1; i < dates.size(); i += 2) {
            b = b * 10 + dates.get(i);
        }
        return a + b;
    }
}
