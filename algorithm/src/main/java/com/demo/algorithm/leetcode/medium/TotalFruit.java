package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/10/17
 * @author chenglong
 * description : 水果成篮
 *
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组fruits表示，其中fruits[i]是第i棵树上的水果种类。
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 * 你只有两个篮子，并且每个篮子只能装单一类型的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从每棵树（包括开始采摘的树）上恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组fruits，返回你可以收集的水果的最大数目。
 *
 * 示例 1：
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部3棵树。
 *
 * 示例 2：
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 *
 * 示例 3：
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 *
 * 示例 4：
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 *
 * 提示：
 * 1 <= fruits.length <= 10^5
 * 0 <= fruits[i] < fruits.length
 */
public class TotalFruit {

    public int totalFruit(int[] fruits) {
        int length = fruits.length;
        if (length <= 2) {
            return length;
        }
        Map<Integer, Integer> marks = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        int first = fruits[0];
        marks.put(first, 0);
        int second = -1;
        int max = 0;
        for (int i = 1; i < length; i++) {
            int cur = fruits[i];
            marks.put(cur, i);
            if (cur == first) {
                endIndex = i;
            } else {
                if (second == -1) {
                    second = cur;
                    endIndex = i;
                } else if (second == cur) {
                    endIndex = i;
                } else {
                    //此时cur是额外出现的类型，计算数量
                    int count = endIndex - startIndex + 1;
                    if (count > max) {
                        max = count;
                    }
                    if (marks.get(first) > marks.get(second)) {
                        startIndex = marks.get(second) + 1;
                    } else {
                        startIndex = marks.get(first) + 1;
                        first = second;
                    }
                    second = cur;
                    endIndex = i;
                }
            }
        }
        int count = endIndex - startIndex + 1;
        if (count > max) {
            max = count;
        }
        return max;
    }
}
