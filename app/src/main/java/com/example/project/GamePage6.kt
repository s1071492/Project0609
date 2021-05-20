package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page2.*
import kotlinx.android.synthetic.main.activity_game_page6.*
import kotlinx.android.synthetic.main.activity_game_page6.btnAI
import kotlinx.android.synthetic.main.activity_game_page6.btnAngry
import kotlinx.android.synthetic.main.activity_game_page6.btnHappy
import kotlinx.android.synthetic.main.activity_game_page6.btnSad
import kotlinx.android.synthetic.main.activity_game_page6.txvAnswer
import kotlinx.android.synthetic.main.activity_game_page6.txvIsRight

class GamePage6 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page6)
        btnAngry.setOnClickListener(this)
        btnHappy.setOnClickListener(this)
        btnSad.setOnClickListener(this)
        btnAI.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnAngry)){
            txvAnswer.text=btnAngry.text
            txvIsRight.text="正確"
        }else if(v.id.equals(R.id.btnAI)){
            if(txvIsRight.text.equals("正確"))
            {
                intent = Intent(this@GamePage6, GamePage7::class.java)
                startActivity(intent)
            }else{
                txvIsRight.text="請先回答正確再進行試試看!"
            }
        }else if(v.id.equals(R.id.btnSad)){
            txvAnswer.text=btnSad.text
            txvIsRight.text="錯誤!請再看一次故事"
        }else if(v.id.equals(R.id.btnHappy)){
            txvAnswer.text=btnHappy.text
            txvIsRight.text="錯誤!請再看一次故事"
        }
    }
}