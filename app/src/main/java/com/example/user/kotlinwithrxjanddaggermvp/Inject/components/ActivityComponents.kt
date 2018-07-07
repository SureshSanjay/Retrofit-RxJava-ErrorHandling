package com.example.user.kotlinwithrxjanddaggermvp.Inject.components

import com.example.user.kotlinwithrxjanddaggermvp.view.MainActivity
import com.example.user.kotlinwithrxjanddaggermvp.module.ActivityModule
import com.example.user.kotlinwithrxjanddaggermvp.scope.PerActivityTest
import dagger.Component

/**
 * Created by user on 3/9/18.
 */

@PerActivityTest
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponents {
    fun inject(mainActivity: MainActivity)
}