package com.example.user.kotlinwithrxjanddaggermvp.view


/**
 * Created by user on 3/9/18.
 */
class BaseActivity {

    interface Presenter<in T>{
        fun subscribe()
        fun unSubscribe()
        fun attachView(view: T)
    }

    interface View{
    }
}