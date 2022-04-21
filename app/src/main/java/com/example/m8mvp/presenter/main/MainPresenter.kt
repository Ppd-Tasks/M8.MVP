package com.example.m8mvp.presenter.main

import com.example.m8mvp.model.Post
import com.example.m8mvp.network.RetrofitHttp
import com.example.m8mvp.view.main.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var mainView: MainView) : MainPresenterImpl {

    lateinit var title:String
    lateinit var body:String

    override fun apiPostList() {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                //refreshAdapter(response.body()!!)
                mainView.onPostListSuccess(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                mainView.onPostListFailure(t.toString())
            }
        })
    }

    override fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                //apiPostList()
                mainView.onPostDeleteSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.toString())
            }
        })
    }

    fun apiPostCreate(){
        val post = Post(1,20,title,body)

        RetrofitHttp.postService.createPost(post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                //Logger.d("@@@",response.body().toString())
                //apiPosterListRetrofit()
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                //Logger.d("@@@",t.message.toString())
            }

        })
    }

    fun apiPostUpdate(post: Post){
        RetrofitHttp.postService.updatePost(post.id,post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                //Logger.d("@@@",response.body().toString())
                //apiPosterListRetrofit()
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                //Logger.d("@@@",t.message.toString())

            }

        })
    }
}