package com.ywy.percentageview.paths.path;

import android.graphics.Path;
import com.ywy.percentageview.paths.PointEntity

/**
 * Time:2020/9/2
 * Author:ahan
 * Description: 路径基类
 */
open class BasePath  {
    var list :Array<PointEntity>?=null  //路径点（大于3个）
    var path:Path?=null                //当前路径对象
}
