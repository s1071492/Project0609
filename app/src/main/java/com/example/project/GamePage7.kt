package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page7.*

class GamePage7 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page7)
        btnDo.setOnClickListener(this)
        btnPlayToy.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnDo)){
            intent = Intent(this@GamePage7, GamePage8::class.java)
            startActivity(intent)
        }else if(v.id.equals(R.id.btnPlayToy)){
            intent = Intent(this@GamePage7, GamePage8_1::class.java)
            startActivity(intent)
        }
    }
}