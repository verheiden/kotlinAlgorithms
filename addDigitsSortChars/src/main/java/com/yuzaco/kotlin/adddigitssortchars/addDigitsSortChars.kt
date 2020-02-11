package com.yuzaco.kotlin.adddigitssortchars

import java.util.*

fun main(args: Array<String>){
  val obj: addDigitsSortChars = addDigitsSortChars()

    System.out.println("Adding digits and sorting characters of CDASEE1234567KHIJPYX7289WWXXAA is :\n"
     + obj.sortChars("CDASEE1234567KHIJPYX7289WWXXAA"))

}
const val MAX_CHAR = 26
class addDigitsSortChars {

    fun sortChars(s:String) : String{
        if ( s == null || s.length == 0 )
            return s
        var charTable: IntArray = IntArray(MAX_CHAR)
        var Sum : Int = 0;
        var ifDigits = false

        for( i in 0 until s.length){
            val c : Int = s.get(i).toInt()

            if ( isDigit(c) ){
                Sum += c - 0x30
                ifDigits = true
            }
            else
            {
                charTable[c-0x40]++
            }
        }
        var buf = StringBuffer()
        for ( i in 0 until MAX_CHAR ){
            for ( j in 0 until charTable[i]){
                buf.append((i+0x40).toChar())
            }
        }
        var s1 = buf.toString()
        if ( ifDigits){
           var numBuf : StringBuffer = StringBuffer()
           while(Sum > 0 ) {
               val digit : Char = (( Sum %10) + 0x30).toChar()
               numBuf.insert(0, digit)
               Sum /= 10
           }
           s1 = s1.plus(numBuf.toString())
        }

        return s1
    }

    fun isDigit(c : Int ): Boolean {
        if ( c >=  0x30 && c <= 0x39 ){
            return true
        }
        return false
    }

}