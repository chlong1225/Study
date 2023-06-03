package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/5/30
 * @author chenglong
 * description : 活字印刷
 *
 * 你有一套活字字模tiles，其中每个字模上都刻有一个字母tiles[i]。返回你可以印出的非空字母序列的数目。
 * 注意：本题中，每个活字字模只能使用一次。
 *
 * 示例 1：
 * 输入："AAB"
 * 输出：8
 * 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
 *
 * 示例 2：
 * 输入："AAABBC"
 * 输出：188
 *
 * 示例 3：
 * 输入："V"
 * 输出：1
 *
 * 提示：
 * 1 <= tiles.length <= 7
 * tiles 由大写英文字母组成
 */
public class NumTilePossibilities {

    public int numTilePossibilities(String tiles) {
        int n = tiles.length();
        //1，统计相同字母出现的次数
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[tiles.charAt(i) - 'A']++;
        }
        //统计不同数量字母的个数
        int[] dates = new int[8];
        //统计总字母个数
        int letterCount = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                letterCount++;
                dates[counts[i]]++;
            }
        }
        for (int i = 6; i >= 0; i--) {
            dates[i] += dates[i + 1];
        }
        int sum = 0;
        //2，统计一个字母的场景
        sum += letterCount;
        if (n >= 2) {
            sum += getTwoCount(letterCount, dates);
        }
        if (n >= 3) {
            sum += getThreeCount(letterCount, dates);
        }
        if (n >= 4) {
            sum += getFourCount(letterCount, dates);
        }
        if (n >= 5) {
            sum += getFiveCount(letterCount, dates);
        }
        if (n >= 6) {
            sum += getSixCount(letterCount, dates);
        }
        if (n >= 7) {
            sum += getSevenCount(letterCount, dates);
        }
        return sum;
    }

    private int getSevenCount(int letterCount, int[] dates) {
        /**
         * 统计七个字母的场景
         * 七个字母相同
         * 六个字母相同+一个字母不同
         * 五个字母相同+两个字母相同
         * 五个字母相同+两个字母不同
         * 四个字母相同+三个字母相同
         * 四个字母相同+两个字母相同+一个字母
         * 四个字母相同+三个字母不同
         * 三个字母相同+三个字母相同+一个字母
         * 三个字母相同+两个字母相同+两个相同字母
         * 三个字母相同+两个字母相同+两个字母不同
         * 三个字母相同+四个字母不同
         * 两个字母相同+两个字母相同+两个字母相同+一个字母
         * 两个字母相同+两个字母相同+三个字母不同
         * 两个字母相同+五个字母不同
         * 七个字母不同
         */
        int sum = dates[7];
        sum += dates[6] * (letterCount - 1) * 7;
        sum += dates[5] * (dates[2] - 1) * 21;
        sum += dates[5] * (letterCount - 1) * (letterCount - 2) * 21;
        sum += dates[4] * (dates[3] - 1) * 35;
        sum += dates[4] * (dates[2] - 1) * (letterCount - 2) * 105;
        sum += dates[4] * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * 35;
        sum += dates[3] * (dates[3] - 1) * (letterCount - 2) * 70;
        sum += dates[3] * (dates[2] - 1) * (dates[2] - 2) * 105;
        sum += dates[3] * (dates[2] - 1) * (letterCount - 2) * (letterCount - 3) * 210;
        sum += dates[3] * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4) * 35;
        sum += dates[2] * (dates[2] - 1) * (dates[2] - 2) * (letterCount - 3) * 105;
        sum += dates[2] * (dates[2] - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4) * 105;
        sum += dates[2] * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4) * (letterCount - 5) * 21;
        if (letterCount >= 7) {
            sum += letterCount * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4) * (letterCount - 5) * (letterCount - 6);
        }
        return sum;
    }

    private int getSixCount(int letterCount, int[] dates) {
        /**
         * 统计六个字母的场景
         * 六个字母相同
         * 五个字母相同+一个字母不同
         * 四个字母相同+两个字母相同
         * 四个字母相同+两个字母不同
         * 三个字母相同+三个字母相同
         * 三个字母相同+两个字母相同+一个字母
         * 三个字母相同+三个字母不同
         * 两个字母相同+两个字母相同+两个字母相同
         * 两个字母相同+两个字母相同+两个字母不同
         * 两个字母相同+四个字母不同
         * 六个字母不同
         */
        int sum = dates[6];
        sum += dates[5] * (letterCount - 1) * 6;
        sum += dates[4] * (dates[2] - 1) * 15;
        sum += dates[4] * (letterCount - 1) * (letterCount - 2) * 15;
        sum += dates[3] * (dates[3] - 1) * 10;
        sum += dates[3] * (dates[2] - 1) * (letterCount - 2) * 60;
        sum += dates[3] * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * 20;
        sum += dates[2] * (dates[2] - 1) * (dates[2] - 2) * 15;
        sum += dates[2] * (dates[2] - 1) * (letterCount - 2) * (letterCount - 3) * 45;
        sum += dates[2] * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4) * 15;
        if (letterCount >= 6) {
            sum += letterCount * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4) * (letterCount - 5);
        }
        return sum;
    }

    private int getFiveCount(int letterCount, int[] dates) {
        /**
         * 统计五个字母的场景
         * 五个字母相同
         * 四个字母相同+一个字母不同
         * 三个字母相同+两个字母相同
         * 三个字母相同+两个字母不同
         * 两个字母相同+两个字母相同+一个字母不同
         * 两个字母相同+三个字母不同
         * 五个字母不同
         */
        int sum = dates[5];
        sum += dates[4] * (letterCount - 1) * 5;
        sum += dates[3] * (dates[2] - 1) * 10;
        sum += dates[3] * (letterCount - 1) * (letterCount - 2) * 10;
        sum += dates[2] * (dates[2] - 1) * (letterCount - 2) * 15;
        sum += dates[2] * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * 10;
        if (letterCount >= 5) {
            sum += letterCount * (letterCount - 1) * (letterCount - 2) * (letterCount - 3) * (letterCount - 4);
        }
        return sum;
    }

    private int getFourCount(int letterCount, int[] dates) {
        //统计四个字母的场景：四个字母相同，三个字母相同+1个字母不同，两个相同+两个相同，两个相同+两个不同，四个都不同
        int sum = dates[4];
        sum += dates[3] * (letterCount - 1) * 4;
        sum += dates[2] * (dates[2] - 1) * 3;
        sum += dates[2] * (letterCount - 1) * (letterCount - 2) * 6;
        if (letterCount >= 4) {
            sum += letterCount * (letterCount - 1) * (letterCount - 2) * (letterCount - 3);
        }
        return sum;
    }

    private int getThreeCount(int letterCount, int[] dates) {
        //统计三个字母的场景。三个字母都相同；两个字母相同，任取其它字母，三个字母都不同
        int sum = dates[3];
        sum += dates[2] * (letterCount - 1) * 3;
        if (letterCount >= 3) {
            sum += (letterCount - 1) * (letterCount - 2) * letterCount;
        }
        return sum;
    }

    private int getTwoCount(int letterCount, int[] dates) {
        //统计两个字母的场景。两个字母相同，两个字母不同
        int sum = dates[2];
        sum += (letterCount - 1) * letterCount;
        return sum;
    }
}
