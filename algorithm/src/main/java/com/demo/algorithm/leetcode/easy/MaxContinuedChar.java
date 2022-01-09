package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/1/9.
 * description :  按键持续时间最长的键
 *
 * LeetCode设计了一款新式键盘，正在测试其可用性。测试人员将会点击一系列键（总计n个），每次一个。
 *
 * 给你一个长度为n的字符串keysPressed，其中keysPressed[i]表示测试序列中第i个被按下的键。
 * releaseTimes是一个升序排列的列表，其中releaseTimes[i]表示松开第i个键的时间。
 * 字符串和数组的下标都从0开始 。第0个键在时间为0时被按下，接下来每个键都恰好在前一个键松开时被按下。
 * 测试人员想要找出按键持续时间最长的键。第i次按键的持续时间为releaseTimes[i]-releaseTimes[i-1]，
 * 第0次按键的持续时间为releaseTimes[0] 。
 * 注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。
 * 请返回按键 持续时间最长 的键，如果有多个这样的键，则返回 按字母顺序排列最大 的那个键。
 *
 * 示例 1：
 * 输入：releaseTimes = [9,29,49,50], keysPressed = "cbcd"
 * 输出："c"
 * 解释：按键顺序和持续时间如下：
 * 按下 'c' ，持续时间 9（时间 0 按下，时间 9 松开）
 * 按下 'b' ，持续时间 29 - 9 = 20（松开上一个键的时间 9 按下，时间 29 松开）
 * 按下 'c' ，持续时间 49 - 29 = 20（松开上一个键的时间 29 按下，时间 49 松开）
 * 按下 'd' ，持续时间 50 - 49 = 1（松开上一个键的时间 49 按下，时间 50 松开）
 * 按键持续时间最长的键是 'b' 和 'c'（第二次按下时），持续时间都是 20
 * 'c' 按字母顺序排列比 'b' 大，所以答案是 'c'
 *
 * 示例 2：
 * 输入：releaseTimes = [12,23,36,46,62], keysPressed = "spuda"
 * 输出："a"
 * 解释：按键顺序和持续时间如下：
 * 按下 's' ，持续时间 12
 * 按下 'p' ，持续时间 23 - 12 = 11
 * 按下 'u' ，持续时间 36 - 23 = 13
 * 按下 'd' ，持续时间 46 - 36 = 10
 * 按下 'a' ，持续时间 62 - 46 = 16
 * 按键持续时间最长的键是 'a' ，持续时间 16
 *
 * 提示：
 * releaseTimes.length == n
 * keysPressed.length == n
 * 2 <= n <= 1000
 * 1 <= releaseTimes[i] <= 109
 * releaseTimes[i] < releaseTimes[i+1]
 * keysPressed 仅由小写英文字母组成
 */
public class MaxContinuedChar {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int length = keysPressed.length();
        int time = releaseTimes[0];
        char result = keysPressed.charAt(0);
        for (int i = 1; i < length; i++) {
            int space = releaseTimes[i] - releaseTimes[i - 1];
            if (space > time) {
                result = keysPressed.charAt(i);
                time = space;
            } else if (time == space) {
                if (result < keysPressed.charAt(i)) {
                    result = keysPressed.charAt(i);
                }
            }
        }
        return result;
    }
}
