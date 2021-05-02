package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/2.
 */
public interface Queue {

    /**
     * 入队操作
     * @param data :
     */
    void enqueue(String data);

    /**
     * 出队操作
     * @return :
     */
    String dequeue();

    /**
     * 队列数据长度
     * @return :
     */
    int size();
}
