package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/2/13
 * @author chenglong
 * description : 替换子串得到平衡字符串
 *
 * 有一个只含有'Q','W','E','R'四种字符，且长度为n的字符串。
 * 假如在该字符串中，这四个字符都恰好出现n/4次，那么它就是一个「平衡字符串」。
 * 给你一个这样的字符串s，请通过「替换一个子串」的方式，使原字符串s变成一个「平衡字符串」。
 * 你可以用和「待替换子串」长度相同的任何其他字符串来完成替换。
 * 请返回待替换子串的最小可能长度。
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 *
 * 示例 1：
 * 输入：s = "QWER"
 * 输出：0
 * 解释：s 已经是平衡的了。
 *
 * 示例 2：
 * 输入：s = "QQWE"
 * 输出：1
 * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
 *
 * 示例 3：
 * 输入：s = "QQQW"
 * 输出：2
 * 解释：我们可以把前面的 "QQ" 替换成 "ER"。
 *
 * 示例 4：
 * 输入：s = "QQQQ"
 * 输出：3
 * 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s.length是4的倍数
 * s中只含有'Q', 'W', 'E','R'四种字符
 */
public class BalancedString {

    public int balancedString(String s) {
        int length = s.length();
        //1，统计字符的个数
        int[] counts = new int[4];
        for (int i = 0; i < length; i++) {
            counts[getIndex(s.charAt(i))]++;
        }
        //2，判断是否本身为平衡字符串
        int target = length / 4;
        if (counts[0] == target && counts[1] == target && counts[2] == target && counts[3] == target) {
            return 0;
        }
        //3，统计多余需要被替换的字符，只要子串中包含多余字符，缺失的字符可以自动替换
        int[] reduces = new int[4];
        for (int i = 0; i < 4; i++) {
            reduces[i] = Math.max(0, counts[i] - target);
        }
        //4，使用滑动窗口查找包含自定字符的最短子串
        int[] dates = new int[4];
        int min = length;
        int start = 0;
        int end = 0;
        for (int i = 0; i < length; i++) {
            dates[getIndex(s.charAt(i))]++;
            if (isInclude(dates, reduces)) {
                //此时结束查找
                end = i;
                min = end - start + 1;
                break;
            }
        }
        //是否滑动起始窗口
        boolean moveStart = true;
        while (end < length) {
            if (moveStart) {
                //滑动起始点时，对应的统计值减少
                while (start <= end) {
                    dates[getIndex(s.charAt(start))]--;
                    start++;
                    //滑动后判断区间是否包含指定字符，不包含时，需要向右边滑动
                    if (!isInclude(dates, reduces)) {
                        moveStart = false;
                        break;
                    } else {
                        if (min > end - start + 1) {
                            min = end - start + 1;
                        }
                    }
                }
            } else {
                //滑动右边
                if (end >= length - 1) {
                    break;
                }
                while (end < length - 1) {
                    //右边继续添加字符
                    end++;
                    dates[getIndex(s.charAt(end))]++;
                    if (isInclude(dates, reduces)) {
                        if (min > end - start + 1) {
                            min = end - start + 1;
                        }
                        moveStart = true;
                        break;
                    }
                }
            }
        }
        return min;
    }

    private int getIndex(char c) {
        if (c == 'Q') {
            return 0;
        } else if (c == 'W') {
            return 1;
        } else if (c == 'E') {
            return 2;
        }
        return 3;
    }

    private boolean isInclude(int[] dates, int[] reduces) {
        for (int i = 0; i < 4; i++) {
            if (dates[i] < reduces[i]) {
                return false;
            }
        }
        return true;
    }
}
