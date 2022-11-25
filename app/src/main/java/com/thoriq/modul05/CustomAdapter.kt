package com.thoriq.modul05

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(context: Context, arrayListDetails: ArrayList<Model>): BaseAdapter() {
    private val layoutInflater: LayoutInflater
    private val arrayListDetails: ArrayList<Model>
    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails = arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val v: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            v = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(v)
            v.tag = listRowHolder
        } else {
            v = convertView
            listRowHolder = v.tag as ListRowHolder
        }

        listRowHolder.tvName.text = arrayListDetails.get(position).name
        listRowHolder.tvEmail.text = arrayListDetails.get(position).email
        listRowHolder.tvId.text = arrayListDetails.get(position).id

        return v
    }
}

private class ListRowHolder(row: View?) {
    val tvName: TextView
    val tvEmail: TextView
    val tvId: TextView
    val linearLayout: LinearLayout

    init {
        this.tvId = row?.findViewById<TextView>(R.id.tvId) as TextView
        this.tvName = row?.findViewById<TextView>(R.id.tvName) as TextView
        this.tvEmail = row?.findViewById<TextView>(R.id.tvEmail) as TextView
        this.linearLayout = row?.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
    }
}