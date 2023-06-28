package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/11/6.
 * description : 设计Goal解析器
 *
 * 请你设计一个可以解释字符串command的Goal解析器。command由"G"、"()" 和/或 "(al)" 按某种顺序组成。
 * Goal 解析器会将"G" 解释为字符串"G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。
 * 然后按原顺序将经解释得到的字符串连接成一个字符串。
 * 给你字符串command，返回Goal解析器对command的解释结果。
 *
 * 示例 1：
 * 输入：command = "G()(al)"
 * 输出："Goal"
 * 解释：Goal 解析器解释命令的步骤如下所示：
 * G -> G
 * () -> o
 * (al) -> al
 * 最后连接得到的结果是 "Goal"
 *
 * 示例 2：
 * 输入：command = "G()()()()(al)"
 * 输出："Gooooal"
 *
 * 示例 3：
 * 输入：command = "(al)G(al)()()G"
 * 输出："alGalooG"
 *
 * 提示：
 * 1 <= command.length <= 100
 * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成
 */
public class Interpret {

    public String interpret(String command) {
        int length = command.length();
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (index < length) {
            char cur = command.charAt(index);

            if (cur == 'G') {
                builder.append(cur);
                index++;
            } else {
                //此时'('
                if (command.charAt(index + 1) == ')') {
                    builder.append('o');
                    index += 2;
                } else {
                    builder.append("al");
                    index += 4;
                }
            }
        }
        return builder.toString();
    }
}
