package com.yuzaco.kotlin.add2binarystrings
fun main(args: Array<String>){
    val obj = add2BinaryStrings()
    System.out.print("\nResult is :  ")
    System.out.print(obj.addTwoBinaryStrings("000000010000000", "11111111"))
}
class add2BinaryStrings {
    fun addTwoBinaryStrings(b1 : String, b2 : String ): String{

        var rLength = Math.max(b1.length, b2.length) + 1
        var result = IntArray(rLength+1)
      var i = b1.length -1
        var j=  b2.length  -1
        var k = rLength

      while(i >= 0 && j >= 0  ){
          result[i] = (result[i].plus( b1.get(i) - '0' )) + ( b2.get(j)-'0')
          if ( result[k]/2 != 0 )
              result[k-1] += 1;
          result[k] = result[i]%2
          i--
          j--
          k--
      }
      while(i>=0)
      {
              result[k] = result[k] + ( b1.get(i) -'0' )
              if ( result[k]/2 != 0 )
                  result[k-1] += 1
              result[k] = result[k]%2
          i--
          k--
      }
      while(j>=0)
      {
            result[k] = result[k] + ( b2.get(j) -'0' )
            if ( result[k]/2 != 0 )
                result[k-1] += 1
            result[k] = result[k]%2
          j--
          k--
      }
      k = 0;
      while(k < rLength )
      {
          if ( result[k] != 0)
              break;
          k++;
      }
      var buf : StringBuffer  = StringBuffer();
      while(k < rLength )
      {
          var c : Char = ( result[k] + 0x30 ).toChar();
          buf = buf.append(c);
          k++
      }
      return buf.toString();
    }

}
/*
fun main(args: Array<String>){
    val obj=addDigitsSortChars()
    System.out.println("The answer is  " + obj.sortCharsAndSumDigits("ABCD12345URESTDERKR748930AAAKKK"))
}*/
const val MaxChar: Int = 26
class addDigitsSortChars {

    fun sortCharsAndSumDigits(s : String) : String {
        if ( s == null || s.length == 0 )
            return s
        var anyDigits  = false
        var sum = 0
        var charTable  : IntArray = IntArray(MaxChar)



        for( i in 0 until s.length ){
            var c : Int  = s.get(i).toInt()

            if ( isDigit(c))
            {
                sum +=  ( c  -0x30)
                anyDigits = true
            }
            else {
                charTable[(c  - 0x40) ]++
            }
        }

        var buf : StringBuffer  = StringBuffer()

        for(  i  in 0 until MaxChar  ){
            for( j in 0 until charTable[i]){
                buf.append( ( i  + 0x40 ).toChar())
            }
        }

        var s1  = buf.toString()
        buf = StringBuffer()
        if ( anyDigits ) {
            while (sum > 0) {
                var digit: Int = sum % 10
                sum = sum / 10
                buf.insert(0, (digit + 0x30).toChar())
            }
            s1 = s1.plus(buf.toString())
        }
        return s1
    }
    fun isDigit(c: Int) :Boolean {
        if ( c <= 0x39 && c  >= 0x30 )
            return true
        return false;
    }
}