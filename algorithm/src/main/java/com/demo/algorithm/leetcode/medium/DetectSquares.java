package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 2022/1/26
 * @author chenglong
 * description : 检测正方形
 *
 * 给你一个在X-Y平面上的点构成的数据流。设计一个满足下述要求的算法：
 * 添加一个在数据流中的新点到某个数据结构中。可以添加重复的点，并会视作不同的点进行处理。
 * 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个面积为正的轴对齐正方形，统计满足该要求的方案数目。
 * 轴对齐正方形是一个正方形，除四条边长度相同外，还满足每条边都与x-轴或y-轴平行或垂直。
 * 实现 DetectSquares 类：
 * DetectSquares() 使用空数据结构初始化对象
 * void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 * int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
 *
 * 示例：
 * 输入：
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * 输出：
 * [null, null, null, null, 1, 0, null, 2]
 *
 * 解释：
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // 返回 1 。你可以选择：第一个，第二个，和第三个点
 * detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
 * detectSquares.add([11, 2]);    // 允许添加重复的点。
 * detectSquares.count([11, 10]); // 返回 2 。你可以选择：第一个，第二个，和第三个点；第一个，第三个，和第四个点
 *
 * 提示：
 * point.length == 2
 * 0 <= x, y <= 1000
 * 调用add和count的总次数最多为 5000
 */
public class DetectSquares {

    private static final int MOD = 1001;

    //统计点point的数量。key = x*1001+y
    private Map<Integer, Integer> counts = new HashMap<>();
    //统计x坐标对应的所有y坐标，对应平行于y轴
    private Map<Integer, List<Integer>> mLineY = new HashMap<>();
    //统计y坐标对应的所有x坐标，对应平行与x轴
    private Map<Integer, List<Integer>> mLineX = new HashMap<>();

    public DetectSquares() {
        counts.clear();
        mLineX.clear();
        mLineY.clear();
    }

    public void add(int[] point) {
        int tem = point[0] * MOD + point[1];
        if (counts.get(tem) == null) {
            //当前点没有被统计过，更新平行x轴，y轴线段的数据
            counts.put(tem, 1);
            //更新平行y轴的数据
            if (mLineY.get(point[0]) == null) {
                List<Integer> datas = new ArrayList<>();
                datas.add(point[1]);
                mLineY.put(point[0], datas);
            } else {
                mLineY.get(point[0]).add(point[1]);
            }
            //更新平行x轴的数据
            if (mLineX.get(point[1]) == null) {
                List<Integer> datas = new ArrayList<>();
                datas.add(point[0]);
                mLineX.put(point[1], datas);
            } else {
                mLineX.get(point[1]).add(point[0]);
            }
        } else {
            counts.put(tem, counts.get(tem) + 1);
        }
    }

    public int count(int[] point) {
        int count = 0;
        if (mLineX.get(point[1]) == null || mLineY.get(point[0]) == null) {
            return count;
        }
        //与point的y坐标相同，可以构成平行与x轴的线
        List<Integer> dataX = mLineX.get(point[1]);
        //与point的x坐标相同，可以构成平行与y轴的线
        List<Integer> dataY = mLineY.get(point[0]);
        for (int i = 0; i < dataX.size(); i++) {
            for (int j = 0; j < dataY.size(); j++) {
                //此时三个点分别为：(point[0],point[1]),(dataX.get(i),point[1]),(point[0],dataY.get(j))，此时需要查找最后一个点：(dataX.get(i),dataY.get(j))
                if (dataX.get(i) == point[0] || dataY.get(j) == point[1]) {
                    //三个点存在重合
                    continue;
                }
                //最后需要找到的点
                int find = dataX.get(i) * MOD + dataY.get(j);
                if (counts.get(find) != null) {
                    int point2 = dataX.get(i) * MOD + point[1];
                    int point3 = point[0] * MOD + dataY.get(j);
                    count += (counts.get(find) * counts.get(point2) * counts.get(point3));
                }
            }
        }
        return count;
    }
}
