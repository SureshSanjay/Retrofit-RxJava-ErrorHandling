package com.example.user.kotlinwithrxjanddaggermvp.service

import com.example.user.kotlinwithrxjanddaggermvp.model.DataItem
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.*

/**
 * Created by user on 3/9/18.
 */
interface ServiceInterface {
    @GET("getmyfriends")
    fun getMyFriends(): Observable<DataItem>

    @FormUrlEncoded
    @POST("token")
    fun getToken(@FieldMap options:Map<String, String> ) :Observable<JsonObject>

    @GET("programmes")
    fun getVotingResponse(@Header("Authorization") auth: String):Observable<JsonObject>
}