package com.example.taoying.utils

import java.io.Closeable
import java.io.IOException
/**
 * Created by TaoYing on 2018/2/28.
 */

object CloseIoUtils {

    /**
     * 关闭IO
     * @param closeables closeables
     */
    fun closeIO(vararg closeables: Closeable) {
        if (closeables == null) return
        closeables
                .filterNotNull()
                .forEach {
                    try {
                        it!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
    }

}