package com.yuzaco.kotlin.maxmodule

import com.sun.org.apache.xpath.internal.operations.Mod
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

    var m : Long = 0L
    var modLargest : Long = 0


// Complete the maximumSum function below.
fun maximumSum(a: Array<Long>, m1: Long): Long
{

    val n = a.size
    m  = m1
    modLargest = 0L

    val tSet  = TreeSet<Long>()
    for(i in 0..n-1)
    {
        a[i] = a[i]%m
        if ( i > 0 )
        {
            a[i] =  ( a[i-1] + a[i])%m
        }
        modmax(a[i])
        var higher: Long?  = tSet.higher(a[i])
        if ( higher != null )
        {
            higher = (( a[i] - higher )  + m )%m
            modmax(higher)
        }
        tSet.add(a[i])
    }
    return modLargest
}


fun modmax( newN : Long)
{
    var tmp : Long = newN.rem(m)
    if ( tmp > modLargest )
        modLargest = tmp
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val q = scan.nextInt()
    for (qItr in 1..q) {

        val n = scan.nextInt()

        val m = scan.nextLong()
        var a : Array<Long> = Array<Long>(n, {0L})
        for( i in 0..n-1){
            a[i] =  scan.nextLong()
        }

        val result = maximumSum(a, m)

        println(result)
    }
}