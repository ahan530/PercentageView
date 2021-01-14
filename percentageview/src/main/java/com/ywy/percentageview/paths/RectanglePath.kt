package com.ywy.percentageview.paths

import android.graphics.Path

/**
 *Time:2020/9/2
 *Author:ahan
 *Description: 多边形路径
 */
class RectanglePath : BasePath {

    constructor(list1: Array<PoinEntity>){
        path = Path()
        list = list1
       initData()
    }
    constructor(path: Path?,list1: Array<PoinEntity>){
        path?.reset()
        this.path = path
        list = list1
        initData()
    }

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