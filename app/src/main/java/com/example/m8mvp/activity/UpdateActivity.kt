package com.example.m8mvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.m8mvp.R
import com.example.m8mvp.model.Post
import com.example.m8mvp.presenter.update.UpdatePresenter
import com.example.m8mvp.view.update.UpdateView

class UpdateActivity : AppCompatActivity(),UpdateView {
    lateinit var et_title: EditText
    lateinit var et_body: EditText
    lateinit var updatePresenter:UpdatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        initViews()
    }

    private fun initViews() {
        updatePresenter = UpdatePresenter(this)
        et_title = findViewById(R.id.et_title)
        et_body = findViewById(R.id.et_body)
        val btn_cancel = findViewById<Button>(R.id.btn_cancel)
        val btn_post = findViewById<Button>(R.id.btn_update)

        val post = intent.getSerializableExtra("post") as Post

        et_title.setText(post.title)
        et_body.setText(post.body)

        btn_cancel.setOnClickListener {
            finish()
        }
        btn_post.setOnClickListener {
            back(post)
        }
    }

    fun back(post: Post){
        post.title = et_title.text.toString()
        post.body = et_body.text.toString()

        updatePresenter.apiPostUpdate(post)
    }

    override fun onPostCreateSuccess(post: Post) {
        val intent = Intent()
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onPostCreateFailure(error: String) {
        Log.d("@@@", "onPostCreateFailure: $error")
    }
}