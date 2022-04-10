package com.demo.algorithm.leetcode.contest.doubleweek73;

/**
 * Created by chl on 2022/3/8.
 * description : 得到回文串的最少操作次数
 *
 * 给你一个只包含小写英文字母的字符串s。
 * 每一次操作，你可以选择s中两个相邻的字符，并将它们交换。
 * 请你返回将s变成回文串的最少操作次数。
 * 注意，输入数据会确保s一定能变成一个回文串。
 *
 * 示例 1：
 * 输入：s = "aabb"
 * 输出：2
 * 解释：
 * 我们可以将 s 变成 2 个回文串，"abba" 和 "baab" 。
 * - 我们可以通过 2 次操作得到 "abba" ："aabb" -> "abab" -> "abba" 。
 * - 我们可以通过 2 次操作得到 "baab" ："aabb" -> "abab" -> "baab" 。
 * 因此，得到回文串的最少总操作次数为 2 。
 *
 * 示例 2：
 * 输入：s = "letelt"
 * 输出：2
 * 解释：
 * 通过 2 次操作从 s 能得到回文串 "lettel" 。
 * 其中一种方法是："letelt" -> "letetl" -> "lettel" 。
 * 其他回文串比方说 "tleelt" 也可以通过 2 次操作得到。
 * 可以证明少于 2 次操作，无法得到回文串。
 *
 * 提示：
 * 1 <= s.length <= 2000
 * s只包含小写英文字母。
 * s可以通过有限次操作得到一个回文串。
 */
public class MinStepToMakePalindrome {

    public int minMovesToMakePalindrome(String s) {
        int length = s.length();
        //1，长度为1或2时，当前字符串直接为回文
        if (length <= 2) {
            return 0;
        }
        char[] dates = s.toCharArray();
        //2，超过2个字符时，区分奇偶数长度。奇数长度时，将没有对应的字符转移到中间
        int step = 0;
        boolean isDouble = (length % 2 == 0);
        int[] counts = new int[26];
        char middle = 'a';
        if (!isDouble) {
            //统计每个字符的数量，判断最后居中字符
            for (int i = 0; i < length; i++) {
                counts[dates[i] - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (counts[i] % 2 == 1) {
                    middle += i;
                    break;
                }
            }
        }
        for (int i = 0; i < length / 2; i++) {
            int left = i;
            int right = length - 1 - i;
            //前后字符已经对称时，不处理
            if (dates[left] == dates[right]) {
                continue;
            }
            if (isDouble) {
                //1，以左边的字符为标准
                int end = right - 1;
                while (end > left) {
                    if (dates[end] == dates[left]) {
                        break;
                    }
                    end--;
                }
                int spaceRight = right - end;
                //2，以右边的字符为标准
                int start = left + 1;
                while (start < right) {
                    if (dates[start] == dates[right]) {
                        break;
                    }
                    start++;
                }
                int spaceLeft = start - left;
                if (spaceLeft > spaceRight) {
                    //以左边字符为标准，右边字符移动替换
                    for (int j = end; j < right; j++) {
                        dates[j] = dates[j + 1];
                    }
                    dates[right] = dates[left];
                    step += spaceRight;
                } else {
                    //以右边字符为标准，左边字符移动替换
                    for (int j = start; j > left; j--) {
                        dates[j] = dates[j - 1];
                    }
                    dates[left] = dates[right];
                    step += spaceLeft;
                }
            } else {
                if (dates[left] == middle && counts[middle - 'a'] == 1) {
                    //以右边的字符为标准
                    int start = left + 1;
                    while (start < right) {
                        if (dates[start] == dates[right]) {
                            break;
                        }
                        start++;
                    }
                    int spaceLeft = start - left;
                    for (int j = start; j > left; j--) {
                        dates[j] = dates[j - 1];
                    }
                    dates[left] = dates[right];
                    step += spaceLeft;
                } else if (dates[right] == middle && counts[middle - 'a'] == 1) {
                    //以左边的字符为标准
                    int end = right - 1;
                    while (end > left) {
                        if (dates[end] == dates[left]) {
                            break;
                        }
                        end--;
                    }
                    int spaceRight = right - end;
                    for (int j = end; j < right; j++) {
                        dates[j] = dates[j + 1];
                    }
                    dates[right] = dates[left];
                    step += spaceRight;
                } else {
                    //此时跟偶数相同
                    //1，以左边的字符为标准
                    int end = right - 1;
                    while (end > left) {
                        if (dates[end] == dates[left]) {
                            break;
                        }
                        end--;
                    }
                    int spaceRight = right - end;
                    //2，以右边的字符为标准
                    int start = left + 1;
                    while (start < right) {
                        if (dates[start] == dates[right]) {
                            break;
                        }
                        start++;
                    }
                    int spaceLeft = start - left;
                    if (spaceLeft > spaceRight) {
                        //以左边字符为标准，右边字符移动替换
                        for (int j = end; j < right; j++) {
                            dates[j] = dates[j + 1];
                        }
                        dates[right] = dates[left];
                        step += spaceRight;
                        if (dates[left] == middle) {
                            counts[middle - 'a'] -= 2;
                        }
                    } else {
                        //以右边字符为标准，左边字符移动替换
                        for (int j = start; j > left; j--) {
                            dates[j] = dates[j - 1];
                        }
                        dates[left] = dates[right];
                        step += spaceLeft;
                        if (dates[right] == middle) {
                            counts[middle - 'a'] -= 2;
                        }
                    }
                }
            }
        }
        return step;
    }
}
