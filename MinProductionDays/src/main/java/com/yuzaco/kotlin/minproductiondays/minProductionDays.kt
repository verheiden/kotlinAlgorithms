package com.yuzaco.kotlin.minproductiondays

import java.util.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.ranges.*


// Complete the minTime function below.
fun minTime(machines: Array<Long>, goal: Long): Long {
    var n = machines.size
    var smallest  = machines[0]
    var avg: Double = 0.0
    var temp : Double = 0.0
    temp += machines[0]
    temp = 1/temp
    avg = temp
    for( i in 1..(n-1)){
        if ( smallest > machines[i] )
        {
            smallest = machines[i]
        }
        temp = 0.0
        temp += machines[i]
        avg  +=  1/temp
    }

 temp = 0.0
 temp += goal
 temp = temp/avg
 var minDays : Long = temp.toLong()

    var maxDays = goal * smallest
    var mid : Long = 0L
    while( minDays < maxDays )
    {
        mid  = ( maxDays + minDays  )/2
        if ( minDays == ( maxDays -1 )) {
            if ( pDays(goal, machines, minDays) == 0L ) {
                mid = minDays
            }
            else
            {
                mid = maxDays
            }
            break
        }
        val comp = pDays(goal, machines, mid )
        if ( comp == 0L )
             break

        if (  comp < 0L )
        {
            maxDays = mid
        }
        else if ( comp > 0L)
        {
            minDays = mid
        }

    }
    while( pDays(goal, machines, mid-1) == 0L )
    {
        mid--
    }
    return mid
}
fun pDays(k: Long, machines:Array<Long>, days: Long ): Long
{
    if ( days <= 0 )
        return -1L
    var totalProduction = 0L
    for( i in 0..(machines.size -1))
    {
        totalProduction += days/machines[i]
        if ( totalProduction > k )
            return -1L
    }
    if ( totalProduction < k )
        return 1L
    return 0L
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nGoal = scan.nextLine().split(" ")

    val n = nGoal[0].trim().toInt()

    val goal = nGoal[1].trim().toLong()
    val machines: Array<Long> = Array<Long>(n, {0L})
    for( i in 0..(n-1) )
    {
        machines[i] = scan.nextLong()
    }

    val ans = minTime(machines, goal)

    println(ans)
}
