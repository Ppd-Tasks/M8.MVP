package com.example.m8mvp.presenter.update

import com.example.m8mvp.model.Post
import com.example.m8mvp.network.RetrofitHttp
import com.example.m8mvp.view.create.CreateView
import com.example.m8mvp.view.main.MainView
import com.example.m8mvp.view.update.UpdateView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePresenter(var updateView: UpdateView) : UpdatePresenterImpl {

    override fun apiPostUpdate(post: Post){

        RetrofitHttp.postService.createPost(post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                updateView.onPostCreateSuccess(post)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                updateView.onPostCreateFailure(t.localizedMessage)
            }

        })
    }
}