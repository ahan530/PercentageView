package com.ywy.percentageview.paths

import android.graphics.Path

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 路径工厂 传入继承basePath的子类，返回完整的path路径 根据传入的不同类进行加工
 */
class  PathFactory {
    companion object{
        fun createPath(basePath:BasePath):Path?{
            return basePath.path
        }
    }
}