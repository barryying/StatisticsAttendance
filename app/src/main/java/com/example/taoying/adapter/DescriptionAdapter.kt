package com.example.taoying.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.taoying.models.Descriptions
import com.example.taoying.statisticsattendance.R
import kotlinx.android.synthetic.main.listitem.view.*

/**
 * Created by TaoYing on 2018/2/28.
 */

@Suppress("UNREACHABLE_CODE")
class DescriptionAdapter(var itemList: List<Descriptions>, var context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var hoder: DescriptionViewhoder
        val view: View
        if (convertView == null) {
            view = View.inflate(context, R.layout.listitem, null)
            hoder = DescriptionViewhoder(view)
            view.tag = hoder
        } else {
            view = convertView
            hoder = view.tag as DescriptionViewhoder
        }
        hoder.id.text = itemList[position].id.toString()
        hoder.title.text = itemList[position].title
        hoder.place.text = itemList[position].place
        hoder.date.text = itemList[position].date

        return view
    }

    override fun getItem(position: Int): Any {
        return itemList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}

class DescriptionViewhoder(viewItem: View) {
    var id: TextView = viewItem.txt_id as TextView
    var title: TextView = viewItem.txt_title as TextView
    var place: TextView = viewItem.txt_place as TextView
    var date: TextView = viewItem.txt_date as TextView
}