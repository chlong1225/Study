package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/10/23.
 * description : 扰乱字符串
 *
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：s1 = "great", s2 = "rgeat"
 * 输出：true
 * 解释：s1 上可能发生的一种情形是：
 * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
 *
 * 示例 2：
 * 输入：s1 = "abcde", s2 = "caebd"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "a", s2 = "a"
 * 输出：true
 *  
 * 提示：
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 */
public class ScrambleStr {

    //用于记录已经检查的结果。1：代表可以扰乱匹配，-1：代表不可以扰乱匹配
    private int[][][] calculates;
    //将s1,s2转成数组
    private int[] data1;
    private int[] data2;

    public boolean isScramble(String s1, String s2) {
        int length = s1.length();
        calculates = new int[length][length][length + 1];
        data1 = new int[length];
        data2 = new int[length];
        for (int i = 0; i < length; i++) {
            data1[i] = s1.charAt(i) - 'a';
            data2[i] = s2.charAt(i) - 'a';
        }
        return checkScramble(0, 0, length);
    }

    private boolean checkScramble(int index1, int index2, int length) {
        if (calculates[index1][index2][length] != 0) {
            return calculates[index1][index2][length] == 1;
        }
        int result = compareStr(index1, index2, length);
        if (result == 1) {
            calculates[index1][index2][length] = 1;
            return true;
        }
        if (result == -1) {
            calculates[index1][index2][length] = -1;
            return false;
        }
        //result = 0需要分割
        for (int i = 1; i < length; i++) {
            //不交换
            if (checkScramble(index1, index2, i) && checkScramble(index1 + i, index2 + i, length - i)) {
                calculates[index1][index2][length] = 1;
                return true;
            }
            //交换
            if (checkScramble(index1, index2 + length - i, i) && checkScramble(index1 + i, index2, length - i)) {
                calculates[index1][index2][length] = 1;
                return true;
            }
        }
        calculates[index1][index2][length] = -1;
        return false;
    }

    //s1能够扰乱返回1,不能扰乱返回-1,需要拆分返回0
    private int compareStr(int index1, int index2, int length) {
        boolean isSame = true;
        for (int i = 0; i < length; i++) {
            if (data1[index1 + i] != data2[index2 + i]) {
                isSame = false;
                break;
            }
        }
        if (isSame) {
            return 1;
        }
        if (hasNoContain(index1, index2, length)) {
            return -1;
        }
        if (length <= 3) {
            return 1;
        }
        return 0;
    }

    //比较s1中从index1开始是否存在s2中从index2开始没有的字符
    private boolean hasNoContain(int index1, int index2, int length) {
        int[] counts = new int[26];
        for (int i = 0; i < length; i++) {
            counts[data2[index2 + i]]++;
        }
        for (int i = 0; i < length; i++) {
            int index = data1[index1 + i];
            if (counts[index] > 0) {
                counts[index]--;
            } else {
                return true;
            }
        }
        return false;
    }

}
