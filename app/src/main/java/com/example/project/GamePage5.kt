package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_page2.*
import kotlinx.android.synthetic.main.activity_game_page4.*
import kotlinx.android.synthetic.main.activity_game_page5.*
import kotlinx.android.synthetic.main.activity_game_page5.btnAI
import kotlinx.android.synthetic.main.activity_game_page5.btnAngry
import kotlinx.android.synthetic.main.activity_game_page5.btnHappy
import kotlinx.android.synthetic.main.activity_game_page5.btnSad
import kotlinx.android.synthetic.main.activity_game_page5.txvAnswer
import kotlinx.android.synthetic.main.activity_game_page5.txvIsRight


class GamePage5 : AppCompatActivity(),View.OnClickListener {
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page5)
        btnSad.setOnClickListener(this)
        btnHappy.setOnClickListener(this)
        btnAngry.setOnClickListener(this)
        btnAI.setOnClickListener(this)
        mper = MediaPlayer()
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnSad)){
            txvAnswer.text=btnSad.text
            Toast.makeText(this, "您選擇的是難過,回答正確!", Toast.LENGTH_SHORT).show();
            txvIsRight.text="正確"
        }else if(v.id.equals(R.id.btnAI)){
            if(txvIsRight.text.equals("正確"))
            {
                intent = Intent(this@GamePage5, DrawCheck::class.java)
                intent.putExtra("表情",txvAnswer.text)
                intent.putExtra("頁數",5)
                startActivity(intent)
            }else{
                Toast.makeText(this, "請先回答正確再進行試試看!", Toast.LENGTH_SHORT).show();
            }
        }else if(v.id.equals(R.id.btnHappy)){
            Toast.makeText(this, "您選擇的是開心,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text=btnHappy.text
            txvIsRight.text="錯誤!請再看一次故事"
        }else if(v.id.equals(R.id.btnAngry)){
            Toast.makeText(this, "您選擇的是生氣,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text=btnAngry.text
            txvIsRight.text="錯誤!請再看一次故事"
        }
    }
    fun StartPlay(v: View){
        mper.reset()

        if(v.id.equals(R.id.imgplay1)){
            mper = MediaPlayer.create(this, R.raw.gamepage1)
            mper.start()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        if(mper != null) {
            mper.release()
        }
    }

    override fun onPause() {
        super.onPause()
        if(mper != null && mper.isPlaying()){
            mper.pause()
        }
        else{
            mper.reset()
        }
    }

    override fun onResume() {
        super.onResume()
        if(mper != null){
            mper.start()
        }
    }
    }
