package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/19.
 * description : 分割回文串
 *
 * 给你一个字符串s，请你将s分割成一些子串，使每个子串都是回文串 。返回s所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 *  
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class SplitPalindrome {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(0, s, path, result);
        return result;
    }

    private void dfs(int index, String s, List<String> path, List<List<String>> result) {
        int length = s.length();
        if (index == length) {
            if (path.size() > 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = 1; i <= length - index; i++) {
            //选取的字母数量
            String tem = s.substring(index, index + i);
            if (isPartition(tem)) {
                path.add(tem);
                dfs(index + i, s, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    //判断当前字符串是否为回文串
    private boolean isPartition(String s) {
        int length = s.length();
        if (length == 1) {
            return true;
        }
        int count = length >> 1;
        for (int i = 0; i < count; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
