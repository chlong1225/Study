package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/9/30
 * @author chenglong
 * description : 种花问题
 *
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给你一个整数数组flowerbed表示花坛，由若干0和1组成，其中0表示没种植花，1表示种植了花。另有一个数n，能否在不打破种植规则的情况下种入n朵花？
 * 能则返回true ，不能则返回false。
 *
 * 示例 1：
 * 输入：flowerbed = [1,0,0,0,1], n = 1
 * 输出：true
 *
 * 示例 2：
 * 输入：flowerbed = [1,0,0,0,1], n = 2
 * 输出：false
 *
 * 提示：
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] 为0或1
 * flowerbed 中不存在相邻的两朵花
 * 0 <= n <= flowerbed.length
 */
public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 1) {
                return n == 0;
            } else {
                return true;
            }
        }
        //1，检查最多可以种的花朵数量
        int max = (flowerbed.length + 1) / 2;
        if (n > max) {
            return false;
        }
        //2，依次尝试种植花朵
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            if (i == 0) {
                if (flowerbed[1] == 0) {
                    count++;
                    flowerbed[0] = 1;
                }
            } else if (i == flowerbed.length - 1) {
                if (flowerbed[i - 1] == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            } else {
                if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            }
        }
        return count >= n;
    }
}
