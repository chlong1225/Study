package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/12
 * @author chenglong
 * description : 用户分组
 *
 * 有n个人被分成数量未知的组。每个人都被标记为一个从0到n-1的唯一ID。
 *
 * 给定一个整数数组groupSizes，其中groupSizes[i]是第i个人所在的组的大小。例如，如果groupSizes[1]=3，则第1个人必须位于大小为3的组中。
 * 返回一个组列表，使每个人i都在一个大小为groupSizes[i]的组中。
 * 每个人应该恰好只出现在一个组中，并且每个人必须在一个组中。如果有多个答案，返回其中任何一个。可以保证给定输入至少有一个有效的解。
 *
 * 示例 1：
 * 输入：groupSizes = [3,3,3,3,3,1,3]
 * 输出：[[5],[0,1,2],[3,4,6]]
 * 解释：
 * 第一组是 [5]，大小为 1，groupSizes[5] = 1。
 * 第二组是 [0,1,2]，大小为 3，groupSizes[0] = groupSizes[1] = groupSizes[2] = 3。
 * 第三组是 [3,4,6]，大小为 3，groupSizes[3] = groupSizes[4] = groupSizes[6] = 3。
 * 其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
 *
 * 示例 2：
 * 输入：groupSizes = [2,1,3,3,3,2]
 * 输出：[[1],[0,5],[2,3,4]]
 *
 * 提示：
 * groupSizes.length == n
 * 1 <= n<= 500
 * 1 <=groupSizes[i] <= n
 */
public class GroupThePeople {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n = groupSizes.length;
        List<List<Integer>> dates = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            dates.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            dates.get(groupSizes[i]).add(i);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            List<Integer> items = dates.get(i);
            if (items.size() > 0) {
                int count = items.size() / i;
                for (int j = 0; j < count; j++) {
                    List<Integer> tem = new ArrayList<>();
                    for (int k = 0; k < i; k++) {
                        tem.add(items.get(k + j * i));
                    }
                    result.add(tem);
                }
            }
        }
        return result;
    }
}
