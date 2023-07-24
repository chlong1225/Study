package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/7/24
 * @author chenglong
 * description : 宝石与石头
 *
 * 给你一个字符串jewels代表石头中宝石的类型，另有一个字符串stones代表你拥有的石头。stones中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1：
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 *
 * 示例 2：
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 *
 * 提示：
 * 1 <=jewels.length, stones.length <= 50
 * jewels和stones仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 */
public class NumJewelsInStones {

    public int numJewelsInStones(String jewels, String stones) {
        boolean[] marks = new boolean[128];
        for (int i = 0; i < jewels.length(); i++) {
            marks[jewels.charAt(i)] = true;
        }
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (marks[stones.charAt(i)]) {
                count++;
            }
        }
        return count;
    }
}
