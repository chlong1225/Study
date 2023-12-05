package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 猜数字大小
 *
 * 猜数字游戏的规则如下：
 * 每轮游戏，我都会从1到n随机选择一个数字。请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口int guess(int num)来获取猜测结果，返回值一共有3种可能的情况（-1，1或0）：
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 *
 * 示例 1：
 * 输入：n = 10, pick = 6
 * 输出：6
 *
 * 示例 2：
 * 输入：n = 1, pick = 1
 * 输出：1
 *
 * 示例 3：
 * 输入：n = 2, pick = 1
 * 输出：1
 *
 * 示例 4：
 * 输入：n = 2, pick = 2
 * 输出：2
 *
 * 提示：
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 */
public class Solution extends GuessGame{

    public int guessNumber(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (guess(middle) == 0) {
                return middle;
            }
            if (guess(middle) == 1) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return start;
    }
}
