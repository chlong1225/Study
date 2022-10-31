package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/10/31
 * @author chenglong
 * description : 神奇字符串
 *
 * 神奇字符串s仅由'1'和'2'组成，并需要遵守下面的规则：
 * 神奇字符串s的神奇之处在于，串联字符串中'1'和'2'的连续出现次数可以生成该字符串。
 * s的前几个元素是s="1221121221221121122……" 。如果将s中连续的若干1和2进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。
 * 每组中1或者2的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是s自身。
 * 给你一个整数n，返回在神奇字符串s的前n个数字中1的数目。
 *
 * 示例 1：
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 1 <= n <= 10^5
 */
public class MagicalString {

    public int magicalString(int n) {
        if (n < 4) {
            return 1;
        }
        int[] marks = new int[n];
        int total = 1;
        marks[0] = 1;
        marks[1] = 2;
        marks[2] = 2;
        int index = 2;
        int length = 3;
        while (length < n) {
            int count = marks[index];
            index++;
            int next = length + count;
            //检查防止越界
            if (next > n) {
                next = n;
                count = next - length;
            }
            if (marks[length - 1] == 1) {
                //此时添加2
                for (int i = length; i < next; i++) {
                    marks[i] = 2;
                }
            } else {
                //添加1
                total += count;
                for (int i = length; i < next; i++) {
                    marks[i] = 1;
                }
            }
            length = next;
        }
        return total;
    }
}
