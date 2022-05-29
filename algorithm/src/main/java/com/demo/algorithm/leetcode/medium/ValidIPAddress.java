package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/5/29.
 * description :  验证IP地址
 *
 * 给定一个字符串queryIP。如果是有效的IPv4地址，返回"IPv4"；如果是有效的IPv6地址，返回"IPv6"；如果不是上述类型的IP地址，返回"Neither" 。
 * 有效的IPv4地址是 “x1.x2.x3.x4”形式的IP地址。其中0 <= xi<= 255且xi不能包含前导零。
 * 例如:“192.168.1.1”、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 * 一个有效的IPv6地址是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * 1 <= xi.length <= 4
 * xi是一个十六进制字符串，可以包含数字、小写英文字母('a'到'f')和大写英文字母('A'到'F')。
 * 在xi中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 * 示例 1：
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 *
 * 示例 2：
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 *
 * 示例 3：
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 *
 * 提示：
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 */
public class ValidIPAddress {

   private static final String IP_V4 = "IPv4";
   private static final String IP_V6 = "IPv6";
   private static final String NEITHER = "Neither";

   public String validIPAddress(String queryIP) {
      if (queryIP == null || queryIP.length() == 0) {
         return NEITHER;
      }
      char first = queryIP.charAt(0);
      if (first == '.' || first == ':') {
         return NEITHER;
      }
      char last = queryIP.charAt(queryIP.length() - 1);
      if (last == '.' || last == ':') {
         return NEITHER;
      }
      String[] split = queryIP.split("\\.");
      if (split.length == 4) {
         if (isIpv4(split)) {
            return IP_V4;
         }
      } else {
         split = queryIP.split(":");
         if (split.length == 8) {
            if (isIpv6(split)) {
               return IP_V6;
            }
         }
      }
      return NEITHER;
   }

   private boolean isIpv6(String[] split) {
      for (int i = 0; i < split.length; i++) {
         String str = split[i];
         if (str == null || str.length() == 0 || str.length() > 4) {
            return false;
         }
         int length = str.length();
         for (int j = 0; j < length; j++) {
            if (!isCharValid(str.charAt(j))) {
               return false;
            }
         }
      }
      return true;
   }

   private boolean isCharValid(char c) {
      if (c >= '0' && c <= '9') {
         return true;
      }
      if (c >= 'a' && c <= 'f') {
         return true;
      }
      if (c >= 'A' && c <= 'F') {
         return true;
      }
      return false;
   }

   private boolean isIpv4(String[] split) {
      for (int i = 0; i < split.length; i++) {
         String str = split[i];
         if (str == null || str.length() == 0 || str.length() > 3) {
            return false;
         }
         int length = str.length();
         if (length > 1 && str.charAt(0) == '0') {
            //有前导的0
            return false;
         }
         int num = 0;
         for (int j = 0; j < length; j++) {
            if (str.charAt(j) > '9' || str.charAt(j) < '0') {
               //存在非0~9的字符
               return false;
            } else {
               num = num * 10 + (str.charAt(j) - '0');
            }
         }
         if (num > 255) {
            return false;
         }
      }
      return true;
   }
}
