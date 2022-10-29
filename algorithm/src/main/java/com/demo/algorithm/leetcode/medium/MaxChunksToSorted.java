package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/10/13
 * @author chenglong
 * description : 最多能完成排序的块
 *
 * 给定一个长度为n的整数数组arr，它表示在[0, n-1]范围内的整数的排列。
 * 我们将arr分割成若干块(即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 * 返回数组能分成的最多块数量。
 *
 * 示例 1:
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 *
 * 示例 2:
 * 输入: arr = [1,0,2,3,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 *
 * 提示:
 * n == arr.length
 * 1 <= n <= 10
 * 0 <= arr[i] < n
 * arr中每个元素都不同
 */
public class MaxChunksToSorted {

    public int maxChunksToSorted(int[] arr) {
        List<Integer> dates = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        int length = arr.length;
        if (length < 2) {
            return length;
        }
        for (int i = 0; i < length; i++) {
            int cur = arr[i];
            if (dates.isEmpty()) {
                dates.add(cur);
            } else {
                int last = dates.get(dates.size() - 1);
                if (last < cur) {
                    dates.add(cur);
                } else {
                    //从头遍历删除
                    if (dates.size() > 1) {
                        for (int j = 0; j < dates.size() - 1; j++) {
                            if (dates.get(j) < cur) {
                                next.add(dates.get(j));
                            }
                        }
                        next.add(last);
                        dates.clear();
                        dates.addAll(next);
                        next.clear();
                    }

                }
            }
        }
        return dates.size();
    }
}
