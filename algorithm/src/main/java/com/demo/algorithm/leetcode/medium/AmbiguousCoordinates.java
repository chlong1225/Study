package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/11/7
 * @author chenglong
 * description : 模糊坐标
 *
 * 我们有一些二维坐标，如"(1, 3)"或"(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。
 * 此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 *
 * 示例 2:
 * 输入: "(00011)"
 * 输出: ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 *
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 *
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 *
 * 提示:
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串S中的其他元素都是数字。
 */
public class AmbiguousCoordinates {

    public List<String> ambiguousCoordinates(String s) {
        List<String> result = new ArrayList<>();
        int length = s.length();
        //有效数字区间的范围
        int start = 1;
        int end = length - 1;
        for (int i = start + 1; i < end; i++) {
            List<String> lefts = getNums(s.substring(start, i));
            List<String> rights = getNums(s.substring(i, end));
            for (int j = 0; j < lefts.size(); j++) {
                for (int k = 0; k < rights.size(); k++) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("(");
                    builder.append(lefts.get(j));
                    builder.append(", ");
                    builder.append(rights.get(k));
                    builder.append(")");
                    result.add(builder.toString());
                }
            }
        }
        return result;
    }

    private List<String> getNums(String nums) {
        List<String> result = new ArrayList<>();
        int length = nums.length();
        if (length == 1) {
            result.add(nums);
        } else {
            //插入小数点
            for (int i = 0; i < length - 1; i++) {
                String pre = nums.substring(0, i + 1);
                String last = nums.substring(i + 1);
                //拼接数据为：pre.last。判断当前小数是否有效
                if (pre.charAt(0) == '0' && pre.length() > 1) {
                    continue;
                }
                if (last.charAt(last.length() - 1) == '0') {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                builder.append(pre);
                builder.append('.');
                builder.append(last);
                result.add(builder.toString());
            }
            //插入整数
            if (nums.charAt(0) != '0') {
                result.add(nums);
            }
        }
        return result;
    }
}
