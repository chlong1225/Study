package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/5/24
 * @author chenglong
 * description : 我能赢吗
 *
 * 在"100 game" 这个游戏中，两名玩家轮流选择从1到10的任意整数，累计整数和，先使得累计整数和达到或超过100的玩家，即为胜者。
 * 如果我们将游戏规则改为“玩家不能重复使用整数”呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从1到15的整数（不放回），直到累计整数和>= 100。
 * 给定两个整数maxChoosableInteger（整数池中可选择的最大数）和desiredTotal（累计和），若先出手的玩家是否能稳赢则返回true，否则返回false 。假设两位玩家游戏时都表现最佳 。
 *
 * 示例 1：
 * 输入：maxChoosableInteger = 10, desiredTotal = 11
 * 输出：false
 * 解释：
 * 无论第一个玩家选择哪个整数，他都会失败。
 * 第一个玩家可以选择从 1 到 10 的整数。
 * 如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
 * 第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
 * 同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
 *
 * 示例 2:
 * 输入：maxChoosableInteger = 10, desiredTotal = 0
 * 输出：true
 *
 * 示例 3:
 * 输入：maxChoosableInteger = 10, desiredTotal = 1
 * 输出：true
 *
 * 提示:
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 */
public class CanIWin {

    private static final int UN_KNOW = 0;
    private static final int FIST_WIN = 1;
    private static final int SECOND_WIN = 2;

    private int max;
    private final Map<Integer, Integer> marks = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //1，统计所有的和，判断特殊场景
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            //此时两个玩家都无法通过选择数字获胜，即先手玩家不能赢
            return false;
        }
        if (sum == desiredTotal) {
            //最后一次选择满足累计和，当前选择的玩家获胜。奇数次数时，先手玩家才能最后一次选择。
            return maxChoosableInteger % 2 == 1;
        }
        max = maxChoosableInteger;
        marks.clear();
        //2，遍历所有的场景获取结果。使用位的0，1区分是否选中
        int result = dfs(1, 0, 0, desiredTotal);
        return result == FIST_WIN;
    }

    private int dfs(int step, int select, int sum, int desiredTotal) {
        if (marks.containsKey(select)) {
            return marks.get(select);
        }
        //当前选择的是否为先手用户
        boolean isFirst = false;
        if (step % 2 == 1) {
            isFirst = true;
        }
        int ans = isFirst ? SECOND_WIN : FIST_WIN;
        for (int i = 1; i <= max; i++) {
            int tem = 1 << (i - 1);
            if ((select & tem) == 0) {
                //第i位置的数未选中，计算选中后新的数字
                int num = (select | tem);
                int total = sum + i;
                if (total >= desiredTotal) {
                    ans = isFirst ? FIST_WIN : SECOND_WIN;
                    marks.put(select, ans);
                    return ans;
                }
                int result = dfs(step + 1, num, total, desiredTotal);
                if (result == (isFirst ? FIST_WIN : SECOND_WIN)) {
                    marks.put(select, result);
                    return result;
                }
                if (result == UN_KNOW) {
                    ans = UN_KNOW;
                }
            }
        }
        marks.put(select, ans);
        return ans;
    }

    private int maxNun;
    private int compare;
    private int[] mark;

    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        /**
         * 当前玩家赢了标记为1，输了标记为-1。默认为0
         */
        //1，处理特殊场景
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        //统计所有的数字和
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            return false;
        }
        if (sum == desiredTotal) {
            return maxChoosableInteger % 2 == 1;
        }
        //2，遍历所有的场景
        maxNun = maxChoosableInteger;
        compare = desiredTotal;
        int length = 1 << maxChoosableInteger;
        mark = new int[length];
        int state = dfs(0, 0);
        return state == 1;
    }

    private int dfs(int select, int total) {
        if (mark[select] != 0) {
            return mark[select];
        }
        for (int i = 0; i < maxNun; i++) {
            int cur = 1 << i;
            if ((select & cur) == 0) {
                //当前位置未被选中
                int sum = total + i + 1;
                if (sum >= compare) {
                    mark[select] = 1;
                    return 1;
                }
                int next = select | cur;
                if (dfs(next, sum) == -1) {
                    mark[select] = 1;
                    return 1;
                }
            }
        }
        mark[select] = -1;
        return -1;
    }
}
