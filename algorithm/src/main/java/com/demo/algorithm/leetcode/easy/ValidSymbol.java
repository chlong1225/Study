package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 9/3/21
 * description :  有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 *
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class ValidSymbol {

    private static final String[] LEFT_SYMBOL = {"(", "[", "{"};
    private static final String[] RIGHT_SYMBOL = {")", "]", "}"};

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int[] leftSymbolIndexLists = new int[10];
        int count = 0;
        String tem = "";
        for (int i = 0; i < s.length(); i++) {
            tem = s.substring(i, i + 1);
            int leftSymbolIndex = isLeftSymbolIndex(tem);
            if (leftSymbolIndex != -1) {
                leftSymbolIndexLists = checkLists(leftSymbolIndexLists, count);
                leftSymbolIndexLists[count] = leftSymbolIndex;
                count++;
            } else {
                int rightSymbolIndex = isRightSymbolIndex(tem);
                if (rightSymbolIndex != -1) {
                    if (count == 0) {
                        return false;
                    }
                    if (leftSymbolIndexLists[count - 1] == rightSymbolIndex) {
                        leftSymbolIndexLists[count - 1] = 0;
                        count--;
                    } else {
                        return false;
                    }
                }
            }

        }
        return count == 0;
    }

    //检查数组是否需要扩容
    private static int[] checkLists(int[] datas, int count) {
        if (datas.length <= count) {
            //数组扩容
            int[] result = new int[datas.length * 2];
            for (int i = 0; i < datas.length; i++) {
                result[i] = datas[i];
            }
            return result;
        }
        return datas;
    }

    private static int isRightSymbolIndex(String s) {
        for (int i = 0; i < RIGHT_SYMBOL.length; i++) {
            if (RIGHT_SYMBOL[i].equals(s)) {
                return i + 1;
            }
        }
        return -1;
    }

    //判断当前字符在左括号中的位置，不是左括号返回-1
    private static int isLeftSymbolIndex(String s) {
        for (int i = 0; i < LEFT_SYMBOL.length; i++) {
            if (LEFT_SYMBOL[i].equals(s)) {
                return i + 1;
            }
        }
        return -1;
    }
}
