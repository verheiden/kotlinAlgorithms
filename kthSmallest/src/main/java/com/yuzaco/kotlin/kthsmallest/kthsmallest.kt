package com.yuzaco.kotlin.kthsmallest

import java.util.*

// Complete the maximumToys function below.
fun maximumToys(prices: IntArray, k: Int): Int
{

    val n = prices.size
    var total = 0
    heapify(prices )
    for( i in (n-1) downTo 0 )
    {
        swap(prices, 0, i )
        total += prices[i]
        if ( total == k )
            return ( n - i )
        else if ( total > k )
            return ( n -i -1)
        minHeap(prices, 0, i )
    }
    return n
}
fun heapify(prices:IntArray )
{
    val n =  prices.size
    for( i in  (n/2) downTo 0 )
    {
        minHeap(prices, i, n )
    }
}
fun minHeap(prices: IntArray, parent: Int, t : Int )
{
    var min  = parent
    var left = parent*2
    var right = left + 1
    if ( left < t && prices[left] < prices[parent] )
    {
        min = left
    }
    if ( right < t && prices[right] < prices[min] )
    {
        min = right
    }
    if ( min != parent )
    {
        swap(prices, min, parent )
        minHeap(prices, min, t )
    }
}
fun swap(prices: IntArray, i: Int, j : Int )
{
    val tmp  = prices[i]
    prices[i] = prices[j]
    prices[j] = tmp
}
fun main(args: Array<String>) {

    val n = 7

    val k = 50

    val prices = intArrayOf(1, 12, 5, 111, 200,1000, 10)

    val result = maximumToys(prices, k)

    println(result)
}
