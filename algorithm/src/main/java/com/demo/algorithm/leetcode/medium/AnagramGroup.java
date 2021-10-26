package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/25.
 * description : 字母异位词分组
 *
 * 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *  
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class AnagramGroup {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        int length = strs.length;
        if (length == 1) {
            List<String> items = new ArrayList<>();
            items.add(strs[0]);
            result.add(items);
            return result;
        }
        List<int[]> compares = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int[] data = strToInt(strs[i]);
            int index = findData(data, compares);
            if (index == -1) {
                compares.add(data);
                List<String> items = new ArrayList<>();
                items.add(strs[i]);
                result.add(items);
            } else {
                List<String> tem = result.get(index);
                tem.add(strs[i]);
            }
        }
        return result;
    }

    //在compare中查找data的位置
    private int findData(int[] data, List<int[]> compares) {
        int size = compares.size();
        if (size == 0) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            int[] compare = compares.get(i);
            if (compare(data, compare)) {
                return i;
            }
        }
        return -1;
    }

    //比较两个数组是否相同，即对于的字符串是否为字母异位词
    private boolean compare(int[] data, int[] compare) {
        //1,先比较最后一位长度
        if (data[26] != compare[26]) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (data[i] == compare[i]) {
                count += data[i];
                if (count == data[26]) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    //将字符串转换成统计数组，便于比较
    private int[] strToInt(String str) {
        //0~25的位置存放a——z的个数，最后一位存放长度
        int[] data = new int[27];
        int length = str.length();
        data[26] = length;
        for (int i = 0; i < length; i++) {
            int index = str.charAt(i) - 'a';
            data[index]++;
        }
        return data;
    }
}
