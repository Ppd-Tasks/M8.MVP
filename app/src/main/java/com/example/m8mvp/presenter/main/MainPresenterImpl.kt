package com.example.m8mvp.presenter.main

import com.example.m8mvp.model.Post

interface MainPresenterImpl {

    fun apiPostList()
    fun apiPostDelete(post: Post)

}