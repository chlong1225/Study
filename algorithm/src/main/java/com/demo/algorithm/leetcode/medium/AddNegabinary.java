package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2023/6/7.
 * description : 负二进制数相加
 *
 * 给出基数为-2的两个数arr1和arr2，返回两数相加的结果。
 * 数字以数组形式给出：数组由若干0和1组成，按最高有效位到最低有效位的顺序排列。
 * 例如，arr= [1,1,0,1]表示数字(-2)^3+ (-2)^2 + (-2)^0 = -3。数组形式中的数字arr也同样不含前导零：即arr==[0]或arr[0] == 1。
 * 返回相同表示形式的arr1和arr2相加的结果。两数的表示形式为：不含前导零、由若干0和1组成的数组。
 *
 * 示例 1：
 * 输入：arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * 输出：[1,0,0,0,0]
 * 解释：arr1 表示 11，arr2 表示 5，输出表示 16 。
 *
 * 示例 2：
 * 输入：arr1 = [0], arr2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：arr1 = [0], arr2 = [1]
 * 输出：[1]
 *
 * 提示：
 * 1 <= arr1.length,arr2.length <= 1000
 * arr1[i]和arr2[i]都是0或1
 * arr1和arr2都没有前导0
 */
public class AddNegabinary {

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        if (n > m) {
            return addNegabinary(arr2, arr1);
        }
        int add = 0;
        List<Integer> dates = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            int index = i + m - n;
            int sum = arr1[i] + arr2[index] + add;
            if (sum == 2) {
                add = -1;
                dates.add(0);
            } else if (sum == -1) {
                dates.add(1);
                add = 1;
            } else if (sum == 3) {
                dates.add(1);
                add = -1;
            } else {
                add = 0;
                dates.add(sum);
            }
        }
        for (int i = m - n - 1; i >= 0; i--) {
            int sum = arr2[i] + add;
            if (sum == 2) {
                add = -1;
                dates.add(0);
            } else if (sum == -1) {
                dates.add(1);
                add = 1;
            } else if (sum == 3) {
                dates.add(1);
                add = -1;
            } else {
                add = 0;
                dates.add(sum);
            }
        }
        if (add == 1) {
            dates.add(1);
        } else if (add == -1) {
            dates.add(1);
            dates.add(1);
        }
        while (dates.size() >= 2 && dates.get(dates.size() - 1) == 0) {
            dates.remove(dates.size() - 1);
        }
        int size = dates.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = dates.get(size - 1 - i);
        }
        return result;
    }
}
