package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/3/20.
 * description : 两个列表的最小索引总和
 *
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。如果答案不止一个，则输出所有答案并且不考虑顺序。你可以假设答案总是存在。
 *
 * 示例 1:
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 *
 * 示例 2:
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 *
 * 提示:
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] 和 list2[i] 由空格' '和英文字母组成。
 * list1的所有字符串都是唯一的。
 * list2中的所有字符串都是唯一的。
 */
public class MinIndexSum {

    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> marks = new HashMap<>();
        int length = list1.length;
        for (int i = 0; i < length; i++) {
            marks.put(list1[i], i);
        }
        int minIndex = Integer.MAX_VALUE;
        length = list2.length;
        for (int i = 0; i < length; i++) {
            if (marks.containsKey(list2[i])) {
                //计算索引和
                int sum = marks.get(list2[i]) + i;
                if (sum < minIndex) {
                    minIndex = sum;
                    result.clear();
                    result.add(list2[i]);
                } else if (sum == minIndex) {
                    result.add(list2[i]);
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}
