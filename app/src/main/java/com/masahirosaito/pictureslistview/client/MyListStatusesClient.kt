package com.masahirosaito.pictureslistview.client

import com.masahirosaito.pictureslistview.model.MyListStatus
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MyListStatusesClient {

    @GET("lists/statuses.json")
    fun getMyListStatuses(@Query("list_id") listId: Long) : Observable<List<MyListStatus>>
}