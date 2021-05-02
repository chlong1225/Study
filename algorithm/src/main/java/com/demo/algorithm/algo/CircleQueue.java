package com.demo.algorithm.algo;

/**
 * Created by w on 2021/5/2.
 *
 * 循环队列
 *
 */
public class CircleQueue implements Queue{

    //栈中的元素
    private String[] itmes;
    private int head;
    private int tail;

    @Override
    public void enqueue(String data) {
        if ((tail + 1) % itmes.length == head) {
            return;
        }
        itmes[tail] = data;
        tail++;
        tail %= itmes.length;
    }

    @Override
    public String dequeue() {
        if (head == tail) {
            return "";
        }
        String itme = itmes[head % itmes.length];
        head++;
        head %= itmes.length;
        return itme;
    }

    @Override
    public int size() {
        if (tail > head) {
            return tail - head;
        } else {
            return tail - head + itmes.length;
        }
    }
}
