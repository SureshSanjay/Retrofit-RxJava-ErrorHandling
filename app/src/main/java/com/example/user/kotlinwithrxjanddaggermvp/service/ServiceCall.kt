package com.example.user.kotlinwithrxjanddaggermvp.service

import com.example.user.kotlinwithrxjanddaggermvp.model.DataItem
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 3/9/18.
 */
class ServiceCall {
    val service : ServiceInterface
    //var baseUrl = "http://demo2974937.mockable.io/"
    var baseUrl = "http://test.voting.api.okdollar.org/"

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<ServiceInterface>(ServiceInterface::class.java)
    }

    fun loadData(): Observable<DataItem> {
        return service.getMyFriends()
    }

    fun generateToken(map: HashMap<String, String>): Observable<JsonObject> {
        return service.getToken(options = map)
    }

    fun getVotingResponse(@NotNull auth:String): Observable<JsonObject> {
        return service.getVotingResponse(auth)
    }
}