package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/8/16
 * @author chenglong
 * description :  找出转圈游戏输家
 *
 * n个朋友在玩游戏。这些朋友坐成一个圈，按顺时针方向从1到n编号。从第i个朋友的位置开始顺时针移动1步会到达第(i+1)个朋友的位置（1 <= i < n），而从第n个朋友的位置开始顺时针移动1步会回到第1个朋友的位置。
 * 游戏规则如下：
 * 第1个朋友接球。
 * 接着，第1个朋友将球传给距离他顺时针方向k步的朋友。
 * 然后，接球的朋友应该把球传给距离他顺时针方向2*k步的朋友。
 * 接着，接球的朋友应该把球传给距离他顺时针方向3*k步的朋友，以此类推。
 * 换句话说，在第i轮中持有球的那位朋友需要将球传递给距离他顺时针方向i*k步的朋友。
 * 当某个朋友第2次接到球时，游戏结束。
 * 在整场游戏中没有接到过球的朋友是输家 。
 * 给你参与游戏的朋友数量n和一个整数k，请按升序排列返回包含所有输家编号的数组answer作为答案。
 *
 * 示例 1：
 * 输入：n = 5, k = 2
 * 输出：[4,5]
 * 解释：以下为游戏进行情况：
 * 1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 2 步的玩家 —— 第 3 个朋友。
 * 2）第 3 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 2 个朋友。
 * 3）第 2 个朋友将球传给距离他顺时针方向 6 步的玩家 —— 第 3 个朋友。
 * 4）第 3 个朋友接到两次球，游戏结束。
 *
 * 示例 2：
 * 输入：n = 4, k = 4
 * 输出：[2,3,4]
 * 解释：以下为游戏进行情况：
 * 1）第 1 个朋友接球，第 1 个朋友将球传给距离他顺时针方向 4 步的玩家 —— 第 1 个朋友。
 * 2）第 1 个朋友接到两次球，游戏结束。
 *
 * 提示：
 * 1 <= k <= n <= 50
 */
public class CircularGameLosers {

    public int[] circularGameLosers(int n, int k) {
        //记录当前朋友是否接球了，从0开始记录
        boolean[] marks = new boolean[n];
        marks[0] = true;
        //当前接球的位置
        int index = 0;
        //球传递的步数
        int jump = k;
        while (true) {
            index += jump;
            index %= n;
            jump += k;
            jump %= n;
            if (marks[index]) {
                break;
            }
            marks[index] = true;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!marks[i]) {
                result.add(i + 1);
            }
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
