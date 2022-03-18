package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/18.
 * description : 简易银行系统
 *
 * 你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。
 * 银行共有n个账户，编号从1到n。每个账号的初始余额存储在一个下标从0开始的整数数组balance中，其中第(i + 1)个账户的初始余额是balance[i] 。
 * 请你执行所有有效的交易。如果满足下面全部条件，则交易有效 ：
 * 指定的账户数量在1和n之间，且
 * 取款或者转账需要的钱的总数小于或者等于账户余额。
 * 实现Bank类：
 * Bank(long[] balance) 使用下标从0开始的整数数组balance初始化该对象。
 * boolean transfer(int account1, int account2, long money)从编号为account1的账户向编号为 account2的账户转帐money美元。如果交易成功，返回true，否则，返回false 。
 * boolean deposit(int account, long money) 向编号为account的账户存款money美元。如果交易成功，返回true ；否则返回false 。
 * boolean withdraw(int account, long money)从编号为account的账户取款money美元。如果交易成功，返回true ；否则返回false 。
 *
 * 示例：
 * 输入：
 * ["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
 * [[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
 * 输出：
 * [null, true, true, true, false, false]
 *
 * 解释：
 * Bank bank = new Bank([10, 100, 20, 50, 30]);
 * bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
 *                          // 账户 3 余额为 $20 - $10 = $10 。
 * bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
 *                          // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
 * bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
 *                          // 账户 5 的余额为 $10 + $20 = $30 。
 * bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
 *                          // 所以无法转账 $15 。
 * bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
 *
 * 提示：
 * n == balance.length
 * 1 <= n, account, account1, account2 <= 10^5
 * 0 <= balance[i], money <= 10^12
 * transfer, deposit, withdraw三个函数，每个最多调用10^4 次
 */
public class Bank {

    private int n;
    private long[] monkeys;

    public Bank(long[] balance) {
        monkeys = balance;
        n = monkeys.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > n || account2 > n) {
            return false;
        }
        if (monkeys[account1 - 1] < money) {
            //钱不够转账
            return false;
        }
        monkeys[account1 - 1] -= money;
        monkeys[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > n) {
            return false;
        }
        monkeys[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > n) {
            return false;
        }
        if (monkeys[account - 1] < money) {
            return false;
        }
        monkeys[account - 1] -= money;
        return true;
    }
}
