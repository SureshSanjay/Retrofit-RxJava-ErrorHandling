package com.example.user.kotlinwithrxjanddaggermvp.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.user.kotlinwithrxjanddaggermvp.R
import com.example.user.kotlinwithrxjanddaggermvp.model.DataItem

/**
 * Created by user on 3/9/18.
 */
class ListAdapterView(private val context: Context, private val listedItem: MutableList<DataItem.User>) :
                    RecyclerView.Adapter<ListAdapterView.ListViewHolder>(){

    private val listener: onItemClickListener

    init {
        this.listener = context as onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)
        return ListAdapterView.ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listedItem.size
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        var friends = listedItem[position]
        holder!!.firsts!!.setText(friends.name)
        holder!!.seconds!!.setText(friends.email)

        holder!!.firsts!!.setOnClickListener{
            listener.itemDetail(friends.name!!)
        }
    }


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @BindView(R.id.first)
        @JvmField var firsts :TextView?= null

        @BindView(R.id.second)
        @JvmField var seconds:TextView?= null

        init {
            ButterKnife.bind(this,itemView)
        }


    }
}