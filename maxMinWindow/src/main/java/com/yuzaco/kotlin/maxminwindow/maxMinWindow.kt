package com.yuzaco.kotlin.maxminwindow
import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
data class Pair(val value :Long, val index:Int)
fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()
    var arr : MutableList<Long> = MutableList<Long>(n+1, {0L})

    for( i in 0..n-1)
    {
        arr[i] = scan.nextLong()
    }
    val obj = maxMinWindow()

    val res = obj.riddle(arr)

    println(res.joinToString(" "))
}
class maxMinWindow {
    // Complete the riddle function below.
    fun riddle(arr: MutableList<Long>): Array<Long> {
        // complete this function
        val n = arr.size
        val results = Array<Long>(n-1, { 0 })
        val stack = Stack<Pair>()
        val map = HashMap<Int, Long>()
        var i = 0
        while (i < n) {

            if (stack.isEmpty() || (stack.peek().value < arr[i])) {
                stack.push(Pair(arr[i], i))
                i++
            } else {
                val top = stack.pop()
                var winSize = 0
                if (stack.isEmpty()) {
                    winSize = i
                } else {
                    winSize = i - stack.peek().index - 1
                }
                if (map.containsKey(winSize)) {
                    map.put(winSize, Math.max(map.get(winSize)!!, top.value))
                } else {
                    map.put(winSize, top.value)
                }
            }
        }
        var smallestNum  = map.get(n-1)!!
        for (i in n-1 downTo 1) {
            if (map.containsKey(i))
            {
                if ( smallestNum < map.get(i)!!) {
                    smallestNum = map.get(i)!!
                }
            }
            results[i - 1] = smallestNum
        }
        return (results)
    }

    fun minNum(arr: Array<Long>, start: Int, end: Int): Long {
        var num = arr[start]
        for (i in start + 1..end) {
            num = Math.min(arr[i], num)
        }
        return num
    }


}