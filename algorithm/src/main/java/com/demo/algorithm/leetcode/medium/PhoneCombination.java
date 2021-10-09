package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/3.
 * description : 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class PhoneCombination {

    private static String[][] datas = {{"a","b","c"},{"d","e","f"},{"g","h","i"},{"j","k","l"},
            {"m","n","o"},{"p","q","r","s"},{"t","u","v"},{"w","x","y","z"}};

    public static List<String> letterCombinations(String digits) {
        List<String> result;
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>(0);
        }

        char[] chars = digits.toCharArray();
        int length = chars.length;
        //1,确定List的大小,防止后续扩容
        int size = 1;
        int num;
        for (int i = 0; i < length; i++) {
            num = chars[i] - '0';
            if (num == 7 || num == 9) {
                size *= 4;
            } else {
                size *= 3;
            }
        }
        result = new ArrayList<>(size);
        //2,填充内容
        String[] data;
        int dataLength;
        for (int i = 0; i < length; i++) {
            num = chars[i] - '0';
            data = datas[num - 2];
            dataLength = data.length;
            if (i == 0) {
                for (int j = 0; j < dataLength; j++) {
                    result.add(data[j]);
                }
            } else {
                int preSize = result.size();
                for (int j = 0; j < preSize; j++) {
                    for (int k = 1; k < dataLength; k++) {
                        result.add(result.get(j) + data[k]);
                    }
                    result.set(j, result.get(j) + data[0]);
                }
            }
        }
        return result;
    }
}
