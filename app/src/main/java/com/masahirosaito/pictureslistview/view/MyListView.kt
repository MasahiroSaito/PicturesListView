package com.masahirosaito.pictureslistview.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.masahirosaito.pictureslistview.R
import com.masahirosaito.pictureslistview.bindView
import com.masahirosaito.pictureslistview.model.MyList

class MyListView : ConstraintLayout {

    constructor(context: Context,
                attrs: AttributeSet,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context,
                attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    val myListNameTextView: TextView by bindView(R.id.mylist_name)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mylist, this)
    }

    fun setMyList(myList: MyList) {
        myListNameTextView.text = myList.name
    }
}