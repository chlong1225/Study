package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/1/2.
 * description : 消除游戏
 *
 * 列表arr由在范围[1, n]中的所有整数组成，并按严格递增排序。请你对arr应用下述算法：
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数n，返回arr最后剩下的数字。
 *
 * 示例 1：
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= n <= 109
 */
public class ClearGame {

    //暴力枚举(运行超时)
    public int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        List<Integer> datas = new ArrayList<>();
        List<Integer> next = new ArrayList<>();
        //初始化第一次删除操作
        for (int i = 2; i <= n; i += 2) {
            datas.add(i);
        }
        boolean isFromLeft = false;
        while (!datas.isEmpty()) {
            int size = datas.size();
            if (size == 1) {
                return datas.get(0);
            }
            if (isFromLeft) {
                //从左向右删除
                for (int i = 1; i < size; i += 2) {
                    next.add(datas.get(i));
                }
            } else {
                //从右向左删除
                for (int i = size - 2; i >= 0; i -= 2) {
                    next.add(0, datas.get(i));
                }
            }
            isFromLeft = !isFromLeft;
            datas.clear();
            datas.addAll(next);
            next.clear();
        }
        return 0;
    }

    public int lastRemaining2(int n) {
        if (n == 1) {
            return 1;
        }
        return fromLeft(n);
    }

    private int fromLeft(int n) {
        if (n == 1) {
            return n;
        }
        return 2 * fromRight(n >> 1);
    }

    private int fromRight(int n) {
        if (n == 1) {
            return n;
        }
        if (n % 2 == 0) {
            return (fromLeft(n >> 1) << 1) - 1;
        } else {
            return fromLeft(n >> 1) << 1;
        }
    }
}
