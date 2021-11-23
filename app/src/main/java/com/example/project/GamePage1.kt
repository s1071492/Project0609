package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_page1.*




class GamePage1 : AppCompatActivity(),View.OnClickListener {

    lateinit var mper: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page1)

        btnYes.setOnClickListener(this)
        btnNo.setOnClickListener(this)


        ShowPicture()
        mper = MediaPlayer()
        intent=getIntent()

        val playNo= intent.getIntExtra("音檔",1)
        mper.start()
        when(playNo){
            1 -> mper=MediaPlayer.create(this, R.raw.gamepage1)
            3 -> mper=MediaPlayer.create(this, R.raw.gamepage3)
            3_1 -> mper=MediaPlayer.create(this, R.raw.gamepage3_1)
            7 -> mper=MediaPlayer.create(this, R.raw.gamepage7)
            21 -> mper=MediaPlayer.create(this, R.raw.game2page1)
            23 -> mper=MediaPlayer.create(this, R.raw.game2page3)
            27 -> mper=MediaPlayer.create(this, R.raw.game2page7)
        }


    }
    override fun onBackPressed() {
        Toast.makeText(this, "不支援此功能!", Toast.LENGTH_SHORT).show()
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
                21->intent.putExtra("編號", 22)
                23->intent.putExtra("編號", 24)
                27->intent.putExtra("編號", 28)
            }
            when(PictureNo){
                1->intent.putExtra("表情", 1)
                3->intent.putExtra("表情", 1)
                3_1->intent.putExtra("表情", 1)
                7->intent.putExtra("表情", 1)
                21->intent.putExtra("表情", 2)
                23->intent.putExtra("表情", 2)
                27->intent.putExtra("表情", 1)
            }
            when(PictureNo){
                1->intent.putExtra("音檔", 2)
                3->intent.putExtra("音檔", 4)
                3_1->intent.putExtra("音檔", 4)
                7->intent.putExtra("音檔", 8)
                21->intent.putExtra("音檔", 22)
                23->intent.putExtra("音檔", 24)
                27->intent.putExtra("音檔", 28_1)
            }

            //intent.putExtra("表情", 1)//1開心 2難過 3生氣
            startActivity(intent)
        }else if(v.id.equals(R.id.btnNo)) {
            intent=getIntent()
            val PictureNo= intent.getIntExtra("編號",0)
            intent = Intent(this@GamePage1, GamePage2::class.java)
            when (PictureNo) {

                1->intent.putExtra("編號", 2_1)
                3->intent.putExtra("編號", 4_1)
                3_1->intent.putExtra("編號", 4_1)
                7->intent.putExtra("編號", 8_1)
                21->intent.putExtra("編號", 22_1)
                23->intent.putExtra("編號", 24_1)
                27->intent.putExtra("編號", 28)
        }
            when(PictureNo){
                1->intent.putExtra("表情", 2)
                3->intent.putExtra("表情", 2)
                3_1->intent.putExtra("表情", 2)
                7->intent.putExtra("表情", 2)
                21->intent.putExtra("表情", 1)
                23->intent.putExtra("表情", 1)
                27->intent.putExtra("表情", 3)
            }
            when(PictureNo){
                1->intent.putExtra("音檔", 2_1)
                3->intent.putExtra("音檔", 4_1)
                3_1->intent.putExtra("音檔", 4_1)
                7->intent.putExtra("音檔", 8_1)
                21->intent.putExtra("音檔", 22_1)
                23->intent.putExtra("音檔", 24_1)
                27->intent.putExtra("音檔", 28_1)
            }
            startActivity(intent)
        }
    }
    fun ShowPicture(){
        intent=getIntent()
        val PictureNo= intent.getIntExtra("編號",0)
        when (PictureNo){
            1 -> img.setImageResource(R.drawable.gamepage1)
            3 -> img.setImageResource(R.drawable.gamepage3)
            3_1 -> img.setImageResource(R.drawable.gamepage3_1)
            7 -> img.setImageResource(R.drawable.gamepage7)
            21 -> img.setImageResource(R.drawable.game2page1)
            23 -> img.setImageResource(R.drawable.game2page3)
            27 -> img.setImageResource(R.drawable.game2page7)
        }

    }
    fun StartPlay(v: View){
        mper.reset()
        intent=getIntent()
        val playNo= intent.getIntExtra("音檔",1)
        if(v.id.equals(R.id.imgplay1)){
            when(playNo){
                1 -> mper=MediaPlayer.create(this, R.raw.gamepage1)
                3 -> mper=MediaPlayer.create(this, R.raw.gamepage3)
                3_1 -> mper=MediaPlayer.create(this, R.raw.gamepage3_1)
                7 -> mper=MediaPlayer.create(this, R.raw.gamepage7)
                21 -> mper=MediaPlayer.create(this, R.raw.game2page1)
                23 -> mper=MediaPlayer.create(this, R.raw.game2page3)
                27 -> mper=MediaPlayer.create(this, R.raw.game2page7)
            }

            mper.start()
        }else if(v.id.equals(R.id.imgplay2)){
            mper.pause()
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