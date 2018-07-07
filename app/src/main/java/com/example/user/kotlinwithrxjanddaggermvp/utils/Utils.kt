package com.example.user.kotlinwithrxjanddaggermvp.utils

import android.app.Fragment
import android.app.FragmentManager

/**
 * Created by user on 3/10/18.
 */
class Utils {
    public fun addFragmentToActivity(fragmentManager: FragmentManager,
                                     fragment: Fragment, frameId: Int, fragmentTag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, fragmentTag).addToBackStack("")
        transaction.commit()
    }
}