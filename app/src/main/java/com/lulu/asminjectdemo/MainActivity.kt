package com.lulu.asminjectdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtn1.setOnClickListener {
            println("按钮1 点击了！！！")
        }
        mBtn2.setOnClickListener {
            println("按钮2 点击了！！！")
        }
    }
}
