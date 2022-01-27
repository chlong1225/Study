package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/1/27
 * @author chenglong
 * description : 句子中的有效单词数
 *
 * 句子仅由小写字母（'a'到'z'）、数字（'0'到'9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
 * 每个句子可以根据空格分解成一个或者多个token，这些token之间由一个或者多个空格' '分隔。
 * 如果一个token同时满足下述条件，则认为这个token是一个有效单词：
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个连字符'-'。如果存在，连字符两侧应当都存在小写字母（"a-b"是一个有效单词，但"-ab"和"ab-"不是有效单词）。
 * 至多一个标点符号。如果存在，标点符号应当位于token的末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 *
 * 给你一个字符串sentence，请你找出并返回sentence中有效单词的数目 。
 *
 * 示例 1：
 * 输入：sentence = "cat and  dog"
 * 输出：3
 * 解释：句子中的有效单词是 "cat"、"and" 和 "dog"
 *
 * 示例 2：
 * 输入：sentence = "!this  1-s b8d!"
 * 输出：0
 * 解释：句子中没有有效单词
 * "!this" 不是有效单词，因为它以一个标点开头
 * "1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
 *
 * 示例 3：
 * 输入：sentence = "alice and  bob are playing stone-game10"
 * 输出：5
 * 解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
 * "stone-game10" 不是有效单词，因为它含有数字
 *
 * 示例 4：
 * 输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
 * 输出：6
 * 解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
 *
 * 提示：
 * 1 <= sentence.length <= 1000
 * sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
 * 句子中至少有 1 个 token
 */
public class ValidWords {

    private static final int MAX_INDEX = 1001;

    public int countValidWords(String sentence) {
        int count = 0;
        //记录是否开始记录单词
        boolean isStart = false;
        //记录单词中是否存在数字
        boolean hasNum = false;
        //记录单词中连字符的位置
        int connectIndex = -1;
        //记录单词中符号的位置
        int symbolIndex = -1;
        int length = sentence.length();
        for (int i = 0; i < length; i++) {
            char tem = sentence.charAt(i);
            if (tem == ' ') {
                //当前位置为空格，判断之前是否记录了单词
                if (isStart) {
                    //此时校验单词是否有效
                    if (!hasNum) {
                        //不含数字
                        if (checkConnect(connectIndex, length, sentence)) {
                            //连字符有效
                            if (checkSymbol(symbolIndex, length, sentence)) {
                                count++;
                            }
                        }
                    }
                    //重置状态
                    isStart = false;
                    hasNum = false;
                    connectIndex = -1;
                    symbolIndex = -1;
                }
            } else {
                isStart = true;
                //判断当前字符是否为数字
                if (tem >= '0' && tem <= '9') {
                    hasNum = true;
                } else if (tem == '-') {
                    //如果超过一个连字符，index设置为max_index，否则记录当前的位置
                    if (connectIndex == -1) {
                        connectIndex = i;
                    } else {
                        connectIndex = MAX_INDEX;
                    }
                } else if (tem == '!' || tem == '.' || tem == ',') {
                    //当前字符非标点符号
                    if (symbolIndex == -1) {
                        symbolIndex = i;
                    } else {
                        symbolIndex = MAX_INDEX;
                    }
                }
            }
        }
        if (isStart) {
            //此时校验单词是否有效
            if (!hasNum) {
                //不含数字
                if (checkConnect(connectIndex, length, sentence)) {
                    //连字符有效
                    if (checkSymbol(symbolIndex, length, sentence)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //判断标点符号是否有效
    private boolean checkSymbol(int symbolIndex, int length, String sentence) {
        //没有标点符号
        if (symbolIndex == -1) {
            return true;
        }
        //超过一个标点符号
        if (symbolIndex == MAX_INDEX) {
            return false;
        }
        //只有一个标点符号(符号在字符串结尾或下个位置为空格，此时可以保证符号在单词结尾)
        return symbolIndex == length - 1 || sentence.charAt(symbolIndex + 1) == ' ';
    }

    //判断连字符是否有效
    private boolean checkConnect(int connectIndex, int length, String sentence) {
        //没有连字符
        if (connectIndex == -1) {
            return true;
        }
        //超过一个连字符
        if (connectIndex == MAX_INDEX) {
            return false;
        }
        //只有一个连字符
        if (connectIndex == 0 || connectIndex == length - 1) {
            return false;
        }
        return isLetter(sentence.charAt(connectIndex - 1)) && isLetter(sentence.charAt(connectIndex + 1));
    }

    //判断是否为小写字母
    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z';
    }
}
