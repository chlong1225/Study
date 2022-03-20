package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/20.
 * description : UTF-8编码验证
 *
 * 给定一个表示数据的整数数组data，返回它是否为有效的UTF-8编码。
 * UTF-8中的一个字符可能的长度为1到4字节，遵循以下的规则：
 * 对于1字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于n字节的字符(n > 1)，第一个字节的前n位都设为1，第n+1位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * 这是UTF-8编码的工作方式：
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 *
 * 示例 1：
 * 输入：data = [197,130,1]
 * 输出：true
 * 解释：数据表示字节序列:11000101 10000010 00000001。
 * 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
 *
 * 示例 2：
 * 输入：data = [235,140,4]
 * 输出：false
 * 解释：数据表示 8 位的序列: 11101011 10001100 00000100.
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 *
 * 提示:
 * 1 <= data.length <= 2 * 10^4
 * 0 <= data[i] <= 255
 */
public class ValibUtf8 {

    public boolean validUtf8(int[] data) {
        int n = data.length;
        int start = 0;
        while (start < n) {
            int count = findCount(data[start]);
            if (count == -1) {
                return false;
            }
            if (count == 1) {
                //1个字节的直接检查下一个
                start++;
            } else {
                if (start + count > n) {
                    //字节不够
                    return false;
                }
                for (int i = start + 1; i < start + count; i++) {
                    //判断字节是否有效
                    if (data[i] < 128 || data[i] >= 192) {
                        return false;
                    }
                }
                start += count;
            }

        }
        return true;
    }

    //判断当前数字代表几个字节
    private int findCount(int num) {
        if (num < 128) {
            return 1;
        }
        if (num >= 192 && num < 224) {
            return 2;
        }
        if (num >= 224 && num < 240) {
            return 3;
        }
        if (num >= 240 && num < 248) {
            return 4;
        }
        return -1;
    }
}
