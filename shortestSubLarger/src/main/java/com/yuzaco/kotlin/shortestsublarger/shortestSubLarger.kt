package com.yuzaco.kotlin.shortestsublarger
fun main(args: Array<String>) {
    val dataArray = intArrayOf(-8, 1, 4, 2, -6, 3, 4, 7)
   val obj = shortestSubLarger()
    val result1 = obj.smallestSubWithSum(dataArray, 6)
    if (result1 > dataArray.size)
        System.out.println("Not Possible.")
    else
        System.out.println("The minimum subarray length is : $result1")
}
class shortestSubLarger {



    fun smallestSubWithSum(data: IntArray?, Sum: Int): Int {
        if (data == null)
            return 0
        val n = data.size
        var minLen = n + 1
        var start = 0
        var curSum = 0
        var end = 0
        curSum = data[start]
        if (curSum > Sum)
            return 1
        end = start + 1
        while (end < n) {

            while (end < n && curSum <= Sum) {
                if (curSum < 0 && data[end] > 0) {
                    curSum = 0
                    start = end
                }
                curSum += data[end++]
            }
            while (start < n && curSum > Sum) {
                if (minLen > end - start) {
                    minLen = end - start
                }
                curSum -= data[start++]
            }
        }
        return minLen
    }

}