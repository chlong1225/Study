package com.demo.algorithm.leetcode.contest.week300;

/**
 * Created by chl on 2022/7/3.
 * description : 知道秘密的人数
 *
 * 在第1天，有一个人发现了一个秘密。
 * 给你一个整数delay，表示每个人会在发现秘密后的delay天之后，每天给一个新的人分享秘密。同时给你一个整数forget，表示每个人在发现秘密forget天之后会忘记这个秘密。
 * 一个人不能在忘记秘密那一天及之后的日子里分享秘密。
 * 给你一个整数n，请你返回在第n天结束时，知道秘密的人数。由于答案可能会很大，请你将结果对10^9 + 7取余后返回。
 *
 * 示例 1：
 * 输入：n = 6, delay = 2, forget = 4
 * 输出：5
 * 解释：
 * 第 1 天：假设第一个人叫 A 。（一个人知道秘密）
 * 第 2 天：A 是唯一一个知道秘密的人。（一个人知道秘密）
 * 第 3 天：A 把秘密分享给 B 。（两个人知道秘密）
 * 第 4 天：A 把秘密分享给一个新的人 C 。（三个人知道秘密）
 * 第 5 天：A 忘记了秘密，B 把秘密分享给一个新的人 D 。（三个人知道秘密）
 * 第 6 天：B 把秘密分享给 E，C 把秘密分享给 F 。（五个人知道秘密）
 *
 * 示例 2：
 * 输入：n = 4, delay = 1, forget = 3
 * 输出：6
 * 解释：
 * 第 1 天：第一个知道秘密的人为 A 。（一个人知道秘密）
 * 第 2 天：A 把秘密分享给 B 。（两个人知道秘密）
 * 第 3 天：A 和 B 把秘密分享给 2 个新的人 C 和 D 。（四个人知道秘密）
 * 第 4 天：A 忘记了秘密，B、C、D 分别分享给 3 个新的人。（六个人知道秘密）
 *
 * 提示：
 * 2 <= n <= 1000
 * 1 <= delay < forget <= n
 */
public class PeopleAwareOfSecret {

    private static final int MOD = 1000000007;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int count = 0;
        int[] marks = new int[n];
        marks[0] = 1;
        for (int i = 0; i < n; i++) {
            if (i + delay >= n) {
                count = (count + marks[i]) % MOD;
            }
            for (int j = i + delay; j < Math.min(n, i + forget); j++) {
                marks[j] += marks[i];
                marks[j] %= MOD;
            }
        }
        return (marks[n - 1] + count) % MOD;
    }
}
