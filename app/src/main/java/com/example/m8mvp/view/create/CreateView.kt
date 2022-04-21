package com.example.m8mvp.view.create

import com.example.m8mvp.model.Post

interface CreateView {

    fun onPostCreateSuccess(post:Post)
    fun onPostCreateFailure(error:String)
}