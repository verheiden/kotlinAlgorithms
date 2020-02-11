package com.yuzaco.kotlin.roadslibs

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
data class Node(val name: Int )
{
    var connections: ArrayList<Node>  = ArrayList<Node>()
    var visited = false
    fun addConnections(city: Node )
    {
        connections.add(city)
    }
}
class Component()
{
    var roads : Int = 0
}
// Complete the roadsAndLibraries function below.
fun roadsAndLibraries(n: Int, c_lib: Int, c_road: Int, bridges: Array<Array<Int>>): Long {
    var minCost = 0L
    var map  = HashMap<Int, Node>()
    var eCost = n.toLong() * c_lib.toLong()

    if ( bridges.size >= ( n - 1) )
    {
        return (Math.min( eCost, c_lib + (n-1)*c_road.toLong()))
    }
    for( i in 0..bridges.size-1)
    {
        var city1Name = bridges[i][0]
        var city2Name = bridges[i][1]
        lateinit var  city1  : Node
        lateinit var city2 : Node

        if ( !map.containsKey(city1Name) )
        {
            city1 = Node(city1Name)
        }
        else
        {
            city1 = map.get(city1Name)!!
        }
        map.put(city1Name, city1)
        if ( !map.containsKey(city2Name) )
        {
            city2 = Node(city2Name)
        }
        else
        {
            city2 = map.get(city2Name)!!
        }
        map.put(city2Name, city2)
        city1.addConnections(city2)
        city2.addConnections(city1)
    }

    map.forEach {(name, city)-> if ( !city.visited )
    {  minCost += c_lib + c_road* dfs(city)}}
    minCost += ( n - map.size )*c_lib.toLong()
    minCost = Math.min( eCost , minCost )
    return(minCost)
}
fun dfs(root: Node ): Int
{
    var s : Stack<Node>  = Stack<Node>()
    s.push(root)
    var roads = 0
    while(s.isNotEmpty())
    {
        var city = s.pop()
        if ( city.visited == false )
        {
            city.visited = true
            roads += 1
            city.connections.forEach<Node>
            {
                if (it.visited == false) {
                    s.push(it)
                }
            }
        }
    }
    return(roads-1)
}
fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val q = scan.nextLine().trim().toInt()

    for (qItr in 1..q) {
        val nmC_libC_road = scan.nextLine().split(" ")

        val n = nmC_libC_road[0].trim().toInt()

        val m = nmC_libC_road[1].trim().toInt()

        val c_lib = nmC_libC_road[2].trim().toInt()

        val c_road = nmC_libC_road[3].trim().toInt()

        val bridges = Array<Array<Int>>(m, { Array<Int>(2, { 0 }) })

        for (i in 0 until m-1) {
            bridges[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
        }
        scan.nextLine()
        val result = roadsAndLibraries(n, c_lib, c_road, bridges)

        println(result)
    }
}
