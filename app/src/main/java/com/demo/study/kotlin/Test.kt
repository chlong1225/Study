package com.demo.study.kotlin

import com.chl.common.utils.LogUtil

fun Animal.sleep(){
    LogUtil.e("AAAA", "animal : sleep")
}

fun Cat.sleep(){
    LogUtil.e("AAAA", "cat : sleep")
}

/**
 * Created by chl on 2021/9/14.
 * description :
 */
class Test {

    fun call() {
        val animal: Animal = Cat()
        animal.eat()
        animal.sleep()
        Outer().call()

        sleep(Animal())
        sleep(Cat())
    }

    fun sleep(animal: Animal) {
        animal.sleep()
    }
}

class Inner{

    fun print(){
        LogUtil.e("AAAA", "print inner");
    }
}

class Outer{

    fun Inner.printName(){
        print()
        this@Outer.print()
    }

    fun print(){
        LogUtil.e("AAAA", "print outer");
    }

    fun call() {
        Inner().printName()
    }

}


