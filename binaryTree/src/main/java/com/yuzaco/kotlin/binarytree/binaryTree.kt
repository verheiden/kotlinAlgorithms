package com.yuzaco.kotlin.binarytree
import java.util.*

fun main(args: Array<String>) {
    val brtree = binaryTree()
    brtree.insert(10)
    brtree.insert(2)
    brtree.insert(8)
    brtree.insert(4)
    brtree.insert(9)
    brtree.insert(7)
    brtree.insert(6)
    brtree.insert(5)
    brtree.insert(3)
    brtree.insert(-11)
    brtree.insert(-55)
    brtree.insert(-111)
    brtree.insert(125)
    brtree.insert(101)
    brtree.insert(-88)
    println("Complete tree")
    brtree.display()
    println("\n  Depth of the tree " + brtree.depth())
    var result = brtree.verticalOrder()
    var level = 0
    for (list in result) {
        print("\n  Level " + level++ + " : ")
        for (id in list) {
            print(" $id")
        }
    }
    print("\n Depth of tree " + brtree.depth() + "\n")
    val common : Node? = brtree.lCommonAncestor(3, -11)
    if ( common != null )
        println("Lowest common ancestor of 3 & -11 is " +  common.id )
    println("Breath First Search of the tree is : \n")
    brtree.BFS()

    brtree.delete(-111)
    brtree.delete(125)
    brtree.delete(101)
    brtree.delete(2)
    brtree.delete(5)
    result = brtree.verticalOrder()
    level = 0
    for (list in result) {
        print("\n  Level " + level++ + " : ")
        for (id in list) {
            print(" $id")
        }
    }
    println("Tree after -111, 125, 101, 2, 5 nodes are deleted")
}
class Node(var id: Int) {
    var left: Node? = null
    var right: Node? = null
}
class Queue<T>(val list:MutableList<T>){
    fun isEmpty() = list.isEmpty()

    fun count():Int = list.count()

    fun dequeue() : T? {
        if ( list.isEmpty())
            return null
        else
            return list.removeAt(0)
    }

    fun enqueue(item: T? ) {
        if ( item != null )
            list.add(item)
    }

}
class binaryTree
{
    var root: Node? = null

    fun BFS()
    {
        val Q : Queue<Node> = Queue<Node>( mutableListOf())
        Q.enqueue(root)
        while( !Q.isEmpty() )
        {
            val node = Q.dequeue()!!
            print(" " + node.id )
            if ( node.left != null )
                Q.enqueue(node.left)
            if ( node.right != null )
                Q.enqueue(node.right)
        }
    }
    fun DFS()
    {
        traverseInOrder(root)
    }

    fun lCommonAncestor(A: Int, B: Int): Node? {
        return if (A > B)
            findCommon(root, A, B)
        else
            findCommon(root, B, A)
    }

    fun findCommon(parent: Node?, big: Int, small: Int): Node? {
        if ( parent == null )
            return null
        if (( parent.id <= big  && parent.id >= small ))
            return parent
        if ( parent.id > big )
            return findCommon(parent.left, big, small )
        else ( parent.id < small )
        return findCommon(parent.right, big, small )
    }

    init {
        root = null
    }

    fun display() {
        traverseInOrder(root)
    }

    fun insert(value: Int) {
        if (root == null) {
            root = Node(value)
            return
        }
        var current = root
        while (current != null) {
            if (value == current.id) {
                return
            } else if (value > current.id) {
                if (current.right == null) {
                    current.right = Node(value)
                    return
                }
                current = current.right
            } else {
                if (current.left == null) {
                    current.left = Node(value)
                    return
                }
                current = current.left
            }
        }
    }
    fun verticalOrder(): ArrayList<ArrayList<Int>> {
        val result = ArrayList<ArrayList<Int>>()
        traverse(root, 0, result)
        return result
    }

    internal fun traverse(node: Node?, level: Int, result: ArrayList<ArrayList<Int>>) {
        if (result.size <= level) {
            val row = ArrayList<Int>()
            row.add(node!!.id)
            result.add(row)
        } else {
            result[level].add(node!!.id)
        }
        if (node.left != null)
            traverse(node.left, level + 1, result)
        if (node.right != null)
            traverse(node.right, level + 1, result)
    }


    fun maxDepth(oneNode: Node?): Int {
        return if (oneNode == null) 0 else Math.max(
            maxDepth(oneNode.left),
            maxDepth(oneNode.right)
        ) + 1
    }

    fun traverseInOrder(currentNode: Node?) {
        if (currentNode == null)
            return
        traverseInOrder(currentNode.left)
        print(currentNode.id.toString() + " ")
        traverseInOrder(currentNode.right)
    }

    fun depth(): Int {
        return depthRecursive(root)
    }

    fun depthRecursive(node: Node?): Int {
        return if (node == null) 0 else Math.max(
            depthRecursive(node.right),
            depthRecursive(node.left)
        ) + 1
    }

    fun delete(data : Int){

        deleteRec(root, data)

    }
    private fun deleteRec(node: Node?, key: Int) : Node ?
    {
        if ( node == null )
            return null;
        if ( node.id < key )
            node.right = deleteRec(node.right, key)
        else if ( node.id > key )
            node.left = deleteRec(node.left, key)
        else /* it is the same as the key */
        {
            if ( node.left == null )
                return node.right
            else if ( node.right == null )
                return node.left
            else
            {
                node.id = minValue(node.right!!)
                node.right = deleteRec(node.right, node.id)
            }
        }
        return node
    }
    private fun minValue(node1 : Node? ): Int
    {
        if ( node1 == null )
            return -1
        var node : Node = node1
        var minVal : Int = node.id

        while(node.left != null )
        {
            minVal = node.left!!.id
            node = node.left!!
        }
        return minVal
    }
}