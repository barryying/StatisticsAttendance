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
import com.example.taoying.adapter.DescriptionAdapter
import com.example.taoying.utils.SqliteUtils
import com.example.taoying.models.Descriptions


/**
 * Created by TaoYing on 2018/2/27.
 */

class OriginatorActivity : AppCompatActivity() {

    data class Description(val title: String, val place: String,val date: String)
    //var itemList = ArrayList<Description>()
    //private var adapter: TestAdapter? = null
    var sqliteutils: SqliteUtils? = null
    var listDescriptions: List<Descriptions> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_originator)
        toast("初始化[一共" + initDB() + "条记录。]")
        //initDB()

        btn_cancel.setOnClickListener { startActivity(Intent(Guiding@this,MainActivity::class.java ))}
        btn_submit.setOnClickListener {
            var success = false
            // 判断是否为空
            var str_title = edttxt_Title.text.toString()
            var str_place = edttxt_place.text.toString()
            var str_date = edttxt_date.text.toString()
            if(str_title == "" || str_place == "" || str_date == "") {
                toast("文本框不允许为空")
            }
            else {

                Log.i("www.ythook.com","start log")
                // 数据类序列化
//                val description = Description(str_title, str_place, str_date)
//                LogUtils.i("www.ythook.com", description.toString())
//
//                // ListView 绑定数据
//                itemList.add(description)
//                lsv_description.adapter = TestAdapter(itemList, this)

                // 添加记录 addDescription
                val descriptions: Descriptions = Descriptions()
                descriptions.title = str_title
                descriptions.place = str_place
                descriptions.date = str_date
                success = sqliteutils?.addDescription(descriptions) as Boolean
                toast("点击提交了")
                initDB()

                //Toast.makeText(this,"创建成功",Toast.LENGTH_SHORT).show()
                toast("创建成功")
                //startActivity(Intent(Guiding@this,MainActivity::class.java ))
            }
        }
    }

    private inner class ItemClickListener : OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            // 获取ListView
            val listView = parent as ListView

            // 通过position查找Item信息，强制转换为Descriptions类型
            var descriptions: Descriptions = listView.getItemAtPosition(position) as Descriptions

            // 解构Description
            edttxt_id.setText(descriptions.id.toString())
            edttxt_Title.setText(descriptions.title)
            edttxt_place.setText(descriptions.place)
            edttxt_date.setText(descriptions.date)
            LogUtils.i("www.ythook.com",descriptions.title + "," + descriptions.place + "," + descriptions.date)

//            // 获取ListView
//            val listView = parent as ListView
//
//            // 通过position查找Item信息，强制转换为Description类型
//            var description: Description = listView.getItemAtPosition(position) as Description
//
//            // 解构Description
//            var (title,place,date) = description//多重赋值
//            edttxt_Title.setText("$title")
//            edttxt_place.setText("$place")
//            edttxt_date.setText("$date")
//            LogUtils.i("www.ythook.com",description.toString())
        }
    }

    fun initDB(): Int {
        // ListView 绑定数据
        sqliteutils = SqliteUtils(this)
        listDescriptions = (sqliteutils as SqliteUtils).description()

        if(!listDescriptions.isEmpty()){
            lsv_description.adapter = DescriptionAdapter(listDescriptions, this)
            // ListView的每个Item点击事件
            lsv_description.onItemClickListener = ItemClickListener()
            edttxt_Title.setText("")
            edttxt_place.setText("")
            edttxt_date.setText("")
            return listDescriptions.count()
        }
        else {
            toast("数据库为空！")
            return 0
        }
    }
}
