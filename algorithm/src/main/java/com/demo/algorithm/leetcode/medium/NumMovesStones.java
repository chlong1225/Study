package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/5/4
 * @author chenglong
 * description : 移动石子直到连续
 *
 * 三枚石子放置在数轴上，位置分别为a，b，c。
 * 每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。形式上，假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。
 * 那么就可以从位置x或者是位置z拿起一枚石子，并将该石子移动到某一整数位置k处，其中x<k<z且k != y。
 * 当你无法进行任何移动时，即这些石子的位置连续时，游戏结束。
 * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？以长度为2的数组形式返回答案：answer = [minimum_moves, maximum_moves]
 *
 * 示例 1：
 * 输入：a = 1, b = 2, c = 5
 * 输出：[1, 2]
 * 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
 *
 * 示例 2：
 * 输入：a = 4, b = 3, c = 2
 * 输出：[0, 0]
 * 解释：我们无法进行任何移动。
 *
 * 提示：
 * 1 <= a <= 100
 * 1 <= b <= 100
 * 1 <= c <= 100
 * a != b, b != c, c != a
 */
public class NumMovesStones {

    public int[] numMovesStones(int a, int b, int c) {
        //1，对a，b，c大小进行排序。依次为：min，middle，max
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        int middle = a;
        if (a == min || a == max) {
            if (b == min || b == max) {
                middle = c;
            } else {
                middle = b;
            }
        }
        //2，处理连续的特殊场景
        if (max - min == 2) {
            //三个位置连续
            return new int[]{0, 0};
        }
        //3，获取最小移动次数
        int minMove = 2;
        if (max - middle == 1 || middle - min == 1 || max - middle == 2 || middle - min == 2) {
            minMove = 1;
        }
        //4，获取最大移动次数
        int maxMove = max - min - 2;
        return new int[]{minMove, maxMove};
    }
}
