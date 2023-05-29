package com.example.retrofit2.retrofit

import com.example.retrofit2.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {
    //searchTerm은 내가 스트링으로 넣어줄건데, 걔가 query라고 들어갈 것이다.
    //https://www.unsplash.com/search/photos/?query="searchTerm으로 넣은 부분이 들어감". =>https://www.unsplash.com까지가 baseURL
    @GET(API.SEARCH_PHOTOS)
    fun searchPhotos(@Query("query") searchTerm: String) : Call<JsonElement> //반환값을 Call로 JsonElement로 받아서

    @GET(API.SEARCH_USERS)
    fun searchUsers(@Query("query") searchTerm: String) : Call<JsonElement>
}