package com.example.user.kotlinwithrxjanddaggermvp.model

import com.google.gson.JsonObject
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.*


/**
 * Created by tomt on 29/05/17.
 */
data class MainViewState(val dataItem: JsonObject, val userName: String = "", val loading: Boolean = false, val errorMsg: String = "")
data class MainViewState1(val dataItem: JsonObject, val userName: String = "", val loading: Boolean = false, val errorMsg: String = "")