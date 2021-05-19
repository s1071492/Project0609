package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page3.*

class GamePage3 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page3)
        btngood.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btngood)){
            intent = Intent(this@GamePage3, GamePage4::class.java)
            startActivity(intent)
        }
    }
}