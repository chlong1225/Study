package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/5
 * @author chenglong
 * description :
 */
public class GuessGame {

    private int pick;

    public int guess(int num) {
        if (num == pick) {
            return 0;
        }
        if (num < pick) {
            return 1;
        }
        return -1;
    }
}
