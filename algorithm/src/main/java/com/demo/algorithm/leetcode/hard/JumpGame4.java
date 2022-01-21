package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chl on 2022/1/21.
 * description : 跳跃游戏IV
 *
 * 给你一个整数数组arr，你一开始在数组的第一个元素处（下标为0）。
 * 每一步，你可以从下标i跳到下标：
 * i + 1满足：i + 1 < arr.length
 * i - 1满足：i - 1 >= 0
 * j满足：arr[i] == arr[j]且i != j
 * 请你返回到达数组最后一个元素的下标处所需的最少操作次数。
 *
 * 注意：任何时候你都不能跳到数组外面。
 *
 * 示例 1：
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 *
 * 示例 2：
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 *
 * 示例 3：
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 *
 * 示例 4：
 * 输入：arr = [6,1,9]
 * 输出：2
 *
 * 示例 5：
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 *
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 */
public class JumpGame4 {

    public int minJumps(int[] arr) {
        /**
         * 对应查询最小次数的问题,使用广度优先搜索bfs，同时记忆状态
         */
        int length = arr.length;
        if (length == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> datas = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (datas.get(arr[i]) == null) {
                List<Integer> tem = new ArrayList<>();
                tem.add(i);
                datas.put(arr[i], tem);
            } else {
                datas.get(arr[i]).add(i);
            }
        }
        //最后一个位置的值
        int compare = arr[length - 1];
        //记录已经访问过的index
        Set<Integer> marks = new HashSet<>();
        marks.add(0);
        int step = 0;
        List<Integer> positions = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        positions.add(0);
        while (!positions.isEmpty()) {
            step++;
            int size = positions.size();
            for (int i = 0; i < size; i++) {
                int position = positions.get(i);
                //1，前进一位
                if (position + 1 == length - 1) {
                    //移动到最后一位时,结束
                    return step;
                }
                if (!marks.contains(position + 1)) {
                    next.add(position + 1);
                    marks.add(position + 1);
                }
                //2，后退一位
                if (position - 1 > 0) {
                    if (!marks.contains(position - 1)) {
                        next.add(position - 1);
                        marks.add(position - 1);
                    }
                }
                //3，跳跃
                if (arr[position] == compare) {
                    return step;
                }
                List<Integer> tem = datas.get(arr[position]);
                if (tem != null) {
                    for (int j = 0; j < tem.size(); j++) {
                        if (tem.get(j) == position) {
                            continue;
                        }
                        if (marks.contains(tem.get(j))) {
                            continue;
                        }
                        marks.add(tem.get(j));
                        next.add(tem.get(j));
                    }
                    datas.remove(arr[position]);
                }
            }
            positions.clear();
            positions.addAll(next);
            next.clear();
        }
        return -1;
    }
}
