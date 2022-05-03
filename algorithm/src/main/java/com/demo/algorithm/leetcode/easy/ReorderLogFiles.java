package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/5/3.
 * description : 重新排列日志文件
 *
 * 给你一个日志数组logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的标识符 。
 * 有两种不同类型的日志：
 * 字母日志：除标识符之外，所有字均由小写字母组成
 * 数字日志：除标识符之外，所有字均由数字组成
 * 请按下述规则将日志重新排序：
 * 所有字母日志都排在数字日志之前。
 * 字母日志在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
 * 数字日志应该保留原来的相对顺序。
 * 返回日志的最终顺序。
 *
 * 示例 1：
 * 输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * 输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * 解释：
 * 字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
 * 数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
 *
 * 示例 2：
 * 输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 * 提示：
 * 1 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] 中，字与字之间都用 单个 空格分隔
 * 题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
 */
public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        List<String> nums = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Map<String, List<String>> marks = new HashMap<>();
        int length = logs.length;
        for (int i = 0; i < length; i++) {
            String tem = logs[i];
            int size = tem.length();
            int index = 0;
            for (int j = 0; j < size; j++) {
                if (tem.charAt(j) == ' ') {
                    index = j;
                    break;
                }
            }
            //标识符为：0~index之间，判读日志类型
            boolean isNum = true;
            for (int j = index + 1; j < size; j++) {
                if (tem.charAt(j) == ' ') {
                    continue;
                }
                if (tem.charAt(j) > '9' || tem.charAt(j) < '0') {
                    isNum = false;
                    break;
                }
            }
            if (isNum) {
                nums.add(tem);
            } else {
                String label = tem.substring(0, index);
                String log = tem.substring(index + 1, size);
                if (marks.containsKey(log)) {
                    marks.get(log).add(label);
                } else {
                    List<String> items = new ArrayList<>();
                    items.add(label);
                    marks.put(log, items);
                    dates.add(log);
                }
            }
        }
        String[] result = new String[length];
        //先添加字母日志
        int index = 0;
        if (dates.size() > 0) {
            //先按照日志内容排序
            Collections.sort(dates);
            for (int i = 0; i < dates.size(); i++) {
                List<String> labes = marks.get(dates.get(i));
                if (labes.size() > 1) {
                    Collections.sort(labes);
                }
                for (int j = 0; j < labes.size(); j++) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(labes.get(j));
                    builder.append(" ");
                    builder.append(dates.get(i));
                    result[index] = builder.toString();
                    index++;
                }
            }
        }
        //添加数字日志
        if (nums.size() > 0) {
            for (int i = 0; i < nums.size(); i++) {
                result[index] = nums.get(i);
                index++;
            }
        }
        return result;
    }
}
