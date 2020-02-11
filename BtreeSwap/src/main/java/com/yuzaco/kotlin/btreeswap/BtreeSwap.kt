package com.yuzaco.kotlin.btreeswap
import java.io.*
import java.math.*
import java.text.*
import java.util.*
import java.util.regex.*
/* fun main(args: Array<String>) {

    val scan = Scanner(System.`in`)

    val n = scan.nextInt()

    val indexes = Array<Array<Int>>(n, { Array<Int>(2, { 0 }) })

    for (indexesRowItr in 0 until n) {
        indexes[indexesRowItr][0] = scan.nextInt()
        indexes[indexesRowItr][1] = scan.nextInt()
    }

    val queriesCount = scan.nextInt()

    val queries = Array<Int>(queriesCount, { 0 })
    for (queriesItr in 0 until queriesCount) {
        queries[queriesItr] = scan.nextInt()
    }

    val result = BtreeSwap.swapNodes(indexes, queries)

    System.out.println(result.map { it.joinToString(" ") }.joinToString("\n"))
}

class BtreeSwap {
    companion object {

        fun swapNodes(indexes: Array<Array<Int>>, queries: Array<Int>): Array<Array<Int>> {
            /*
         * Write your code here.
         */
            val n = indexes.size
            val nodes: ArrayList<Node> = ArrayList<Node>()
            for (i in 1..(n)) {
                nodes.add(Node(i))
            }

            for (i in 0..(n - 1)) {
                val node: Node = nodes[i]
                if (indexes[i][0] != -1) {
                    node.left = nodes[indexes[i][0] - 1]
                }
                if (indexes[i][1] != -1) {
                    node.right = nodes[indexes[i][1] - 1]
                }
            }


            val result: Array<Array<Int>> = Array(queries.size) { Array(n) {0} }
            val bTree = BinaryTree(nodes[0])
            for (i in 0..(queries.size - 1)) {

                result[i] =  bTree.swapNodesK(queries[i], ArrayList<Int>())
            }
            return result
        }
    }

    class Node(val index : Int) {

        public var left: Node? = null
        public var right: Node? = null

        fun swap(K:Int, depth: Int)
        {
            if ( (depth % K) == 0  )
            {
                val tmp: Node? = left
                left = right
                right = tmp
            }
            if ( left != null ) left!!.swap(K, depth+1 )
            if ( right != null ) right!!.swap(K, depth+1 )
        }
    }
    class BinaryTree(var root: Node) {
        fun swapNodesK(K: Int, result: ArrayList<Int>): Array<Int> {
            root.swap(K, 1)
            inOrder(root, result)
            return result.toTypedArray()

        }

        fun inOrder(parent: Node, result: ArrayList<Int>) {
            if (parent.left == null) {
                result.add(parent.index)
            } else {
                inOrder(parent.left!!, result)
                result.add(parent.index)
            }
            if (parent.right != null) {
                inOrder(parent.right!!, result)
            }
        }
    }
}
*/

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


// Complete the triplets function below.
fun triplets(a: Array<Int>, b: Array<Int>, c: Array<Int>): Long {
    a.sort(0, a.size )
    b.sort(0, b.size )
    c.sort(0, c.size)

    var count:Long = 0L
    var ai  = eliminateDups(a) -1
    var bi  = eliminateDups(b) -1
    var ci  = eliminateDups(c) -1

    while( bi >= 0 )
    {
        while( ai >= 0 && b[bi] <  a[ai] )
        {
            ai--
        }
        if ( ai < 0 )
            return count

        while(ci >= 0 && b[bi] < c[ci] ){
            ci--
        }

        if ( ci < 0 )
            return count
        var num1 : Long = 0L
       var num2 :Long =  0L
        num1 += (ai + 1)*(1)
        num2 += (ci +1)*(1)
        num1 = num1*num2
        count += num1
        bi--
    }
    return count
}
fun eliminateDups(arr: Array<Int>): Int
{
    var index : Int = 0
    var i: Int = 0
    while(i < arr.size )
    {
        arr[index] = arr[i++]
        while( i < arr.size && arr[index] == arr[i] ){
            i++
        }
        index++
    }
    return index
}
fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val lenaLenbLenc = scan.nextLine().split(" ")

    val lena = lenaLenbLenc[0].trim().toInt()

    val lenb = lenaLenbLenc[1].trim().toInt()

    val lenc = lenaLenbLenc[2].trim().toInt()

    val arra : Array<Int> = Array<Int>(lena, {0})
    for( i in 0..(lena-1))
    {
        arra[i] = scan.nextInt()
    }
    val arrb : Array<Int> = Array<Int>(lenb, {0})
    for( i in 0..(lenb-1))
    {
        arrb[i] = scan.nextInt()
    }
    val arrc : Array<Int> = Array<Int>(lenc, {0})
    for( i in 0..(lenc-1))
    {
        arrc[i] = scan.nextInt()
    }

    val ans = triplets(arra, arrb, arrc)

    println(ans)
}

