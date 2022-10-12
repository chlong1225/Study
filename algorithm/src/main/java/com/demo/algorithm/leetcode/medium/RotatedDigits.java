package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/9/25
 * @author chenglong
 * description : 旋转数字
 *
 * 我们称一个数X为好数, 如果它的每位数字逐个地被旋转180度后，我们仍可以得到一个有效的，且和X不同的数。要求每位数字都要被旋转。
 * 如果一个数的每位数字被旋转以后仍然还是一个数字，则这个数是有效的。0, 1, 和8被旋转后仍然是它们自己；2和5可以互相旋转成对方
 * （在这种情况下，它们以不同的方向旋转，换句话说，2和5互为镜像）；6和9同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 * 现在我们有一个正整数N, 计算从1到N中有多少个数X是好数？
 *
 * 示例：
 * 输入: 10
 * 输出: 4
 * 解释:
 * 在[1, 10]中有四个好数： 2, 5, 6, 9。
 * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
 *
 * 提示：
 * N的取值范围是[1, 10000]。
 */
public class RotatedDigits {

    private static int[] dates = new int[10001];

    static {
        /**
         * 分析：将0～9分成3类
         * 0，1，8：有效数字，但不能全部是这三个，否则旋转后还是自身
         * 2，5，6，9：有效数字，只需要有一个就可以保证旋转后不是本身，对应好数
         * 3，4，7：无效数字，不能出现，否则无法旋转
         */
        dates[1] = 0;
        for (int i = 2; i < 10001; i++) {
            if (checkNum(i)) {
                dates[i] = dates[i - 1] + 1;
            } else {
                dates[i] = dates[i - 1];
            }
        }
    }

    private static boolean checkNum(int num) {
        int count = 0;
        while (num > 0) {
            int last = num % 10;
            if (last == 2 || last == 5 || last == 6 || last == 9) {
                count++;
            } else if (last == 3 || last == 4 || last == 7) {
                return false;
            }
            num /= 10;
        }
        return count > 0;
    }

    public int rotatedDigits(int n) {
        return dates[n];
    }
}
