package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/2/21
 * @author chenglong
 * description : 推多米诺
 *
 * n张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 * 示例 1：
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 *
 * 示例 2：
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 *
 * 提示：
 * n == dominoes.length
 * 1 <= n <= 10^5
 * dominoes[i] 为 'L'、'R' 或 '.'
 */
public class PushDomilo {

    public String pushDominoes(String dominoes) {
        int length = dominoes.length();
        //记录出现.的次数
        int count = 0;
        char left = 0;
        char right;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (dominoes.charAt(i) == '.') {
                count++;
            } else {
                if (count > 0) {
                    //此时在.之后
                    right = dominoes.charAt(i);
                    //转换count个.
                    if (left == 0) {
                        for (int j = 0; j < count; j++) {
                            if (right == 'L') {
                                builder.append('L');
                            } else {
                                builder.append('.');
                            }
                        }
                    } else {
                        push(left, right, count, builder);
                    }
                    //count重置
                    count = 0;
                }
                left = dominoes.charAt(i);
                builder.append(left);
            }
        }
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                if (left == 'L' || left == 0) {
                    builder.append('.');
                } else {
                    builder.append('R');
                }
            }
        }
        return builder.toString();
    }

    private void push(char left, char right, int count, StringBuilder builder) {
        if (left == 'L' && right == 'R') {
            for (int i = 0; i < count; i++) {
                builder.append('.');
            }
        } else if (left == 'R' && right == 'R') {
            for (int i = 0; i < count; i++) {
                builder.append('R');
            }
        } else if (left == 'L' && right == 'L') {
            for (int i = 0; i < count; i++) {
                builder.append('L');
            }
        } else {
            int num = count / 2;
            for (int i = 0; i < num; i++) {
                builder.append('R');
            }
            if (count % 2 != 0) {
                builder.append('.');
            }
            for (int i = 0; i < num; i++) {
                builder.append('L');
            }
        }
    }
}
