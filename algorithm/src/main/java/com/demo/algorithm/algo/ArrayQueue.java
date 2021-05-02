package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/2.
 * <p>
 * 数组实现的顺序队列
 */
public class ArrayQueue implements Queue {

    //默认大小
    private static final int DEFAULT_CAPACITY = 10;
    //默认扩容因子
    private static final float CAPACITY_FACTOR = 1.5f;
    //栈的容量大小
    private int capacity = DEFAULT_CAPACITY;
    //栈中的元素
    private String[] itmes;
    private int head;
    private int tail;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        head = 0;
        tail = 0;
        itmes = new String[capacity];
    }

    @Override
    public void enqueue(String data) {
        if (tail == capacity) {
            if (size() >= capacity) {
                //扩容
                expansion();
            } else {
                //整体搬移
                move();
            }
        }
        itmes[tail] = data;
        tail++;
    }

    //数组迁移
    private void move() {
        for (int i = head; i < itmes.length; i++) {
            itmes[i - head] = itmes[i];
        }
        tail -= head;
        head = 0;
    }

    //数组扩容
    private void expansion() {
        capacity = (int) (capacity * CAPACITY_FACTOR);
        String[] result = new String[capacity];
        for (int i = head; i < itmes.length; i++) {
            result[i - head] = itmes[i];
        }
        head = 0;
        tail = itmes.length;
        itmes = result;
    }

    @Override
    public String dequeue() {
        if (head == tail) {
            return "";
        }
        String itme = itmes[head];
        head++;
        return itme;
    }

    @Override
    public int size() {
        return tail - head;
    }
}
