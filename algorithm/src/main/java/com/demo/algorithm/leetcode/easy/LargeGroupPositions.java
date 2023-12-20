package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create by chenglong on 2023/12/11
 * description : 较大分组的位置
 *
 * 在一个由小写字母构成的字符串s中，包含由一些连续的相同字符所构成的分组。
 * 例如，在字符串s="abbxxxxzyy" 中，就含有"a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * 分组可以用区间[start, end]表示，其中start和end分别表示该分组的起始和终止位置的下标。上例中的"xxxx"分组用区间表示为[3,6]。
 * 我们称所有包含大于或等于三个连续字符的分组为较大分组。
 * 找到每一个较大分组的区间，按起始位置下标递增顺序排序后，返回结果。
 *
 * 示例 1：
 * 输入：s = "abbxxxxzzy"
 * 输出：[[3,6]]
 * 解释："xxxx" 是一个起始于3且终止于6的较大分组。
 *
 * 示例 2：
 * 输入：s = "abc"
 * 输出：[]
 * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
 *
 * 示例 3：
 * 输入：s = "abcdddeeeeaabbbcd"
 * 输出：[[3,5],[6,9],[12,14]]
 * 解释：较大分组为 "ddd", "eeee" 和 "bbb"
 *
 * 示例 4：
 * 输入：s = "aba"
 * 输出：[]
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅含小写英文字母
 */
public class LargeGroupPositions {

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> answer = new ArrayList<>();
        int count = 1;
        char pre = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                count++;
            } else {
                if (count >= 3) {
                    List<Integer> items = new ArrayList<>();
                    int end = i - 1;
                    int start = i - count;
                    items.add(start);
                    items.add(end);
                    answer.add(items);
                }
                count = 1;
            }
            pre = s.charAt(i);
        }
        if (count >= 3) {
            List<Integer> items = new ArrayList<>();
            int end = s.length() - 1;
            int start = s.length() - count;
            items.add(start);
            items.add(end);
            answer.add(items);
        }
        return answer;
    }
}
