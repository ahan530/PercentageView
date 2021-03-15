package com.ywy.percentageview.paths.factory

import android.graphics.Path
import com.ywy.percentageview.paths.path.BasePath

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 路径工厂 传入继承basePath的子类，返回完整的path路径 根据传入的不同类进行加工
 */
object PathFactory : BasePathFactory() {

    fun createPath(basePath: BasePath): Path? {
        return basePath.path
    }
}