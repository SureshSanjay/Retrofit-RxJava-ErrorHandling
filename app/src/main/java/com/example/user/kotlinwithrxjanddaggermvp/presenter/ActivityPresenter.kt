package com.example.user.kotlinwithrxjanddaggermvp.presenter

import android.util.Log
import com.example.user.kotlinwithrxjanddaggermvp.model.MainViewState
import com.example.user.kotlinwithrxjanddaggermvp.model.MainViewState1
import com.example.user.kotlinwithrxjanddaggermvp.service.ServiceCall
import com.example.user.kotlinwithrxjanddaggermvp.utils.UtilsJava
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.logging.Logger


/**
 * Created by user on 3/9/18.
 */
class ActivityPresenter : ActivityInterfacePresenter.Presentere {
    override fun onGetUsersFail(e: Throwable?): MainViewState1? {
        var jsonRes = ""
        if (e is HttpException){
            jsonRes = e.response().errorBody().string()
            Log.d("",""+jsonRes);
        }
        return MainViewState1(dataItem = JsonObject(),userName = "", loading = false, errorMsg= jsonRes)
    }

    private val subscriptions = CompositeDisposable()

    private var grant_type = "grant_type"

    private var password = "password"

    var access_token = "access_token"
    var token_type = "token_type"

    //test Server
    val sKey_luckydraw = "+6hVcLhU1UjQIRznqWwZRKr9O2Fycwl5djJOTW92M2xVUzdjQzZ3WkV5YXBxNFVya1pUaUpiQzJUTVhQTWdiQkJxNEZ0azQxYXpva0g2TDFoZm1PY1VjM1FFSjBpZ01oWFpuQTlQODllYklVeGN2V0JncnE0TWpxT0J6b2JFVDNtR01yU0syU2htdEFNSEd4U3d5Q3lnbkt6N3Y3WlVpYUlPcnFQRUQwb1pKUmRqZEc1QlZvUFFFR2NDMlZZNDNNRFQxOGFaaE9zOFlaV1dxUWRwQ1g4M0sxSmxPTkxzYnRmN05LcmZxZmVHNVIyb3VHVmwydDJmTktJOXl5TFVDQWdvNFpJZ2o3OWczMjgzOWUxSDFrenMxQWZiNzVkOTI1LWZlYmEtNDdkMi1hZWYwLTA2MzUxYWE3N2NjNQ=="
    val aKey_luckydraw = "2U10vAJ0oCL3mau6KAfFwg=="

    private lateinit var view: ActivityInterfacePresenter.View

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unSubscribe() {
        subscriptions.clear()
    }

    override fun loadFromApi() {
        getDataItem()
    }

    private fun getDataItem() {
       var observableData = ServiceCall().loadData()
        //updateView(observableData)
        generateTokens()
    }

    private fun generateTokens(){
        val map = HashMap<String, String>()
        map[grant_type] = password
        map[password] = UtilsJava.hmacSha1(aKey_luckydraw, sKey_luckydraw)
        var observableData = ServiceCall().generateToken(map)
        updateView(observableData) ;
    }

    private fun updateView(observableData: Observable<JsonObject>) {

        observableData.map { items -> MainViewState(items, loading = false) }
                .onErrorReturn { error -> MainViewState(JsonObject(), loading = false, errorMsg = error.message!!) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(MainViewState(dataItem = JsonObject(),loading = true))
                .subscribe { render(it) }
    }


    private fun render1(viewstate: MainViewState1?) {
        System.out.print("Success")
    }
    private fun render(viewstate: MainViewState?) {


        if(!viewstate!!.loading) {
            var auth:String = UtilsJava.getTokenDateFromKey(viewstate!!.dataItem.toString(), token_type) + " " +
                    UtilsJava.getTokenDateFromKey(viewstate!!.dataItem.toString(), access_token)
            getVotingRequest(auth)
            System.out.print(""+viewstate.dataItem)
        }

    }

    private fun updateView1(observableData: Observable<JsonObject>) {
        /* var subscribe = observableData.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe({
                     dataitem : DataItem? ->
                     var listUsers = dataitem!!.user
                     view.onLoadView(listUsers)
                 })
         subscriptions.add(subscribe)*/


         observableData.map { items -> MainViewState1(items, loading = false) }
                .onErrorReturn  {e ->
                    if (e is RuntimeException) {
                        throw e
                    } else {
                        onGetUsersFail(e)
                    }  }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(MainViewState1(dataItem = JsonObject(), loading = true))
                .subscribe { render1(it) }
    }

    private fun getVotingRequest(auth: String) {
        //var observableData = ServiceCall().getVotingResponse(auth)

        var observableData = ServiceCall().getVotingResponse(auth)
        updateView1(observableData)
    }

    override fun attachView(view: ActivityInterfacePresenter.View) {
        this.view= view  //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadFromDb() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDetailsFromDb() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItemFromDb() {
        Logger.getLogger(ActivityPresenter::class.java.name).warning("Welcome Suresh..")//To change body of created functions use File | Settings | File Templates.
    }


}


