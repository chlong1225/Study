package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/3/21
 * @author chenglong
 * description : 分割两个字符串得到回文串
 *
 * 给你两个字符串a和b，它们长度相同。请你选择一个下标，将两个字符串都在相同的下标分割开。由a可以得到两个字符串：aprefix和asuffix，满足a = aprefix + asuffix，
 * 同理，由b可以得到两个字符串bprefix和bsuffix，满足b = bprefix + bsuffix。请你判断aprefix + bsuffix 或者bprefix + asuffix能否构成回文串。
 * 当你将一个字符串s分割成sprefix和ssuffix时，ssuffix或者sprefix可以为空。比方说，s="abc"那么"" + "abc"，"a" + "bc"，"ab" + "c"和"abc" + ""都是合法分割。
 * 如果能构成回文字符串，那么请返回true，否则返回false。
 * 注意，x + y表示连接字符串x和y。
 *
 * 示例 1：
 * 输入：a = "x", b = "y"
 * 输出：true
 * 解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * 那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。
 *
 * 示例 2：
 * 输入：a = "abdef", b = "fecab"
 * 输出：true
 *
 * 示例 3：
 * 输入：a = "ulacfd", b = "jizalu"
 * 输出：true
 * 解释：在下标为 3 处分割：
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
 *
 * 提示：
 * 1 <= a.length, b.length <= 10^5
 * a.length == b.length
 * a和b都只包含小写英文字母
 */
public class CheckPalindromeFormation {

    public boolean checkPalindromeFormation(String a, String b) {
        int length = a.length();
        //1，判断a，b自身是否为回文
        if (length == 1) {
            return true;
        }
        boolean palindrome1 = isPalindrome(a);
        if (palindrome1) {
            return true;
        }
        boolean palindrome2 = isPalindrome(b);
        if (palindrome2) {
            return true;
        }
        //2，判断a+b或b+a是否可以构建长度为length的回文
        return isPalindromeWithLength(a + b, length) || isPalindromeWithLength(b + a, length);
    }

    //字符串前后取长度size构建回文
    private boolean isPalindromeWithLength(String s, int count) {
        int length = s.length();
        int left = 0;
        int right = length - 1;
        while (count > 0 && left < length) {
            if (count == 1) {
                return true;
            }
            if (s.charAt(left) == s.charAt(right)) {
                count -= 2;
                left++;
                right--;
            } else {
                if (isPalindrome(s.substring(left, left + count))) {
                    return true;
                }
                if (isPalindrome(s.substring(right + 1 - count, right + 1))) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    //判断单个字符串是否为回文
    private boolean isPalindrome(String s) {
        int length = s.length();
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
