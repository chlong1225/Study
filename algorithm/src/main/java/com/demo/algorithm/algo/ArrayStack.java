package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/1.
 *
 * 使用数组实现栈,默认使用string类型
 *
 */
public class ArrayStack implements Stack{

    //默认大小
    private static final int DEFAULT_CAPACITY = 10;
    //默认扩容因子
    private static final float CAPACITY_FACTOR = 1.5f;

    //栈中元素数量
    private int count;
    //栈的容量大小
    private int capacity = DEFAULT_CAPACITY;
    //栈中的元素
    private String[] itmes;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 初始化指定大小的栈
     * @param capacity :
     */
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        itmes = new String[capacity];
        count = 0;
    }

    /**
     * 入栈操作
     */
    @Override
    public void push(String data) {
        if (count == capacity) {
            expansion();
        }
        itmes[count] = data;
        count++;
    }

    //数组扩容
    private void expansion() {
        capacity = (int) (capacity * CAPACITY_FACTOR);
        String[] result = new String[capacity];
        for (int i = 0; i < itmes.length; i++) {
            result[i] = itmes[i];
        }
        itmes = result;
    }

    /**
     * 出栈操作
     * @return :
     */
    @Override
    public String pop() {
        if (count == 0) {
            return "";
        }
        String itme = itmes[count - 1];
        count--;
        return itme;
    }

    @Override
    public int size() {
        return count;
    }
}
