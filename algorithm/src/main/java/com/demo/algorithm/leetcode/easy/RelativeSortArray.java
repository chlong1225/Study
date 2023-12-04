package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 数组的相对排序
 *
 * 给你两个数组，arr1和arr2，arr2中的元素各不相同，arr2中的每个元素都出现在arr1中。
 * 对arr1中的元素进行排序，使arr1中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
 *
 * 示例 1：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 示例  2:
 * 输入：arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * 输出：[22,28,8,6,17,44]
 *
 * 提示：
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素arr2[i]各不相同
 * arr2 中的每个元素arr2[i]都出现在arr1中
 */
public class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n = arr2.length;
        //记录arr2中值与位置index的对应关系，便于后续快速查找
        int[] indexs = new int[1001];
        for (int i = 0; i < 1001; i++) {
            indexs[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            indexs[arr2[i]] = i;
        }
        //统计arr1中出现对应arr2中数字的次数
        int[] counts = new int[n];
        //存放未在arr2中出现的数字
        List<Integer> last = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            int cur = arr1[i];
            if (indexs[cur] == -1) {
                last.add(cur);
            } else {
                counts[indexs[cur]]++;
            }
        }
        Collections.sort(last);
        int[] result = new int[arr1.length];
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    result[index] = arr2[i];
                    index++;
                }
            }
        }
        for (int i = 0; i < last.size(); i++) {
            result[index] = last.get(i);
            index++;
        }
        return result;
    }
}
