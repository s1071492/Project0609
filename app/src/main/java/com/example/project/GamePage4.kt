package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page4.*



class GamePage4 : AppCompatActivity(),View.OnClickListener {
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page4)
        btnHappy.setOnClickListener(this)
        btnSad.setOnClickListener(this)
        btnAngry.setOnClickListener(this)
        btnAI.setOnClickListener(this)
        mper = MediaPlayer()
    }


    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnHappy)){
            txvAnswer.text=btnHappy.text
            txvIsRight.text="正確"
        }else if(v.id.equals(R.id.btnAI)){
            if(txvIsRight.text.equals("正確"))
            {
                intent = Intent(this@GamePage4, DrawCheck::class.java)
                intent.putExtra("表情",txvAnswer.text)
                intent.putExtra("頁數",4)
                startActivity(intent)
            }else{
                txvIsRight.text="請先回答正確再進行試試看!"
            }
        }else if(v.id.equals(R.id.btnSad)){
            txvAnswer.text=btnSad.text
            txvIsRight.text="錯誤!請再看一次故事"
        }else if(v.id.equals(R.id.btnAngry)){
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