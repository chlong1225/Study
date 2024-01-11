package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/1/11
 * @author chenglong
 * description : 构造有效字符串的最少插入数
 *
 * 给你一个字符串word，你可以向其中任何位置插入"a"、"b"或"c"任意次，返回使word有效需要插入的最少字母数。
 * 如果字符串可以由"abc"串联多次得到，则认为该字符串有效。
 *
 * 示例 1：
 * 输入：word = "b"
 * 输出：2
 * 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
 *
 * 示例 2：
 * 输入：word = "aaa"
 * 输出：6
 * 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
 *
 * 示例 3：
 * 输入：word = "abc"
 * 输出：0
 * 解释：word 已经是有效字符串，不需要进行修改。
 *
 * 提示：
 * 1 <= word.length <= 50
 * word 仅由字母 "a"、"b" 和 "c" 组成。
 */
public class AddMinimum {

    public int addMinimum(String word) {
        int count = 0;
        int index = 0;
        int n = word.length();
        while (index < n) {
            if (word.charAt(index) == 'a') {
                if (index + 1 < n) {
                    if (word.charAt(index + 1) == 'b') {
                        if (index + 2 < n) {
                            if (word.charAt(index + 2) == 'c') {
                                //此时结构为‘abc..’
                                index += 3;
                            } else {
                                index += 2;
                                count++;
                            }
                        } else {
                            //此时以字符‘ab’结尾
                            count++;
                            index += 2;
                        }
                    } else if (word.charAt(index + 1) == 'c') {
                        //此时字符结构为‘ac..’，需要中间插入‘b’
                        count++;
                        index += 2;
                    } else {
                        //此时字符结构为‘aa..’
                        index++;
                        count += 2;
                    }
                } else {
                    //此时'a'是最后一个字符
                    count += 2;
                    index++;
                }
            } else if (word.charAt(index) == 'b') {
                if (index + 1 < n) {
                    if (word.charAt(index + 1) == 'c') {
                        //此时字符结构为‘bc’，插入‘a’
                        index += 2;
                        count++;
                    } else {
                        index++;
                        count += 2;
                    }
                } else {
                    //最后一个字符为'b'
                    index++;
                    count += 2;
                }
            } else {
                //此时字符结构为‘c..’
                index++;
                count += 2;
            }
        }
        return count;
    }
}
