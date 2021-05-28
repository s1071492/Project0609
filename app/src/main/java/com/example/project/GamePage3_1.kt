package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page3_1.*


class GamePage3_1 : AppCompatActivity(),View.OnClickListener {
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page3_1)
        btnbad.setOnClickListener(this)
        btngood.setOnClickListener(this)
        mper = MediaPlayer()
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnbad)){
            intent = Intent(this@GamePage3_1, GamePage4_1::class.java)
            startActivity(intent)
        }else if(v.id.equals(R.id.btngood)){
            intent = Intent(this@GamePage3_1, GamePage4::class.java)
            startActivity(intent)
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