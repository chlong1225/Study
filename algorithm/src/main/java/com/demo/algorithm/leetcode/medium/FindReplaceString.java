package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/8/15
 * @author chenglong
 * description : 字符串中的查找与替换
 *
 * 你会得到一个字符串s(索引从0开始)，你必须对它执行k个替换操作。替换操作以三个长度均为k的并行数组给出：indices,sources,targets。
 * 要完成第i个替换操作:
 * 检查子字符串sources[i]是否出现在原字符串s的索引 indices[i]处。
 * 如果没有出现，什么也不做。
 * 如果出现，则用targets[i]替换该子字符串。
 * 例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。
 *
 * 所有替换操作必须同时发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。
 * 例如，一个s="abc"，indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab" 和 "bc" 替换重叠。
 * 在对s执行所有替换操作后返回结果字符串。
 * 子字符串是字符串中连续的字符序列。
 *
 * 示例 1：
 * 输入：s = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * 输出："eeebffff"
 * 解释：
 * "a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
 * "cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
 *
 * 示例 2：
 * 输入：s = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * 输出："eeecd"
 * 解释：
 * "ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
 * "ec" 没有从原始的 S 中的索引 2 开始，所以它没有被替换。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * k == indices.length == sources.length == targets.length
 * 1 <= k <= 100
 * 0 <= indexes[i] < s.length
 * 1 <= sources[i].length, targets[i].length <= 50
 * s 仅由小写英文字母组成
 * sources[i] 和 targets[i] 仅由小写英文字母组成
 */
public class FindReplaceString {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int[][] dates = new int[indices.length][2];
        for (int i = 0; i < indices.length; i++) {
            dates[i][0] = indices[i];
            dates[i][1] = i;
        }
        Arrays.sort(dates, (o1, o2) -> o1[0] - o2[0]);
        StringBuilder builder = new StringBuilder();
        int start = 0;
        int n = s.length();
        int index = 0;
        while (start < n) {
            if (index < indices.length && start == dates[index][0]) {
                int sourceIndex = dates[index][1];
                //当前索引处检查并替换
                int end = start + sources[sourceIndex].length();
                if (end > n) {
                    //防止索引越界
                    builder.append(s.substring(start));
                    break;
                }
                if (sources[sourceIndex].equals(s.substring(start, end))) {
                    //此时可以替换
                    builder.append(targets[sourceIndex]);
                    start = end;
                } else {
                    builder.append(s.charAt(start));
                    start++;
                }
                index++;
            } else {
                builder.append(s.charAt(start));
                start++;
            }
        }
        return builder.toString();
    }
}
