package com.demo.algorithm.leetcode.contest.doulbeweek84;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2022/8/12
 * @author chenglong
 * description : 合并相似的物品
 *
 * 给你两个二维整数数组items1和items2，表示两个物品集合。每个数组items有以下特质：
 * items[i] = [valuei, weighti] 其中valuei表示第i件物品的价值，weighti表示第i件物品的重量。
 * items中每件物品的价值都是唯一的。
 * 请你返回一个二维数组ret，其中ret[i] = [valuei, weighti]，weighti是所有价值为valuei物品的重量之和。
 * 注意：ret应该按价值升序排序后返回。
 *
 * 示例 1：
 * 输入：items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
 * 输出：[[1,6],[3,9],[4,5]]
 * 解释：
 * value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 5 ，总重量为 1 + 5 = 6 。
 * value = 3 的物品再 items1 中 weight = 8 ，在 items2 中 weight = 1 ，总重量为 8 + 1 = 9 。
 * value = 4 的物品在 items1 中 weight = 5 ，总重量为 5 。
 * 所以，我们返回 [[1,6],[3,9],[4,5]] 。
 *
 * 示例 2：
 * 输入：items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
 * 输出：[[1,4],[2,4],[3,4]]
 * 解释：
 * value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 3 ，总重量为 1 + 3 = 4 。
 * value = 2 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 1 ，总重量为 3 + 1 = 4 。
 * value = 3 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
 * 所以，我们返回 [[1,4],[2,4],[3,4]] 。
 *
 * 示例 3：
 * 输入：items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
 * 输出：[[1,7],[2,4],[7,1]]
 * 解释：
 * value = 1 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 4 ，总重量为 3 + 4 = 7 。
 * value = 2 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
 * value = 7 的物品在 items2 中 weight = 1 ，总重量为 1 。
 * 所以，我们返回 [[1,7],[2,4],[7,1]] 。
 *
 * 提示：
 * 1 <= items1.length, items2.length <= 1000
 * items1[i].length == items2[i].length == 2
 * 1 <= valuei, weighti <= 1000
 * items1中每个valuei都是唯一的。
 * items2中每个valuei都是唯一的。
 */
public class MergeSimilarItems {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Arrays.sort(items1, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(items2, (o1, o2) -> o1[0] - o2[0]);
        List<List<Integer>> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        int length1 = items1.length;
        int length2 = items2.length;
        while (index1 < length1 && index2 < length2) {
            if (items1[index1][0] == items2[index2][0]) {
                List<Integer> item = new ArrayList<>();
                item.add(items1[index1][0]);
                item.add(items1[index1][1] + items2[index2][1]);
                result.add(item);
                index1++;
                index2++;
            } else if (items1[index1][0] < items2[index2][0]) {
                List<Integer> item = new ArrayList<>();
                item.add(items1[index1][0]);
                item.add(items1[index1][1]);
                result.add(item);
                index1++;
            } else {
                List<Integer> item = new ArrayList<>();
                item.add(items2[index2][0]);
                item.add(items2[index2][1]);
                result.add(item);
                index2++;
            }
        }
        if (index1 == length1) {
            for (int i = index2; i < length2; i++) {
                List<Integer> item = new ArrayList<>();
                item.add(items2[i][0]);
                item.add(items2[i][1]);
                result.add(item);
            }
        } else {
            for (int i = index1; i < length1; i++) {
                List<Integer> item = new ArrayList<>();
                item.add(items1[i][0]);
                item.add(items1[i][1]);
                result.add(item);
            }
        }
        return result;
    }
}
