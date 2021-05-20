package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page9.*

class GamePage9 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page9)
        btnRe.setOnClickListener(this)
        //btnEnd.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnRe)){
            intent = Intent(this@GamePage9, MainActivity::class.java)
            startActivity(intent)
        }
    }
}