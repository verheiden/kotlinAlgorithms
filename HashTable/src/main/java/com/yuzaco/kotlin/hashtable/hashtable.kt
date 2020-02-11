package com.yuzaco.kotlin.hashtable

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


class  MaxModule  {
    companion object {
        lateinit var dp: Array<LongArray>
        var modL: Long = 0L

        fun computeSubs(i: Int, j: Int,  m: Long ): Long {
            if (i < 0 || j < 0)
                return 0L

            if (i > j)
                return 0L

            if (dp[i][j] != -1L)
                return dp[i][j]
            for (k in (i+1)..(j)) {
                computeSubs(i, k, m)
            }
            dp[i][j] = dp[j][j] + dp[i][i] + computeSubs(i+1, j - 1, m)
            val temp: Long = dp[i][j] % m
            if (modL < temp)
                modL = temp
            return dp[i][j]
        }


    }
}
// Complete the maximumSum function below.
fun maximumSum(a: Array<Long>, m: Long): Long {
    val n1 = a.size
    MaxModule.dp = Array(n1, {LongArray(n1)})
    for (i in 0..(a.size - 1)) {
        var array = Array<Long>(n1, {-1L})
        for (j in 0..(a.size - 1)) {
            if (i == j) {
                MaxModule.dp[i][j] = a[i]
                val temp: Long = a[i] % m
                if (MaxModule.modL < temp)
                    MaxModule.modL = temp
            }
        }
    }
    MaxModule.computeSubs(0, n1 - 1, m)

    return MaxModule.modL

}
fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val q = scan.nextLine().trim().toInt()

    for (qItr in 1..q) {
        val nm = scan.nextLine().split(" ")

        val n = nm[0].trim().toInt()

        val m = nm[1].trim().toLong()
        var a : Array<Long> = Array<Long>(n, {0L})
        for( i in 0..n-1){
            a += scan.nextLong()
        }

        val result = maximumSum(a, m)

        println(result)
    }
}