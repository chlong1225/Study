package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/5/26
 * @author chenglong
 * description : 掉落的方块
 *
 * 在无限长的数轴（即x轴）上，我们根据给定的顺序放置对应的正方形方块。
 * 第i个掉落的方块（positions[i] = (left, side_length)）是正方形，其中left表示该方块最左边的点位置(positions[i][0])，side_length 表示该方块的边长(positions[i][1])。
 * 每个方块的底部边缘平行于数轴（即x轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * 返回一个堆叠高度列表ans。每一个堆叠高度ans[i]表示在通过positions[0], positions[1], ..., positions[i]表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
 *
 * 示例 1:
 * 输入: [[1, 2], [2, 3], [6, 1]]
 * 输出: [2, 5, 5]
 *
 * 示例 2:
 * 输入: [[100, 100], [200, 100]]
 * 输出: [100, 100]
 * 解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
 *
 * 注意:
 * 1 <= positions.length <= 1000.
 * 1 <= positions[i][0] <= 10^8.
 * 1 <= positions[i][1] <= 10^6.
 */
public class FallingSquares {

    public List<Integer> fallingSquares(int[][] positions) {
        int length = positions.length;
        List<Integer> result = new ArrayList<>(length);
        result.add(positions[0][1]);
        for (int i = 1; i < length; i++) {
            int[] cur = positions[i];
            //依次对比之前的区间，获取最大高度
            int max = cur[1];
            for (int j = 0; j < i; j++) {
                if (compareRange(positions[j], cur)) {
                    int height = cur[1] + result.get(j);
                    if (height > max) {
                        max = height;
                    }
                }
            }
            result.add(max);
        }
        int max = result.get(0);
        for (int i = 1; i < length; i++) {
            if (result.get(i) <= max) {
                result.set(i, max);
            } else {
                max = result.get(i);
            }
        }
        return result;
    }

    private boolean compareRange(int[] pre, int[] cur) {
        int preLeft = pre[0];
        int preRight = pre[0] + pre[1];
        int curLeft = cur[0];
        int curRight = cur[0] + cur[1];
        if (preRight <= curLeft || preLeft >= curRight) {
            return false;
        }
        return true;
    }
}
