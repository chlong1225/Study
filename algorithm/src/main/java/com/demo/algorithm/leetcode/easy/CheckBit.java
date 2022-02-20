package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/2/20.
 * description : 1比特与2比特字符
 *
 * 有两种特殊字符：
 * 第一种字符可以用一个比特0来表示
 * 第二种字符可以用两个比特(10或11)来表示、
 * 给定一个以0结尾的二进制数组bits，如果最后一个字符必须是一位字符，则返回true 。
 *
 * 示例1:
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 *
 * 示例2:
 * 输入: bits = [1, 1, 1, 0]
 * 输出: false
 * 解释: 唯一的编码方式是两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 *
 * 提示:
 * 1 <= bits.length <= 1000
 * bits[i] == 0 or 1
 */
public class CheckBit {

    public boolean isOneBitCharacter(int[] bits) {
        int length = bits.length;
        int index = 0;
        boolean isOne = true;
        while (index < length) {
            if (bits[index] == 0) {
                index++;
                isOne = true;
            } else {
                index += 2;
                isOne = false;
            }
        }
        return isOne;
    }
}
