package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgStart.setOnClickListener(this)
        imgExit.setOnClickListener(this)
        mper = MediaPlayer()
    }

    override fun onClick(v:View) {
        if(v.id.equals(R.id.imgStart)){
            intent = Intent(this@MainActivity, GamePage1::class.java)
            startActivity(intent)

        }else if(v.id.equals(R.id.imgExit)){
            System.exit(0)
        }
    }
    fun StartPlay(v: View){
        mper.reset()

        if(v.id.equals(R.id.imgStart)){
            mper = MediaPlayer.create(this, R.raw.button)
            mper.start()
        }else{
            mper = MediaPlayer.create(this, R.raw.button)
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