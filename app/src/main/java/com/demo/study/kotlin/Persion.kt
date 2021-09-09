package com.demo.study.kotlin

/**
 * Created by chl on 2021/8/23.
 */
data class Persion(val name: String) {

    var age: Int = 0

    override fun equals(other: Any?): Boolean {
        if (other is Persion) {
            return name == other.name && age == other.age
        }
        return super.equals(other)
    }

    override fun toString(): String {
        return "Persion(name = $name , age = $age)"
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }
}