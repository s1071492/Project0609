package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_page2.*



class GamePage2 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page2)
        btnHappy.setOnClickListener(this)
        btnAI.setOnClickListener(this)
        btnSad.setOnClickListener(this)
        btnAngry.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnHappy)){
            txvAnswer.text=btnHappy.text
            Toast.makeText(this, "您選擇的是開心,回答正確!", Toast.LENGTH_SHORT).show();
            txvIsRight.text="正確"
        }else if(v.id.equals(R.id.btnAI)){
            if(txvIsRight.text.equals("正確"))
            {
                intent = Intent(this@GamePage2, DrawCheck::class.java)
                intent.putExtra("表情",txvAnswer.text)
                intent.putExtra("頁數",2)
                startActivity(intent)
            }else{
                Toast.makeText(this, "請先回答正確再進行試試看!", Toast.LENGTH_SHORT).show();
            }
        }else if(v.id.equals(R.id.btnSad)){
            Toast.makeText(this, "您選擇的是難過,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text=btnSad.text
            txvIsRight.text="錯誤!請再看一次故事"
        }else if(v.id.equals(R.id.btnAngry)){
            Toast.makeText(this, "您選擇的是生氣,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text=btnAngry.text
            txvIsRight.text="錯誤!請再看一次故事"
        }
    }
}