package com.masahirosaito.pictureslistview.client

import com.masahirosaito.pictureslistview.model.MyList
import retrofit2.http.GET
import rx.Observable

interface MyListClient {

    @GET("lists/list.json")
    fun getMyLists(): Observable<List<MyList>>
}