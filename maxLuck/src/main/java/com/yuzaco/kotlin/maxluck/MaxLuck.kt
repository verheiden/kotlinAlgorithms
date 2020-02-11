package com.yuzaco.kotlin.maxluck

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

// Complete the luckBalance function below.
fun luckBalance(K: Int, contests: MutableList<Array<Int>>): Int {
    var sum = 0
    var lowest =  -1
    var k = K

    Collections.sort(contests) {a: Array<Int>, b: Array<Int> ->b[0] - a[0]}


    for( i in 0..contests.size-1)
    {
        val contest = contests[i]
        if ( contest[1] == 1 )
        {
            if ( k > 0 )
            {
                sum += contest[0]
                k -= 1
            }
            else
            {
                sum -= contest[0]
            }
        }
        else
        {
            sum += contest[0]
        }
    }
    return ( sum )
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nk = scan.nextLine().split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

    val contests = MutableList<Array<Int>>(n, { Array<Int>(2, { 0 }) })

    for (i in 0 until n) {
        contests[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }

    val result = luckBalance(k, contests)

    println(result)
}