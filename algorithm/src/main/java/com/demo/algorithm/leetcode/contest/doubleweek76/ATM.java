package com.demo.algorithm.leetcode.contest.doubleweek76;

/**
 * Created by chl on 2022/4/19.
 * description : 设计一个ATM机器
 *
 * 一个ATM机器，存有5种面值的钞票：20，50，100，200和500美元。初始时ATM机是空的。用户可以用它存或者取任意数目的钱。
 * 取款时，机器会优先取较大数额的钱。
 * 比方说，你想取$300，并且机器里有2张 $50的钞票，1张$100的钞票和1张$200的钞票，那么机器会取出$100和$200的钞票。
 * 但是，如果你想取$600，机器里有3张$200的钞票和1张$500的钞票，那么取款请求会被拒绝，因为机器会先取出$500的钞票，
 * 然后无法取出剩余的$100。注意，因为有$500钞票的存在，机器不能取$200的钞票。
 *
 * 请你实现 ATM 类：
 * ATM()初始化ATM对象。
 * void deposit(int[] banknotesCount)分别存入$20，$50，$100，$200和$500钞票的数目。
 * int[] withdraw(int amount)返回一个长度为5的数组，分别表示$20，$50，$100，$200和$500钞票的数目，并且更新 ATM 机里取款后钞票的剩余数量。如果无法取出指定数额的钱，请返回[-1]（这种情况下 不取出任何钞票）。
 *
 * 示例 1：
 * 输入：
 * ["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
 * [[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
 * 输出：
 * [null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]
 * 解释：
 * ATM atm = new ATM();
 * atm.deposit([0,0,1,2,1]); // 存入 1 张 $100 ，2 张 $200 和 1 张 $500 的钞票。
 * atm.withdraw(600);        // 返回 [0,0,1,0,1] 。机器返回 1 张 $100 和 1 张 $500 的钞票。机器里剩余钞票的数量为 [0,0,0,2,0] 。
 * atm.deposit([0,1,0,1,1]); // 存入 1 张 $50 ，1 张 $200 和 1 张 $500 的钞票。
 *                           // 机器中剩余钞票数量为 [0,1,0,3,1] 。
 * atm.withdraw(600);        // 返回 [-1] 。机器会尝试取出 $500 的钞票，然后无法得到剩余的 $100 ，所以取款请求会被拒绝。
 *                           // 由于请求被拒绝，机器中钞票的数量不会发生改变。
 * atm.withdraw(550);        // 返回 [0,1,0,0,1] ，机器会返回 1 张 $50 的钞票和 1 张 $500 的钞票。
 *
 * 提示：
 * banknotesCount.length == 5
 * 0 <= banknotesCount[i] <= 10^9
 * 1 <= amount <= 10^9
 * 总共最多有5000次withdraw 和deposit的调用。
 * 函数withdraw 和deposit至少各有 一次调用。
 */
public class ATM {

    private long[] dates;

    public ATM() {
        dates = new long[5];
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            dates[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] counts = new int[5];
        if (amount >= 500 && dates[4] > 0) {
            //优先取500
            int tem = (int) Math.min(dates[4], amount / 500);
            amount -= (tem * 500);
            counts[4] = tem;
        }
        if (amount >= 200 && dates[3] > 0) {
            int tem = (int) Math.min(dates[3], amount / 200);
            amount -= (tem * 200);
            counts[3] = tem;
        }
        if (amount >= 100 && dates[2] > 0) {
            int tem = (int) Math.min(dates[2], amount / 100);
            amount -= (tem * 100);
            counts[2] = tem;
        }
        if (amount >= 50 && dates[1] > 0) {
            int tem = (int) Math.min(dates[1], amount / 50);
            amount -= (tem * 50);
            counts[1] = tem;
        }
        if (amount >= 20 && dates[0] > 0) {
            int tem = (int) Math.min(dates[0], amount / 20);
            amount -= (tem * 20);
            counts[0] = tem;
        }
        if (amount == 0) {
            for (int i = 0; i < 5; i++) {
                dates[i] -= counts[i];
            }
            return counts;
        } else {
            return new int[]{-1};
        }
    }
}
