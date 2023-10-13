package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2023/10/13
 * @author chenglong
 * description : 避免洪水泛滥
 *
 * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第n个湖泊下雨前是空的，那么它就会装满水。如果第n个湖泊下雨前是满的，这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
 * 给你一个整数数组rains，其中：
 * rains[i] > 0 表示第i天时，第rains[i]个湖泊会下雨。
 * rains[i] == 0 表示第i天没有湖泊会下雨，你可以选择一个湖泊并抽干这个湖泊的水。
 * 请返回一个数组ans，满足：
 * ans.length == rains.length
 * 如果 rains[i] > 0 ，那么ans[i] == -1 。
 * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
 * 如果有多种可行解，请返回它们中的任意一个。如果没办法阻止洪水，请返回一个空的数组 。
 * 请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。
 *
 * 示例 1：
 * 输入：rains = [1,2,3,4]
 * 输出：[-1,-1,-1,-1]
 * 解释：第一天后，装满水的湖泊包括 [1]
 * 第二天后，装满水的湖泊包括 [1,2]
 * 第三天后，装满水的湖泊包括 [1,2,3]
 * 第四天后，装满水的湖泊包括 [1,2,3,4]
 * 没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
 *
 * 示例 2：
 * 输入：rains = [1,2,0,0,2,1]
 * 输出：[-1,-1,2,1,-1,-1]
 * 解释：第一天后，装满水的湖泊包括 [1]
 * 第二天后，装满水的湖泊包括 [1,2]
 * 第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
 * 第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
 * 第五天后，装满水的湖泊包括 [2]。
 * 第六天后，装满水的湖泊包括 [1,2]。
 * 可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
 *
 * 示例 3：
 * 输入：rains = [1,2,0,1,2]
 * 输出：[]
 * 解释：第二天后，装满水的湖泊包括 [1,2]。我们可以在第三天抽干一个湖泊的水。
 * 但第三天后，湖泊 1 和 2 都会再次下雨，所以不管我们第三天抽干哪个湖泊的水，另一个湖泊都会发生洪水。
 *
 * 提示：
 * 1 <= rains.length <= 10^5
 * 0 <= rains[i] <= 10^9
 */
public class AvoidFlood {

    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        //记录抽水结果
        int[] answer = new int[n];
        //记录可以抽水的天数
        List<Integer> dates = new ArrayList<>();
        //记录湖泊装满水的状态
        Map<Integer, Integer> marks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                //当天没有下雨，可以抽干湖泊
                dates.add(i);
                answer[i] = 1;
            } else {
                //当天湖泊rains[i]下雨
                answer[i] = -1;
                if (marks.containsKey(rains[i])) {
                    //之前湖泊已经满了，需要在preDay之后有时间排干
                    int preDay = marks.get(rains[i]);
                    int dayIndex = findIndex(preDay, dates);
                    if (dayIndex == -1) {
                        //找不到可以排水的
                        return new int[]{};
                    }
                    answer[dates.get(dayIndex)] = rains[i];
                    dates.remove(dayIndex);
                }
                marks.put(rains[i], i);
            }
        }
        return answer;
    }

    private int findIndex(int target, List<Integer> dates) {
        if (dates.size() == 0) {
            return -1;
        }
        if (dates.size() == 1) {
            if (dates.get(0) > target) {
                return 0;
            }
            return -1;
        }
        int start = 0;
        int end = dates.size() - 1;
        if (dates.get(start) > target) {
            return start;
        }
        if (dates.get(end) < target) {
            return -1;
        }
        //此时target在start与end之间
        while (start < end) {
            int middle = (start + end) / 2;
            if (dates.get(middle) < target) {
                start = middle + 1;
            } else {
                end = middle;
                if (dates.get(middle - 1) < target) {
                    return middle;
                }
            }
        }
        return start;
    }
}
