package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/9/13
 * @author chenglong
 * description : 最大交换
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 注意:
 * 给定数字的范围是[0, 10^8]
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        //1，num只有一位时，无法交换
        if (num < 10) {
            return num;
        }
        //2，转换成字符串，依次判断是否有递增的组合
        char[] dates = ("" + num).toCharArray();
        int length = dates.length;
        for (int i = 0; i < length - 1; i++) {
            //当前需要被交换的数
            int first = dates[i] - '0';
            int findIndex = -1;
            for (int j = i + 1; j < length; j++) {
                int change = dates[j] - '0';
                if (change > first) {
                    if (findIndex == -1) {
                        findIndex = j;
                    } else {
                        int pre = dates[findIndex] - '0';
                        if (change >= pre) {
                            findIndex = j;
                        }
                    }
                }
            }
            if (findIndex != -1) {
                //交换位置i与findIndex位置的数
                char tem = dates[findIndex];
                dates[findIndex] = dates[i];
                dates[i] = tem;
                return Integer.parseInt(new String(dates));
            }
        }
        return num;
    }
}
