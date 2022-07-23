package com.demo.algorithm.leetcode.offer2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2022/7/23
 * @author chenglong
 * description : 剑指OfferII 115.重建序列
 *
 * 给定一个长度为n的整数数组nums，其中nums是范围为[1，n]的整数的排列。还提供了一个2D整数数组sequences，其中sequences[i]是nums的子序列。
 * 检查nums是否是唯一的最短超序列。最短超序列是长度最短的序列，并且所有序列sequences[i]都是它的子序列。对于给定的数组sequences，可能存在多个有效的超序列 。
 * 例如，对于sequences = [[1,2],[1,3]]，有两个最短的超序列，[1,2,3] 和 [1,3,2] 。
 * 而对于sequences = [[1,2],[1,3],[1,2,3]]，唯一可能的最短超序列是[1,2,3]。[1,2,3,4]是可能的超序列，但不是最短的。
 * 如果nums是序列的唯一最短超序列，则返回true，否则返回false 。
 * 子序列是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], sequences = [[1,2],[1,3]]
 * 输出：false
 * 解释：有两种可能的超序列：[1,2,3]和[1,3,2]。
 * 序列 [1,2] 是[1,2,3]和[1,3,2]的子序列。
 * 序列 [1,3] 是[1,2,3]和[1,3,2]的子序列。
 * 因为 nums 不是唯一最短的超序列，所以返回false。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], sequences = [[1,2]]
 * 输出：false
 * 解释：最短可能的超序列为 [1,2]。
 * 序列 [1,2] 是它的子序列：[1,2]。
 * 因为 nums 不是最短的超序列，所以返回false。
 *
 * 示例 3：
 * 输入：nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * 输出：true
 * 解释：最短可能的超序列为[1,2,3]。
 * 序列 [1,2] 是它的一个子序列：[1,2,3]。
 * 序列 [1,3] 是它的一个子序列：[1,2,3]。
 * 序列 [2,3] 是它的一个子序列：[1,2,3]。
 * 因为 nums 是唯一最短的超序列，所以返回true。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^4
 * nums是[1, n]范围内所有整数的排列
 * 1 <= sequences.length <= 10^4
 * 1 <= sequences[i].length <= 10^4
 * 1 <= sum(sequences[i].length) <= 10^5
 * 1 <= sequences[i][j] <= n
 * sequences的所有数组都是唯一的
 * sequences[i]是nums的一个子序列
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        //1，对sequences数据源构建图的结构
        int n = nums.length;
        int[] counts = new int[n + 1];
        List<Set<Integer>> marks = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            marks.add(new HashSet<>());
        }
        int length = sequences.length;
        for (int i = 0; i < length; i++) {
            int[] items = sequences[i];
            for (int j = 1; j < items.length; j++) {
                int pre = items[j - 1];
                int cur = items[j];
                if (marks.get(pre).add(cur)) {
                    counts[cur]++;
                }
            }
        }
        //2，对sequences进行拓扑排序
        List<Integer> dates = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (counts[i] == 0) {
                dates.add(i);
            }
        }
        int index = 0;
        //起点必须是唯一
        if (dates.size() != 1) {
            return false;
        }
        if (dates.get(0) != nums[index]) {
            return false;
        }
        index++;
        List<Integer> next = new ArrayList<>();
        while (index < n) {
            for (int i = 0; i < dates.size(); i++) {
                Set<Integer> items = marks.get(dates.get(i));
                for (int key : items) {
                    counts[key]--;
                    if (counts[key] == 0) {
                        next.add(key);
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
            if (dates.size() != 1) {
                return false;
            }
            if (dates.get(0) != nums[index]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
