package com.example.m8mvp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m8mvp.R
import com.example.m8mvp.adapter.PostAdapter
import com.example.m8mvp.model.Post
import com.example.m8mvp.presenter.main.MainPresenter
import com.example.m8mvp.utils.Utils
import com.example.m8mvp.view.main.MainView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var recyclerView: RecyclerView
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainPresenter = MainPresenter(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))
        mainPresenter.apiPostList()

        Utils.setItemTouchHelperRightToLeft(this,recyclerView)

        val btn_floating:FloatingActionButton = findViewById(R.id.btn_floating)
        btn_floating.setOnClickListener {
            callCreateActivity()
        }
    }

    private fun callCreateActivity() {
        val intent = Intent(this,CreateActivity::class.java)
        createLauncher.launch(intent)
    }

    var createLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            mainPresenter.apiPostList()
        }
    }

    fun callUpdateActivity(post: Post?){
        val intent = Intent(this,UpdateActivity::class.java)
        intent.putExtra("post",post)
        updateLauncher.launch(intent)
    }

    var updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            mainPresenter.apiPostList()
        }
    }

    fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this, posters)
        recyclerView.setAdapter(adapter)
    }

    override fun onPostListSuccess(posts: ArrayList<Post>?) {
        refreshAdapter(posts!!)
    }

    override fun onPostListFailure(error: String) {

    }

    override fun onPostDeleteSuccess(posts: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {

    }

    /**/
}