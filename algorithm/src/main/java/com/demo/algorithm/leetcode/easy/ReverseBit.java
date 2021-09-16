package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 9/15/21
 * description : 颠倒二进制位
 *
 *  颠倒给定的 32 位无符号整数的二进制位。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *  
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 * 示例 1：
 * 输入：n = 00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 *
 * 示例 2：
 * 输入：n = 11111111111111111111111111111101
 * 输出：3221225471 (10111111111111111111111111111111)
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 *  
 * 提示：
 * 输入是一个长度为 32 的二进制字符串
 */
public class ReverseBit {

    public static int reverseBits(int n) {
        if (n == 0) {
            return 0;
        }
        //1,将n转换为32位的byte数组
        byte[] datas = new byte[32];
        int index = datas.length - 1;
        if (n > 0) {
            while (n / 2 != 0) {
                datas[index] = (byte) (n % 2);
                index--;
                n /= 2;
            }
            datas[index] = (byte) n;
        } else {
            int tem = Math.abs(n) - 1;
            while (tem / 2 != 0) {
                datas[index] = (byte) (tem % 2);
                index--;
                tem /= 2;
            }
            datas[index] = (byte) tem;
            for (int i = 0; i < 32; i++) {
                datas[i] = (byte) (datas[i] == 1 ? 0 : 1);
            }
        }
        //2，将byte数组转换位int数据
        int result = 0;
        if (datas[31] == 0) {
            //正数
            for (int i = 30; i >= 0; i--) {
                result = result * 2 + datas[i];
            }
        } else {
            //负数,反转并取补码
            for (int i = 0; i < 16; i++) {
                byte b = (byte) (datas[i] == 1 ? 0 : 1);
                datas[i] = (byte) (datas[31 - i] == 1 ? 0 : 1);
                datas[31 - i] = b;
            }
            for (int i = 0; i < 32; i++) {
                result = result * 2 + datas[i];
            }
            //负数的反码+1
            result = (-result - 1);
        }
        return result;
    }
}
