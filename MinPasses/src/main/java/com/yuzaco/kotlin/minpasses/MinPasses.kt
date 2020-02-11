package com.yuzaco.kotlin.minpasses


import jdk.nashorn.internal.objects.NativeFunction.function
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

// Complete the minimumPasses function below.
fun minimumPasses(M: Long,W: Long, p: Long, n: Long): Long {

    var passes = 1L
    var dtotal: Double  = 0.0
    var w1: Double = 0.0
    w1 += W
    var m1 : Double = 0.0
    m1 += M
    var w : Long  = W
    var m : Long  = M
    dtotal +=m1 * w1
    if ( dtotal >= n )
        return passes
    var total1 = m*w
    var nums = 0L
    while ( total1 < n  )
    {

      var prevTotal = total1
      val prevDaily = m*w
      var remainder = n - total1


      if (remainder <= p )
      {
          passes += remainder/prevDaily
          if ( remainder%(prevDaily) != 0L )
              passes++
          return passes
      }
      if( total1 < p )
        {
            var nPasses = (p - total1)/prevDaily
            if ( (p - total1)%prevDaily != 0L )
                nPasses++
            passes += nPasses
            total1 += nPasses*prevDaily
            prevTotal = total1
        }
        nums = total1/p
        total1 -= nums*p
        if (m > w) {
            if ((m - w) >= nums)
                w += nums;
            else {
                val mod = (nums - (m - w)) % 2
                nums = (nums - (m - w)) / 2
                m += nums
                w = m + mod
            }
        } else {
            if ((w - m) >= nums)
                m += nums
            else {
                val mod = (nums - (w - m)) % 2
                nums = (nums - (w - m)) / 2
                w += nums
                m = w + mod
            }
        }
        remainder = n - total1
        var newTries = ( remainder)/(m*w)
        if ( (remainder%(m*w))  != 0L )
            newTries++
        var oldTries = ( n -prevTotal)/prevDaily
        if ( (n - prevTotal )%prevDaily != 0L )
            oldTries++
        if ( newTries > oldTries) {
            passes += (n - prevTotal) / prevDaily
            val mod = (n - prevTotal) % prevDaily
            if (mod != 0L) return ++passes
            return passes
        }
        total1 += m*w
        passes++
    }
    return passes
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val mwpn = scan.nextLine().split(" ")

    val m = mwpn[0].trim().toLong()

    val w = mwpn[1].trim().toLong()

    val p = mwpn[2].trim().toLong()

    val n = mwpn[3].trim().toLong()

    val result = minimumPasses(m, w, p, n)

    println(result)
}
