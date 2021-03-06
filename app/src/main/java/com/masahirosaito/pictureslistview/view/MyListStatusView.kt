package com.masahirosaito.pictureslistview.view

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.masahirosaito.pictureslistview.R
import com.masahirosaito.pictureslistview.bindView
import com.masahirosaito.pictureslistview.model.MyListStatus

class MyListStatusView : ConstraintLayout {

    constructor(context: Context,
                attrs: AttributeSet,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context,
                attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    val userIconImageView: ImageView by bindView(R.id.user_icon)
    val userNameTextView: TextView by bindView(R.id.user_name)
    val textTextView: TextView by bindView(R.id.text)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_myliststatus, this)
    }

    fun setMyListStatus(myListStatus: MyListStatus) {
        userNameTextView.text = myListStatus.user.name
        textTextView.text = myListStatus.text
        Glide.with(context).load(myListStatus.user.profile_image_url).into(userIconImageView)
    }
}