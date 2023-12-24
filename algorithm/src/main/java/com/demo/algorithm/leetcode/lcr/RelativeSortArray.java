package com.demo.algorithm.leetcode.lcr;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : LCR 075. 数组的相对排序
 *
 * 给定两个数组，arr1和arr2，
 * arr2中的元素各不相同
 * arr2中的每个元素都出现在arr1中
 * 对arr1中的元素进行排序，使arr1中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
 *
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 提示：
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //1，统计arr1中数字出现的次数
        int max = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] > max) {
                max = arr1[i];
            }
        }
        int[] counts = new int[max + 1];
        for (int i = 0; i < arr1.length; i++) {
            counts[arr1[i]]++;
        }
        //2，排序arr2中出现的数字
        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            while (counts[arr2[i]] > 0) {
                counts[arr2[i]]--;
                arr1[index] = arr2[i];
                index++;
            }
        }
        //3，排序arr2中无出现的数字
        for (int i = 0; i <= max; i++) {
            while (counts[i] > 0) {
                arr1[index] = i;
                counts[i]--;
                index++;
            }
        }
        return arr1;
    }
}
