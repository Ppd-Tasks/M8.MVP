package com.example.m8mvp.presenter.create

import com.example.m8mvp.model.Post
import com.example.m8mvp.network.RetrofitHttp
import com.example.m8mvp.view.create.CreateView
import com.example.m8mvp.view.main.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePresenter(var createView: CreateView) : CreatePresenterImpl {

    override fun apiPostCreate(post: Post){

        RetrofitHttp.postService.createPost(post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                createView.onPostCreateSuccess(post)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                createView.onPostCreateFailure(t.localizedMessage)
            }

        })
    }
}