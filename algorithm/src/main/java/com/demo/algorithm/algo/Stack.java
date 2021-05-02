package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/1.
 *
 * 定义栈的操作方法,默认存储string类型
 */
public interface Stack {

    /**
     * 入栈操作
     * @param data :
     */
    void push(String data);

    /**
     * 出栈操作
     * @return :
     */
    String pop();


    /**
     * 栈内元素数量
     * @return :
     */
    int size();
}
