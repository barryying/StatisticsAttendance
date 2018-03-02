package com.example.taoying.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by TaoYing on 2018/2/28.
 */

/**
 * 对Context的拓展函数，弹Toast
 */
fun Context.toast(message:String, length: Int= Toast.LENGTH_SHORT){
    Toast.makeText(this,message,length).show()
}