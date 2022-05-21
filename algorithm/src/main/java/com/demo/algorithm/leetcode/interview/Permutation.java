package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题08.07. 无重复字符串的排列组合
 *
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 *
 * 示例1:
 *  输入：S = "qwe"
 *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 *
 * 示例2:
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 *
 * 提示:
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class Permutation {

    private int index;

    public String[] permutation(String S) {
        int length = S.length();
        int count = 1;
        for (int i = 2; i <= length; i++) {
            count *= i;
        }
        String[] result = new String[count];
        List<Integer> path = new ArrayList<>();
        dfs(0, S, path, result);
        return result;
    }

    private void dfs(int step, String str, List<Integer> path, String[] result) {
        int length = str.length();
        if (step == length) {
            //构建字符串
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(str.charAt(path.get(i)));
            }
            result[index] = builder.toString();
            index++;
            return;
        }
        for (int i = 0; i < length; i++) {
            if (path.size() > 0) {
                if (path.contains(i)) {
                    continue;
                }
                path.add(i);
                dfs(step + 1, str, path, result);
                path.remove(path.size() - 1);
            } else {
                path.add(i);
                dfs(step + 1, str, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
