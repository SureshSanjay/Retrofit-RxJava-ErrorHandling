package com.example.user.kotlinwithrxjanddaggermvp.view

import com.example.user.kotlinwithrxjanddaggermvp.model.DataItem

interface onItemClickListener {
    fun itemRemoveClick(user: DataItem.User)
    fun itemDetail(userId : String)
}
