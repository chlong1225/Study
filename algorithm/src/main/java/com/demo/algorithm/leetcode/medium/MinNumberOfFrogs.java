package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/5/6
 *
 * @author chenglong
 * description : 数青蛙
 *
 * 给你一个字符串croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串"croak"）的组合。由于同一时间可以有多只青蛙呱呱作响，所以croakOfFrogs中会混合多个“croak”。
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * 要想发出蛙鸣"croak"，青蛙必须依序输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 *
 * 示例 1：
 * 输入：croakOfFrogs = "croakcroak"
 * 输出：1
 * 解释：一只青蛙“呱呱”两次
 *
 * 示例 2：
 * 输入：croakOfFrogs = "crcoakroak"
 * 输出：2
 * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
 * 第一只青蛙 "crcoakroak"
 * 第二只青蛙 "crcoakroak"
 *
 * 示例 3：
 * 输入：croakOfFrogs = "croakcrook"
 * 输出：-1
 * 解释：给出的字符串不是 "croak" 的有效组合。
 *
 * 提示：
 * 1 <= croakOfFrogs.length <= 10^5
 * 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
 */
public class MinNumberOfFrogs {

    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        //1，字符长度必须是5的倍数，最后一个字符必须是k
        if (n % 5 != 0 || croakOfFrogs.charAt(n - 1) != 'k') {
            return -1;
        }
        int total = 0;
        //分别记录c,r,o,a,k出现的次数
        int[] counts = new int[5];
        for (int i = 0; i < n; i++) {
            int index = getIndex(croakOfFrogs.charAt(i));
            if (index == 0) {
                if (total > 0) {
                    total--;
                }
                counts[index]++;
            } else {
                //判断当前字符是否合法
                for (int j = 0; j < index; j++) {
                    if (counts[j] <= counts[index]) {
                        return -1;
                    }
                }
                if (index == 4) {
                    //最后一个k时可以从前面依次取出字符构成croak
                    total++;
                    counts[0]--;
                    counts[1]--;
                    counts[2]--;
                    counts[3]--;
                } else {
                    counts[index]++;
                }
            }
        }
        //检查是否都使用完
        if (counts[0] != 0 || counts[1] != 0 || counts[2] != 0 || counts[3] != 0 || counts[4] != 0) {
            return -1;
        }
        return total;
    }

    private int getIndex(char c) {
        if (c == 'c') {
            return 0;
        }
        if (c == 'r') {
            return 1;
        }
        if (c == 'o') {
            return 2;
        }
        if (c == 'a') {
            return 3;
        }
        return 4;
    }
}
