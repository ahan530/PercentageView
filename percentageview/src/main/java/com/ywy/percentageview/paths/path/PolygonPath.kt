package com.ywy.percentageview.paths.path

import android.graphics.Path
import com.ywy.percentageview.paths.PointEntity

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 多边形路径
 */
class PolygonPath : BasePath {

    //仅有路径点
    constructor(list1: Array<PointEntity>){
        path = Path()
        list = list1
       initData()
    }

    //有路径点和复用path对象
    constructor(path: Path?,list1: Array<PointEntity>){
        path?.reset()
        this.path = path
        list = list1
        initData()
    }

    //连接所有点
    private fun initData() {
        list?.let {
            for(i in it.indices){
                if (i == 0){
                    path?.moveTo(it[0].x,it[0].y)
                }else {
                    path?.lineTo(it[i].x,it[i].y)
                }
            }
            path?.close()
        }

    }
}