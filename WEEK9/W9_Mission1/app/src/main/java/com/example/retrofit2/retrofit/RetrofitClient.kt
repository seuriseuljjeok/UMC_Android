package com.example.retrofit2.retrofit

import android.util.Log
import com.example.retrofit2.utils.API
import com.example.retrofit2.utils.Constants.TAG
import com.example.retrofit2.utils.isJsonArray
import com.example.retrofit2.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

//kotlin에서 object는 싱글톤. 메모리를 하나만 쓰는 것
object RetrofitClient {
    //레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null
//    private lateinit var retrofitClient: Retrofit = null

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d(TAG, "RetrofitClient - getClient() called")

        //로깅 인터셉터 추가

        //OkHttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        //로그를 찍기 위해 로깅 인터셉터 추가 => 전반적인 모든 통신 내용을 볼 수 있음
        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d(TAG,"RetrofitClient - log() called / message: $message")
                when{
                    message.isJsonObject()->
                        Log.d(TAG, JSONObject(message).toString(4)) //4줄 들여쓰기
                    message.isJsonArray()->
                        Log.d(TAG, JSONObject(message).toString(4)) //4줄 들여쓰기
                    else -> {
                        try {
                            Log.d(TAG,JSONObject(message).toString(4))
                        }catch (e: Exception){
                            Log.d(TAG,message)
                        }
                    }
                }
            }
        })
        //로깅 레벨 설
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        //위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)


        //기본 파라미터 추가
        val baseParameterInterceptor : Interceptor = (object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "RetrofitClient - intercept() called")
                //오리지날 리퀘스트 => 기본 파라미터 들어가기 전, 기존 리퀘스트 가져옴
                val originalRequest = chain.request()

                //?client_id=fsdfasfs 를 넣는 과정
                //쿼리 파라미터 추가하기
                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()
                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()
                return chain.proceed(finalRequest)
            }

        })

        //위에서 설정한 기본 파라미터 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(baseParameterInterceptor)

        //커넥션 타임아웃
        client.connectTimeout(10,TimeUnit.SECONDS)
        client.readTimeout(10,TimeUnit.SECONDS)
        client.writeTimeout(10,TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        if (retrofitClient == null){ //retrofitclient가 없으면 그 때 빌드
            //레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build()) //위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                .build() //.~~형식으로 옵션들을 설정해주고 마지막에 build()로 포장
        }
        return retrofitClient
    }
}