package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * create on 2023/5/15
 * @author chenglong
 * description : 距离相等的条形码
 *
 * 在一个仓库里，有一排条形码，其中第i个条形码为barcodes[i]。
 * 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 *
 * 示例 1：
 * 输入：barcodes = [1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 *
 * 示例 2：
 * 输入：barcodes = [1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 *
 * 提示：
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 */
public class RearrangeBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        if (n <= 2) {
            return barcodes;
        }
        //1，排序
        Arrays.sort(barcodes);
        //2，统计数量并排序
        List<int[]> dates = new ArrayList<>();
        int pre = barcodes[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (barcodes[i] == pre) {
                count++;
            } else {
                dates.add(new int[]{count, pre});
                pre = barcodes[i];
                count = 1;
            }
        }
        dates.add(new int[]{count, pre});
        Collections.sort(dates, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        //2，按照奇偶数位依次分配
        int[] result = new int[n];
        int index = 0;
        for (int i = 0; i < n; i += 2) {
            int[] tem = dates.get(index);
            result[i] = tem[1];
            tem[0]--;
            if (tem[0] == 0) {
                index++;
            }
        }
        for (int i = 1; i < n; i+=2) {
            int[] tem = dates.get(index);
            result[i] = tem[1];
            tem[0]--;
            if (tem[0] == 0) {
                index++;
            }
        }
        return result;
    }
}
