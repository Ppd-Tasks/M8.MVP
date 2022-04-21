package com.example.m8mvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.m8mvp.R
import com.example.m8mvp.model.Post
import com.example.m8mvp.presenter.create.CreatePresenter
import com.example.m8mvp.view.create.CreateView

class CreateActivity : AppCompatActivity(),CreateView {
    lateinit var et_title: EditText
    lateinit var et_body: EditText
    lateinit var createPresenter: CreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()
    }

    private fun initViews() {
        createPresenter = CreatePresenter(this)
        et_title = findViewById(R.id.et_title)
        et_body = findViewById(R.id.et_body)
        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        val btn_post = findViewById<Button>(R.id.btn_post)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_post.setOnClickListener {
            back()
        }
    }

    fun back(){
        val title:String = et_title.text.toString()
        val body:String = et_body.text.toString()

        val post = Post(1,2,title,body)

        createPresenter.apiPostCreate(post)
    }

    override fun onPostCreateSuccess(post: Post) {
        val intent = Intent()
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onPostCreateFailure(error: String) {
        Log.d("@@@", "onPostCreateFailure: $error")
        finish()
    }
}