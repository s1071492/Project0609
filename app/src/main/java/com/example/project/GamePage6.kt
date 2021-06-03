package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_page2.*
import kotlinx.android.synthetic.main.activity_game_page4.*
import kotlinx.android.synthetic.main.activity_game_page6.*
import kotlinx.android.synthetic.main.activity_game_page6.btnAI
import kotlinx.android.synthetic.main.activity_game_page6.btnAngry
import kotlinx.android.synthetic.main.activity_game_page6.btnHappy
import kotlinx.android.synthetic.main.activity_game_page6.btnSad
import kotlinx.android.synthetic.main.activity_game_page6.txvAnswer
import kotlinx.android.synthetic.main.activity_game_page6.txvIsRight

class GamePage6 : AppCompatActivity(),View.OnClickListener {
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page6)
        btnAngry.setOnClickListener(this)
        btnHappy.setOnClickListener(this)
        btnSad.setOnClickListener(this)
        btnAI.setOnClickListener(this)
        mper = MediaPlayer()
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnAngry)){
            txvAnswer.text=btnAngry.text
            Toast.makeText(this, "您選擇的是生氣,回答正確!", Toast.LENGTH_SHORT).show();
            txvIsRight.text="正確"
        }else if(v.id.equals(R.id.btnAI)){
            if(txvIsRight.text.equals("正確"))
            {
                intent = Intent(this@GamePage6, DrawCheck::class.java)
                intent.putExtra("表情",txvAnswer.text)
                intent.putExtra("頁數",6)
                startActivity(intent)
            }else{
                Toast.makeText(this, "請先回答正確再進行試試看!", Toast.LENGTH_SHORT).show();
            }
        }else if(v.id.equals(R.id.btnSad)){
            Toast.makeText(this, "您選擇的是難過,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text=btnSad.text
            txvIsRight.text="錯誤!請再看一次故事"
        }else if(v.id.equals(R.id.btnHappy)){
            Toast.makeText(this, "您選擇的是開心,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text=btnHappy.text
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