package com.example.user.kotlinwithrxjanddaggermvp.module

import android.app.Activity
import com.example.user.kotlinwithrxjanddaggermvp.presenter.ActivityInterfacePresenter
import com.example.user.kotlinwithrxjanddaggermvp.presenter.ActivityPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by user on 3/9/18.
 */

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivityContext() : Activity {
        return activity
    }

    @Provides
    fun getFriendsPresenter() : ActivityInterfacePresenter.Presentere{
        return ActivityPresenter()
    }
}