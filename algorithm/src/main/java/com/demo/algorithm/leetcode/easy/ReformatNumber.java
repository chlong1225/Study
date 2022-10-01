package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/10/1
 * @author chenglong
 * description : 重新格式化电话号码
 *
 * 给你一个字符串形式的电话号码number。number由数字、空格' '、和破折号'-'组成。
 * 请你按下述方式重新格式化电话号码。
 * 首先，删除所有的空格和破折号。
 * 其次，将数组从左到右每3个一组分块，直到剩下4个或更少数字。剩下的数字将按下述规定再分块：
 * 2 个数字：单个含2个数字的块。
 * 3 个数字：单个含3个数字的块。
 * 4 个数字：两个分别含2个数字的块。
 * 最后用破折号将这些块连接起来。注意，重新格式化过程中不应该生成仅含1个数字的块，并且最多生成两个含2个数字的块。
 * 返回格式化后的电话号码。
 *
 * 示例 1：
 * 输入：number = "1-23-45 6"
 * 输出："123-456"
 * 解释：数字是 "123456"
 * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
 * 步骤 2：剩下 3 个数字，将它们放入单个含 3 个数字的块。第 2 个块是 "456" 。
 * 连接这些块后得到 "123-456"。
 *
 * 示例 2：
 * 输入：number = "123 4-567"
 * 输出："123-45-67"
 * 解释：数字是 "1234567".
 * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
 * 步骤 2：剩下 4 个数字，所以将它们分成两个含 2 个数字的块。这 2 块分别是 "45" 和 "67" 。
 * 连接这些块后得到 "123-45-67"。
 *
 * 示例 3：
 * 输入：number = "123 4-5678"
 * 输出："123-456-78"
 * 解释：数字是 "12345678" 。
 * 步骤 1：第 1 个块 "123" 。
 * 步骤 2：第 2 个块 "456" 。
 * 步骤 3：剩下 2 个数字，将它们放入单个含 2 个数字的块。第 3 个块是 "78" 。
 * 连接这些块后得到 "123-456-78"。
 *
 * 示例 4：
 * 输入：number = "12"
 * 输出："12"
 *
 * 示例 5：
 * 输入：number = "--17-5 229 35-39475 "
 * 输出："175-229-353-94-75"
 *
 * 提示：
 * 2 <= number.length <= 100
 * number由数字和字符'-'及' '组成。
 * number中至少含2个数字。
 */
public class ReformatNumber {

    public String reformatNumber(String number) {
        char[] chars = number.toCharArray();
        int length = chars.length;
        List<Character> dates = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                dates.add(chars[i]);
            }
        }
        int three = 0;
        int two = 0;
        if (dates.size() % 3 == 0) {
            three = dates.size() / 3;
        } else if (dates.size() % 3 == 1) {
            two = 2;
            three = (dates.size() - 4) / 3;
        } else {
            two = 1;
            three = dates.size() / 3;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < three; i++) {
            builder.append(dates.get(3 * i));
            builder.append(dates.get(3 * i + 1));
            builder.append(dates.get(3 * i + 2));
            builder.append('-');
        }
        for (int i = 0; i < two; i++) {
            builder.append(dates.get(3 * three + 2 * i));
            builder.append(dates.get(3 * three + 2 * i + 1));
            builder.append('-');
        }
        return builder.deleteCharAt(builder.length()-1).toString();
    }
}
