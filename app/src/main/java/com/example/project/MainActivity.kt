package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnExit.setOnClickListener(this)
        btnStart.setOnClickListener(this)

    }

    override fun onClick(v:View) {
        if(v.id.equals(R.id.btnStart)){
            intent = Intent(this@MainActivity, GamePage1::class.java)
            startActivity(intent)

        }else if(v.id.equals(R.id.btnExit)){
            System.exit(0)
        }
    }
}