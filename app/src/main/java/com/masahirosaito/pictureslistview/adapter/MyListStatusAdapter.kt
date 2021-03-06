package com.masahirosaito.pictureslistview.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.masahirosaito.pictureslistview.model.MyListStatus
import com.masahirosaito.pictureslistview.view.MyListStatusView

class MyListStatusAdapter(private val context: Context) : BaseAdapter() {

    var myListStatuses: List<MyListStatus> = emptyList()

    override fun getCount(): Int = myListStatuses.size

    override fun getItem(position: Int): Any = myListStatuses[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?): View =
            ((convertView as? MyListStatusView) ?: MyListStatusView(context)).apply {
                setMyListStatus(myListStatuses[position])
            }
}