package com.yuzaco.kotlin.kthpolindrone
import java.util.*
import sun.misc.Version.print
fun main(args: Array<String>){

    val obj : kthpolindrone = kthpolindrone()

    if ( obj.isOnePolinDrone("abcdkdcba"))
        System.out.println("")
}
class kthpolindrone {

    fun isOnePolinDrone(s: String?) : Boolean {
        if (s == null || s.length == 0)
            return false
        var n: Int = s.length
        var i: Int = 0
        var j: Int = n - 1

        while (i < n && j >= 0 && s.run { s.get(i) } == s.get(i)) {
            i++;
            j--;
        }
        if (i == (n - 1) && j == 0)
            return true;

        var ii: Int = i + 1;
        var jj: Int = j;
        while ((ii < n && j >= 0) && s.get(ii) == s.get(jj)) {
            ii++;
            jj--;
        }
        if (ii == (n ) && jj == 0)
            return true;
        ii = i;
        jj = j -1;
        while ((ii < n && j >= 0) && s.get(ii) == s.get(jj)) {
            ii++;
            jj--;
        }
        if (ii == n && jj == 0) return true;

        return false;
    }
}