package com.example.taoying.statisticsattendance

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.taoying.adapter.TestAdapter
import com.example.taoying.utils.LogUtils
import com.example.taoying.utils.toast
import kotlinx.android.synthetic.main.activity_originator.*
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView


/**
 * Created by TaoYing on 2018/2/27.
 */

class OriginatorActivity : AppCompatActivity() {

    data class Description(val title: String, val place: String,val date: String)
    var itemList = ArrayList<Description>()
    private var adapter: TestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_originator)
        var title = edttxt_Title.text.toString()
        var place = edttxt_place.text.toString()
        var date = edttxt_date.text.toString()

        btn_cancel.setOnClickListener ({ startActivity(Intent(Guiding@this,MainActivity::class.java ))})
        btn_submit.setOnClickListener ({
            // 判断是否为空
            if(title == "" || place == "" || date == "") {
                toast("文本框不允许为空")
            }
            else {

                //Log.i("www.ythook.com","start log")
                // 数据类序列化
                val description = Description(title, place, date)
                LogUtils.i("www.ythook.com", description.toString())

                // ListView 绑定数据
                itemList.add(description)
                lsv_description.adapter = TestAdapter(itemList, this)

                // ListView的每个Item点击事件
                lsv_description.setOnItemClickListener(ItemClickListener())
                edttxt_Title.setText("")
                edttxt_place.setText("")
                edttxt_date.setText("")

                //Toast.makeText(this,"创建成功",Toast.LENGTH_SHORT).show()
                toast("创建成功")
                //startActivity(Intent(Guiding@this,MainActivity::class.java ))
            }
        })
    }

    private inner class ItemClickListener : OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            // 获取ListView
            val listView = parent as ListView

            // 通过position查找Item信息，强制转换为Description类型
            var description: Description = listView.getItemAtPosition(position) as Description

            // 解构Description
            var (title,place,date) = description//多重赋值
            edttxt_Title.setText("$title")
            edttxt_place.setText("$place")
            edttxt_date.setText("$date")
            LogUtils.i("www.ythook.com",description.toString())
        }
    }
}
