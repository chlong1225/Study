package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * create on 2023/2/9
 * @author chenglong
 * description : 设计一个验证系统
 *
 * 你需要设计一个包含验证码的验证系统。每一次验证中，用户会收到一个新的验证码，这个验证码在currentTime时刻之后timeToLive秒过期。如果验证码被更新了，
 * 那么它会在currentTime（可能与之前的currentTime不同）时刻延长timeToLive秒。
 * 请你实现AuthenticationManager类：
 * AuthenticationManager(int timeToLive)构造AuthenticationManager并设置timeToLive参数。
 * generate(string tokenId, int currentTime)给定tokenId，在当前时间currentTime生成一个新的验证码。
 * renew(string tokenId, int currentTime)将给定tokenId且未过期的验证码在currentTime时刻更新。如果给定tokenId对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。
 * countUnexpiredTokens(int currentTime)请返回在给定currentTime时刻，未过期的验证码数目。
 * 如果一个验证码在时刻t过期，且另一个操作恰好在时刻t发生（renew或者countUnexpiredTokens操作），过期事件优先于其他操作。
 *
 * 示例 1：
 * 输入：
 * ["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", "generate", "renew", "renew", "countUnexpiredTokens"]
 * [[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
 * 输出：
 * [null, null, null, 1, null, null, null, 0]
 * 解释：
 * AuthenticationManager authenticationManager = new AuthenticationManager(5); // 构造 AuthenticationManager ，设置 timeToLive = 5 秒。
 * authenticationManager.renew("aaa", 1); // 时刻 1 时，没有验证码的 tokenId 为 "aaa" ，没有验证码被更新。
 * authenticationManager.generate("aaa", 2); // 时刻 2 时，生成一个 tokenId 为 "aaa" 的新验证码。
 * authenticationManager.countUnexpiredTokens(6); // 时刻 6 时，只有 tokenId 为 "aaa" 的验证码未过期，所以返回 1 。
 * authenticationManager.generate("bbb", 7); // 时刻 7 时，生成一个 tokenId 为 "bbb" 的新验证码。
 * authenticationManager.renew("aaa", 8); // tokenId 为 "aaa" 的验证码在时刻 7 过期，且 8 >= 7 ，所以时刻 8 的renew 操作被忽略，没有验证码被更新。
 * authenticationManager.renew("bbb", 10); // tokenId 为 "bbb" 的验证码在时刻 10 没有过期，所以 renew 操作会执行，该 token 将在时刻 15 过期。
 * authenticationManager.countUnexpiredTokens(15); // tokenId 为 "bbb" 的验证码在时刻 15 过期，tokenId 为 "aaa" 的验证码在时刻 7 过期，所有验证码均已过期，所以返回 0 。
 *
 * 提示：
 * 1 <= timeToLive <= 10^8
 * 1 <= currentTime <= 10^8
 * 1 <= tokenId.length <= 5
 * tokenId只包含小写英文字母。
 * 所有generate函数的调用都会包含独一无二的tokenId值。
 * 所有函数调用中，currentTime的值严格递增。
 * 所有函数的调用次数总共不超过2000次。
 */
public class AuthenticationManager {

    private Map<String, Integer> marks = new HashMap<>();
    private int mTimeToLive;

    public AuthenticationManager(int timeToLive) {
        mTimeToLive = timeToLive;
        marks.clear();
    }

    public void generate(String tokenId, int currentTime) {
        marks.put(tokenId, currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        if (marks.containsKey(tokenId)) {
            int pre = marks.get(tokenId);
            if (pre + mTimeToLive > currentTime) {
                //此时未过期
                marks.put(tokenId, currentTime);
            }
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for (String tokenId : marks.keySet()) {
            int cur = marks.get(tokenId);
            if (cur + mTimeToLive > currentTime) {
                count++;
            }
        }
        return count;
    }
}
