package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/6/4.
 * description : 面试题05.03. 翻转数位
 *
 * 给定一个32位整数num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 *
 * 示例 1：
 * 输入: num = 1775(11011101111)
 * 输出: 8
 *
 * 示例 2：
 * 输入: num = 7(0111)
 * 输出: 4
 */
public class ReverseBits {

    public int reverseBits(int num) {
        if (num == 0) {
            return 1;
        }
        int max = 1;
        int count = 0;
        int reverseCount = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & 1) == 1) {
                //当前位为1
                count++;
                reverseCount++;
            } else {
                //当前位为0
                reverseCount = count + 1;
                count = 0;
            }
            if (reverseCount > max) {
                max = reverseCount;
            }
            num >>= 1;
        }
        return max;
    }
}
