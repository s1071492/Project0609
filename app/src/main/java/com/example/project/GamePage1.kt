package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_page1.*




class GamePage1 : AppCompatActivity(),View.OnClickListener {
    var playNo=0
    lateinit var mper: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page1)
        btnYes.setOnClickListener(this)
        btnNo.setOnClickListener(this)
        mper = MediaPlayer()
        ShowPicture()

    }

    override fun onClick(v: View) {
        intent=getIntent()
        val PictureNo= intent.getIntExtra("編號",0)

        if(v.id.equals(R.id.btnYes)){

            intent = Intent(this@GamePage1, GamePage2::class.java)
            when (PictureNo) {
                1->intent.putExtra("編號", 2)
                3->intent.putExtra("編號", 4)
                3_1->intent.putExtra("編號", 4)
                7->intent.putExtra("編號", 8)
            }
            when(PictureNo){
                1->intent.putExtra("表情", 1)
                3->intent.putExtra("表情", 1)
                3_1->intent.putExtra("表情", 1)
                7->intent.putExtra("表情", 1)
            }

            //intent.putExtra("表情", 1)//1開心 2難過 3生氣
            startActivity(intent)
        }else if(v.id.equals(R.id.btnNo)) {
            intent=getIntent()
            var PictureNo= intent.getIntExtra("編號",0)
            intent = Intent(this@GamePage1, GamePage2::class.java)
            when (PictureNo) {

                1->intent.putExtra("編號", 2_1)
                3->intent.putExtra("編號", 4_1)
                3_1->intent.putExtra("編號", 4_1)
                7->intent.putExtra("編號", 8_1)
        }
            when(PictureNo){
                1->intent.putExtra("表情", 2)
                3->intent.putExtra("表情", 2)
                3_1->intent.putExtra("表情", 2)
                7->intent.putExtra("表情", 2)
            }
            startActivity(intent)
        }
    }
    fun ShowPicture(){
        intent=getIntent()
        var PictureNo= intent.getIntExtra("編號",0)
        when (PictureNo){
            1 -> img.setImageResource(R.drawable.gamepage1)
            3 -> img.setImageResource(R.drawable.gamepage3)
            3_1 -> img.setImageResource(R.drawable.gamepage3_1)
            7 -> img.setImageResource(R.drawable.gamepage7)

        }

    }
    fun StartPlay(v: View){
        mper.reset()
        if(v.id.equals(R.id.imgplay1)){
            when(playNo){
                1 -> MediaPlayer.create(this, R.raw.gamepage1)
                3 -> MediaPlayer.create(this, R.raw.gamepage3)
                3_1 -> MediaPlayer.create(this, R.raw.gamepage1)
                7 -> MediaPlayer.create(this, R.raw.gamepage7)
            }
            //mper = MediaPlayer.create(this, R.raw.gamepage1)
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