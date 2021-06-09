package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_game_page2.*

@GlideModule
public final class MyAppGlideModule : AppGlideModule()




class GamePage2 : AppCompatActivity(),View.OnClickListener {


    //lateinit var img:ImageView

    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page2)
/*
        img = findViewById(R.id.img2)


        GlideApp.with(this)
            .asBitmap()
            .load(R.drawable.gamepage2)
            .into

 */

        btnhappy.setOnClickListener(this)
        btnai.setOnClickListener(this)
        btnsad.setOnClickListener(this)
        btnangry.setOnClickListener(this)
        mper = MediaPlayer()

    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnhappy)){
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.correct)
            mper.start()
            txvAnswer.text="開心"
            Toast.makeText(this, "您選擇的是開心,回答正確!", Toast.LENGTH_SHORT).show();
            txvIsRight.text="正確"
        }else if(v.id.equals(R.id.btnai)){
            if(txvIsRight.text.equals("正確"))
            {
                intent = Intent(this@GamePage2, Select::class.java)
                intent.putExtra("表情",txvAnswer.text)
                intent.putExtra("頁數",2)
                startActivity(intent)
            }else{
                Toast.makeText(this, "請先回答正確再進行試試看!", Toast.LENGTH_SHORT).show();
            }
        }else if(v.id.equals(R.id.btnsad)){
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.wrong)
            mper.start()
            Toast.makeText(this, "您選擇的是難過,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text="難過"
            txvIsRight.text="錯誤!請再看一次故事"
        }else if(v.id.equals(R.id.btnangry)){
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.wrong)
            mper.start()
            Toast.makeText(this, "您選擇的是生氣,回答錯誤!", Toast.LENGTH_SHORT).show();
            txvAnswer.text="生氣"
            txvIsRight.text="錯誤!請再看一次故事"
        }
    }
    fun StartPlay(v: View){
        mper.reset()

        if(v.id.equals(R.id.imgplay)){
            mper = MediaPlayer.create(this, R.raw.gamepage2)
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