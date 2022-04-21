package com.example.m8mvp.view.main

import com.example.m8mvp.model.Post

interface MainView {

    fun onPostListSuccess(posts:ArrayList<Post>?)
    fun onPostListFailure(error:String)

    fun onPostDeleteSuccess(posts:Post?)
    fun onPostDeleteFailure(error:String)
}