package com.example.retrofit2.retrofit

import android.util.Log
import com.example.retrofit2.utils.API
import com.example.retrofit2.utils.Constants.TAG
import com.example.retrofit2.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class RetrofitManager {
    //싱글톤이 적용되도록 companion object에
    companion object {
        val instance = RetrofitManager() //RetrofitManager를 가져올 때는 얘의 instance만 가져오도록
    }

    //http 콜 만들기
    //레트로핏 인터페이스 가져오기
    private val retrofitInterface : RetrofitInterface? = RetrofitClient.getClient(API.BASE_URL)?.create(RetrofitInterface::class.java)

    //사진 검색 api 호출
    //매개변수로 글자와 끝난 것에 대한 결과를 string으로 받아서 보내줄 것
    //괄호를 비워두면 이벤트만 전달이 되는데 데이터도 같이 넘기고 싶으면 넘길 데이터의 자료형을 써줘야 함 : completion: (자료형) -> Unit
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE,String) -> Unit){
        val term = searchTerm.let {
            it
        }?: "" //searchTerm이 비어있으면 ""를 넣어라
//        val term = searchTerm ?: "" //위와 같은 코드

        val call = retrofitInterface?.searchPhotos(searchTerm = term).let {
            it
        }?: return //값이 없으면 리턴

        call.enqueue(object  : retrofit2.Callback<JsonElement>{
            //응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG,"RetrofitManager - onResponse() called / response: ${response.body()}")
                completion(RESPONSE_STATE.OKAY,response.body().toString())
            }
            //응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG,"RetrofitManager - onfailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL,t.toString())
            }

        })

    }
}