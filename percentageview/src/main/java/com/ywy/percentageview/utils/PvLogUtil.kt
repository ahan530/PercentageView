package com.ywy.percentageview.utils

import android.util.Log
import com.ywy.percentageview.BuildConfig
/**
 * CreateTime:2020/12/30
 * @Author:admin
 * Description:日志工具类
 **/
object PvLogUtil {

    fun e(tag:String,content:String){
        if (BuildConfig.DEBUG){
            Log.e(tag,content)
        }
    }

    fun i(tag:String,content:String){
        if (BuildConfig.DEBUG){
            Log.i(tag,content)
        }
    }

    fun d(tag:String,content:String){
        if (BuildConfig.DEBUG){
            Log.d(tag,content)
        }
    }
}