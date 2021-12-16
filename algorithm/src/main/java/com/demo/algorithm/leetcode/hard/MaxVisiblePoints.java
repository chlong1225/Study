package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chl on 2021/12/16.
 * description : 可见点的最大数目
 *
 * 给你一个点数组points和一个表示角度的整数angle ，你的位置是location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
 * 最开始，你面向东方进行观测。你不能进行移动改变位置，但可以通过自转调整观测角度。换句话说，posx和posy不能改变。你的视野范围的角度用angle表示，这决定了你观测任意方向时可以多宽。设 d为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
 * 对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
 * 同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
 * 返回你能看到的点的最大数目。
 *
 * 示例 1：
 * 输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * 输出：3
 * 解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。
 *
 * 示例 2：
 * 输入：points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * 输出：4
 * 解释：在你的视野中，所有的点都清晰可见，包括你所在位置的那个点。
 *
 * 示例 3：
 * 输入：points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * 输出：1
 * 解释：如图所示，你只能看到两点之一。
 *  
 * 提示：
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 100
 */
public class MaxVisiblePoints {

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        //1,转换为相对location的极坐标,然后获取角度
        int size = points.size();
        //存放point相对位置的角度，范围0~360对应0~2*Math.PI
        double[] angles = new double[size];
        //相同的数量
        int sameCount = 0;
        for (int i = 0; i < size; i++) {
            int dx = points.get(i).get(0) - location.get(0);
            int dy = points.get(i).get(1) - location.get(1);
            if (dx == 0 && dy == 0) {
                //点与当前位置重合
                sameCount++;
                //角度为0有两种情况.点重合+x轴右边.这里使用0便于排序
                angles[i] = 0;
                continue;
            }
            if (dx == 0) {
                //点在y轴方向
                if (dy > 0) {
                    //90度的方向
                    angles[i] = Math.PI / 2;
                } else {
                   //270度方向
                    angles[i] = Math.PI / 2 + Math.PI;
                }
            } else if (dy == 0) {
                //点在x轴方向
                if (dx > 0) {
                    //0度的方向
                    angles[i] = 0;
                } else {
                    //180度的方向
                    angles[i] = Math.PI;
                }
            } else if (dx > 0) {
                if (dy > 0) {
                    //在第一象限0~90度范围
                    angles[i] = Math.atan2(dy, dx);
                } else {
                    //在第四象限270~360度范围
                    angles[i] = 2 * Math.PI - Math.atan2(-dy, dx);
                }
            } else {
                if (dy > 0) {
                    //在第二象限90~180度范围
                    angles[i] = Math.PI - Math.atan2(dy, -dx);
                } else {
                    //在第三象限180~270度范围
                    angles[i] = Math.PI + Math.atan2(-dy, -dx);
                }
            }
        }
        //所有的点击都重合
        if (sameCount == size) {
            return sameCount;
        }
        //2,对角度数组进行排序
        Arrays.sort(angles);
        double space = angle * Math.PI / 180;
        int count = size - sameCount;
        //3,过滤掉重合的位置,然后重新构建数据
        int length = 2 * count;
        double[] datas = new double[length];
        for (int i = 0; i < length; i++) {
            if (i < count) {
                datas[i] = angles[sameCount + i];
            } else {
                //起始位置角度为0~space。扫描一圈后结束的边界会超过360。数据需要再加一圈，但起始边界不能超过360
                datas[i] = angles[sameCount + i - count] + 2 * Math.PI;
            }
        }
        //4,使用滑动窗口的方法遍历获取最大值
        int max = 0;
        int startIndex = 0;
        count = 1;
        out: for (int i = 1; i < length; i++) {
            if (datas[startIndex] + space >= datas[i]) {
                count++;
            } else {
                //此时超过了start+space的范围，记录一下最大数目
                if (max < count) {
                    max = count;
                }
                startIndex++;
                //用于校验起始边界不能超过360度
                if (startIndex >= (length >> 1)) {
                    break;
                }
                while (datas[startIndex] + space < datas[i]) {
                    startIndex++;
                    count--;
                    if (startIndex >= (length >> 1)) {
                        break out;
                    }
                }
            }
        }
        if (count > max) {
            max = count;
        }
        //5,最终值 = 最大值 + 重合数量
        return sameCount + max;
    }
}
