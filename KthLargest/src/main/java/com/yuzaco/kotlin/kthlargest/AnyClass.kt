package com.yuzaco.kotlin.anyclass

import java.util.*
fun main(args: Array<String>){
    val array  = arrayOf(8, 278, -198, 100, 2003, 4, 5, 88)
    val obj = AnyClass()
    val K = 1
    System.out.println("The " + K + "th largest number is " + obj.findKthLargest(array, K))
}
class AnyClass {
    companion object {
        var Total = 0
    }
    fun findKthLargest(data : Array<Int>, k: Int): Int{
        if ( k >  data.size )
            return -1
        Total = data.size - 1
        heapify(data)

        for ( i in 1..k )
        {
            swap(data, 0, Total)
            if ( i == k )
                return data[Total]
            Total--
            maxHeap(data, 0)
        }
        return -1
    }
    fun heapify(data: Array<Int>){
        Total = data.size -1
        for( i  in Total/2 downTo 0 ){
            maxHeap(data, i)
        }
    }
    fun swap(data:Array<Int>, i : Int, j : Int){
        val temp = data[i]
        data[i] = data[j]
        data[j] = temp
    }
    fun maxHeap(data: Array<Int>, parent : Int){
        var max = parent
        var left = parent*2
        var right = left+1
        if ( left < Total && data[left] > data[max] )
        {
            max = left
        }
        if ( right < Total  && data[right] > data[max])
        {
            max = right
        }
        if ( max != parent )
        {
            swap(data,parent, max)
            maxHeap(data, max)
        }
    }
}