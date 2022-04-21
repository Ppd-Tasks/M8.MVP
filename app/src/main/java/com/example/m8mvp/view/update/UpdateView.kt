package com.example.m8mvp.view.update

import com.example.m8mvp.model.Post

interface UpdateView {

    fun onPostCreateSuccess(post:Post)
    fun onPostCreateFailure(error:String)
}