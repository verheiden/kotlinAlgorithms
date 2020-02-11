package com.yuzaco.kotlin.linkedlistsort

import sun.misc.Version.print
import java.util.*

fun main(args: Array<String>) {

    val obj = LinkedListSort()
    var a = obj.testSort()

    while (a != null) {
        print(" " + a.value )
        a = a.next
    }
}

class LinkedListSort {
     class ListNode(var value: Int) {
        var next: ListNode? = null

        init {
            next = null
        }
    }

fun testSort(): ListNode? {
        val a = ListNode(10)
        val b = ListNode(5)
        val c = ListNode(3)
        a.next = b
        b.next = c
        val d = ListNode(5)
        val e = ListNode(0)
        val f = ListNode(9)
        c.next = d
        d.next = e
        e.next = f
        return sortList(a)
    }

    private fun sortList(list: ListNode?): ListNode? {
        if (list == null || list.next == null)
            return list

        var faster = list.next
        var slower = list

        while (faster != null) {
            faster = faster.next
            if (faster != null) {
                faster = faster.next
                slower = slower!!.next
            }

        }
        val right = slower!!.next
        slower.next = null
        val leftSorted = sortList(list)
        val rightSorted = sortList(right)
        return merge(leftSorted, rightSorted)
    }

    private fun merge(a: ListNode?, b: ListNode?): ListNode? {
        var head: ListNode? = null
        var tail: ListNode? = null
        var left = a
        var right = b
        while (left != null && right != null) {
            if (left.value < right.value) {
                if (head == null) {
                    head = left
                }
                if (tail != null)
                    tail.next = left
                tail = left
                left = left.next

            } else {
                if (head == null)
                    head = right
                if (tail != null)
                    tail.next = right
                tail = right
                right = right.next

            }
            tail.next = null
        }
        if (left != null)
            tail!!.next = left
        else if (right != null)
            tail!!.next = right
        return head
    }

}