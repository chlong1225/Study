package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 8/30/21
 * description : 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 */
public class IntReverse {

    public static int getReverse(int num) {
        long min = 1;
        for (int i = 0; i < 31; i++) {
            min *= 2;
        }
        long tem = min;
        int minLength = 1;
        while (tem / 10 > 0) {
            tem = tem / 10;
            minLength++;
        }
        tem = min;
        int[] minSplit = new int[minLength];
        for (int i = 0; i < minLength; i++) {
            minSplit[minLength - 1 - i] = (int) (tem % 10);
            tem = tem / 10;
        }

        long max = min - 1;
        tem = max;
        int maxLength = 1;
        while (tem / 10 > 0) {
            tem = tem / 10;
            maxLength++;
        }
        tem = max;
        int[] maxSplit = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            maxSplit[maxLength - 1 - i] = (int) (tem % 10);
            tem = tem / 10;
        }
        if (num == 0) {
            return 0;
        }
        //是否大于0
        boolean isMoreThanZero = true;
        long checkNum = num;
        if (num < 0) {
            isMoreThanZero = false;
            checkNum = -checkNum;
        }
        //将Num按位拆分
        tem = checkNum;
        int checkLength = 1;
        while (tem / 10 > 0) {
            tem = tem / 10;
            checkLength++;
        }
        tem = checkNum;
        int[] checkSplit = new int[checkLength];
        for (int i = 0; i < checkLength; i++) {
            int indexValue = (int) (tem % 10);
            checkSplit[checkLength - 1 - i] = indexValue;
            tem = tem / 10;
        }
        //反转同时去掉0
        int[] result = new int[checkLength];
        int index = 0;
        for (int i = checkLength - 1; i >= 0; i--) {
            result[index] = checkSplit[i];
            index++;
        }
        if (isMoreThanZero) {
            if (result.length > maxLength) {
                return 0;
            } else if (result.length == maxLength && compare(result, maxSplit)) {
                return 0;
            } else {
                return buildData(result);
            }
        } else {
            if (result.length > minLength) {
                return 0;
            } else if (result.length == minLength && compare(result, minSplit)) {
                return 0;
            } else {
                return -buildData(result);
            }
        }
    }

    private static int buildData(int[] datas) {
        int result = 0;
        for (int i = 0; i < datas.length; i++) {
            int tem = datas[i];
            int temLength = datas.length - 1 - i;
            for (int j = 0; j < temLength; j++) {
                tem *= 10;
            }
            result += tem;
        }
        return result;
    }

    private static boolean compare(int[] result, int[] splits) {
        for (int i = 0; i < result.length; i++) {
            if (result[i] > splits[i]) {
                return true;
            }
            if (result[i] < splits[i]) {
                return false;
            }
        }
        return false;
    }
}
