package com.example.user.kotlinwithrxjanddaggermvp.presenter

import com.example.user.kotlinwithrxjanddaggermvp.model.DataItem
import com.example.user.kotlinwithrxjanddaggermvp.model.MainViewState1
import com.example.user.kotlinwithrxjanddaggermvp.view.BaseActivity

/**
 * Created by user on 3/9/18.
 */
class ActivityInterfacePresenter {

   interface Presentere : BaseActivity.Presenter<View>{
       fun loadFromApi()
       fun loadFromDb()
       fun loadDetailsFromDb()
       fun deleteItemFromDb()
       fun onGetUsersFail(e: Throwable?): MainViewState1?
   }

    interface View: BaseActivity.View{
        fun onLoadView(listUsers: List<DataItem.User>?)
        fun showProgress()
        fun hideProgress()
        fun deletedItem()
    }
}