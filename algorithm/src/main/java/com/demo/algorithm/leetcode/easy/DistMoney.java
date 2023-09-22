package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/9/22
 * @author chenglong
 * description : 将钱分给最多的儿童
 *
 * 给你一个整数money，表示你总共有的钱数（单位为美元）和另一个整数children，表示你要将钱分配给多少个儿童。
 * 你需要按照如下规则分配：
 * 所有的钱都必须被分配。
 * 每个儿童至少获得1美元。
 * 没有人获得4美元。
 * 请你按照上述规则分配金钱，并返回最多有多少个儿童获得恰好8美元。如果没有任何分配方案，返回-1。
 *
 * 示例 1：
 * 输入：money = 20, children = 3
 * 输出：1
 * 解释：
 * 最多获得 8 美元的儿童数为 1 。一种分配方案为：
 * - 给第一个儿童分配 8 美元。
 * - 给第二个儿童分配 9 美元。
 * - 给第三个儿童分配 3 美元。
 * 没有分配方案能让获得 8 美元的儿童数超过 1 。
 *
 * 示例 2：
 * 输入：money = 16, children = 2
 * 输出：2
 * 解释：每个儿童都可以获得 8 美元。
 *
 * 提示：
 * 1 <= money <= 200
 * 2 <= children <= 30
 */
public class DistMoney {

    public int distMoney(int money, int children) {
        //1，判断是否能够至少分配1美元
        if (money < children) {
            return -1;
        }
        //先统一分配1美元。此时判断条件改为没人获取3美元。统计获取7美元的人数
        money -= children;
        int count = 0;
        while (money >= 7) {
            if (count < children) {
                count++;
                money -= 7;
            } else if (count == children) {
                //此时需要将剩余的金额给予最后一个儿童
                count--;
                break;
            }
        }
        //剩余金额money
        if (money > 0) {
            if (count == children) {
                count--;
            } else {
                //此时剩余money给剩余的人
                if (money == 3) {
                    if (children - count == 1) {
                        count--;
                    }
                }
            }
        }
        return count;
    }
}
