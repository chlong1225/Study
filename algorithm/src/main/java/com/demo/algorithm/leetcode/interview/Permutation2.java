package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by chl on 2022/5/21.
 * description : 面试题08.08. 有重复字符串的排列组合
 *
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 * 示例1:
 *  输入：S = "qqe"
 *  输出：["eqq","qeq","qqe"]
 *
 * 示例2:
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 *
 * 提示:
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class Permutation2 {

    public String[] permutation(String S) {
        Set<String> ans = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, S, path, ans);
        String[] result = new String[ans.size()];
        Iterator<String> iterator = ans.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            result[index++] = iterator.next();
        }
        return result;
    }

    private void dfs(int index, String str, List<Integer> path, Set<String> ans) {
        int length = str.length();
        if (index == length) {
            //构建字符串
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(str.charAt(path.get(i)));
            }
            ans.add(builder.toString());
            return;
        }
        for (int i = 0; i < length; i++) {
            if (path.size() > 0) {
                if (path.contains(i)) {
                    continue;
                }
                path.add(i);
                dfs(index + 1, str, path, ans);
                path.remove(path.size() - 1);
            } else {
                path.add(i);
                dfs(index + 1, str, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }
}
