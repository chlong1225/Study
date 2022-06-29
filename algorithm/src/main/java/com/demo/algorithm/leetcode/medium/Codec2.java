package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/6/29
 * @author chenglong
 * description : TinyURL的加密与解密
 *
 * TinyURL是一种URL简化服务， 比如：当你输入一个URLhttps://leetcode.com/problems/design-tinyurl时，它将返回一个简化的URLhttp://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密TinyURL。
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且这个TinyURL可以用解密方法恢复成原本的URL 。
 *
 * 实现 Solution 类：
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *
 * 示例：
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 *
 * 提示：
 * 1 <= url.length <= 10^4
 * 题目数据保证 url 是一个有效的 URL
 */
public class Codec2 {

    private static final int KEY = 37;

    public String encode(String longUrl) {
        int length = longUrl.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) (longUrl.charAt(i) ^ KEY));
        }
        return builder.toString();
    }

    public String decode(String shortUrl) {
        int length = shortUrl.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) (shortUrl.charAt(i) ^ KEY));
        }
        return builder.toString();
    }
}
