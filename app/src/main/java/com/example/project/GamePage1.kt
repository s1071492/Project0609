package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page1.*


class GamePage1 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page1)
        btnwake.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnwake)){
            intent = Intent(this@GamePage1, GamePage2::class.java)
            startActivity(intent)
        }else if(v.id.equals(R.id.btnsleep)){
            TODO()
        }
    }
}