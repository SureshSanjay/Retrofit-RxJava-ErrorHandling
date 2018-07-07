package com.example.user.kotlinwithrxjanddaggermvp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ListAdapter
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnCheckedChanged
import com.example.user.kotlinwithrxjanddaggermvp.presenter.ActivityInterfacePresenter
import com.example.user.kotlinwithrxjanddaggermvp.Inject.components.DaggerActivityComponents
import com.example.user.kotlinwithrxjanddaggermvp.R
import com.example.user.kotlinwithrxjanddaggermvp.model.DataItem
import com.example.user.kotlinwithrxjanddaggermvp.module.ActivityModule
import com.example.user.kotlinwithrxjanddaggermvp.presenter.ActivityPresenter
import com.jakewharton.rxbinding2.view.clicks
import java.util.logging.Logger
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityInterfacePresenter.View,
                        onItemClickListener{

    override fun itemRemoveClick(user: DataItem.User) {

    }

    override fun itemDetail(userName: String) {
        Logger.getLogger(ActivityPresenter::class.java.name).warning(userName)
    }

    @Inject
    lateinit var presenter: ActivityInterfacePresenter.Presentere

    @BindView(R.id.recycler_view)
    @JvmField var recyclerView :RecyclerView ? = null

    @BindView(R.id.clickBtn)
    lateinit var btnClick :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        injector()
    }

    private fun injector() {
        var activityComponent = DaggerActivityComponents.builder()
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
        presenter.attachView(this)

    }

    @butterknife.OnClick(R.id.clickBtn)
    public final fun btnClickAction(){
         presenter.loadFromApi()
    }
    override fun onLoadView(listUsers: List<DataItem.User>?) {
        Toast.makeText(this,"Success Response",Toast.LENGTH_LONG).show()
        var adapter = ListAdapterView(this, listUsers!!.toMutableList())
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setAdapter(adapter)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
    }
    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletedItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

