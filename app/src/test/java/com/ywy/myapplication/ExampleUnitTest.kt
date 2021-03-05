package com.ywy.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun stringToFolat() {
        val toFloat = "0.0160".toFloat()
        print(toFloat)
    }


    fun stringToEmpty(){
        val  aaa:String =""
        if (aaa.isEmpty()){
            println(true)
        }else{
            print(false)
        }
    }
}