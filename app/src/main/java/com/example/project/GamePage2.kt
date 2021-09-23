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
import kotlinx.android.synthetic.main.activity_game_page1.*
import kotlinx.android.synthetic.main.activity_game_page2.*

@GlideModule
public final class MyAppGlideModule : AppGlideModule()




class GamePage2 : AppCompatActivity(),View.OnClickListener {



    var MyAns=0
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page2)


        btnhappy.setOnClickListener(this)
        btnai.setOnClickListener(this)
        btnsad.setOnClickListener(this)
        btnangry.setOnClickListener(this)

        mper = MediaPlayer()
        ShowPicture()

        intent=getIntent()
        val playNo= intent.getIntExtra("音檔",2)
        mper.start()
        when(playNo){
            2 -> mper = MediaPlayer.create(this, R.raw.gamepage2)
            2_1 -> mper = MediaPlayer.create(this, R.raw.gamepage2_1)
            4 -> mper = MediaPlayer.create(this, R.raw.gamepage4)
            4_1 -> mper = MediaPlayer.create(this, R.raw.gamepage4_1)
            5   -> mper = MediaPlayer.create(this, R.raw.gamepage5)
            5_1 -> mper = MediaPlayer.create(this, R.raw.gamepage5_1)
            6 -> mper = MediaPlayer.create(this, R.raw.gamepage6)
            8 -> mper = MediaPlayer.create(this, R.raw.gamepage8)
            8_1  -> mper = MediaPlayer.create(this, R.raw.gamepage8_1)
            22  -> mper = MediaPlayer.create(this, R.raw.game2page2)
            22_1  -> mper = MediaPlayer.create(this, R.raw.game2page2_1)
            24  -> mper = MediaPlayer.create(this, R.raw.game2page4)
            24_1  -> mper = MediaPlayer.create(this, R.raw.game2page4_1)
            25  -> mper = MediaPlayer.create(this, R.raw.game2page5)
            26 -> mper = MediaPlayer.create(this, R.raw.game2page6)
            28 -> mper = MediaPlayer.create(this, R.raw.game2page8)
            28_1 -> mper = MediaPlayer.create(this, R.raw.game2page8_1)
            29 -> mper = MediaPlayer.create(this, R.raw.game2page9)
        }
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.btnhappy)){
            MyAns=1
            btnchk()
        }else if(v.id.equals(R.id.btnai)){
            if(txvIsRight.text.equals("正確"))
            {
                intent=getIntent()
                val PictureNo= intent.getIntExtra("編號",0)
                intent = Intent(this@GamePage2, Select::class.java)
                intent.putExtra("表情",txvAnswer.text)
                intent.putExtra("編號",PictureNo)
                startActivity(intent)
            }else{
                Toast.makeText(this, "請先回答正確再進行試試看!", Toast.LENGTH_SHORT).show();
            }
        }else if(v.id.equals(R.id.btnsad)){
            MyAns=2
            btnchk()
        }else if(v.id.equals(R.id.btnangry)){
            MyAns=3
            btnchk()
        }
    }
    fun ShowPicture(){
        intent=getIntent()
        val PictureNo= intent.getIntExtra("編號",0)
        if(PictureNo.equals(9)){
            intent = Intent(this@GamePage2, GamePage9::class.java)
            startActivity(intent)
        }else
        when (PictureNo){
            2 -> img2.setImageResource(R.drawable.gamepage2)
            2_1 -> img2.setImageResource(R.drawable.gamepage2_1)
            4 -> img2.setImageResource(R.drawable.gamepage4)
            4_1 -> img2.setImageResource(R.drawable.gamepage4_1)
            5   -> img2.setImageResource(R.drawable.gamepage5)
            5_1 -> img2.setImageResource(R.drawable.gamepage5_1)
            6 -> img2.setImageResource(R.drawable.gamepage6)
            8 -> img2.setImageResource(R.drawable.gamepage8)
            8_1  -> img2.setImageResource(R.drawable.gamepage8_1)
            22  -> img2.setImageResource(R.drawable.game2page2)
            22_1  -> img2.setImageResource(R.drawable.game2page2_1)
            24  -> img2.setImageResource(R.drawable.game2page4)
            24_1  -> img2.setImageResource(R.drawable.game2page4_1)
            25  -> img2.setImageResource(R.drawable.game2page5)
            26-> img2.setImageResource(R.drawable.game2page6)
            28-> img2.setImageResource(R.drawable.game2page8)
            28_1-> img2.setImageResource(R.drawable.game2page8_1)
            29-> img2.setImageResource(R.drawable.game2page9)

        }
    }
    fun btnchk(){
        intent=getIntent()
        val emotion=intent.getIntExtra("表情",0)
        if(emotion.equals(MyAns)){
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.correct)
            mper.start()
            when(MyAns){
                1->txvAnswer.text="開心"
                2->txvAnswer.text="難過"
                3->txvAnswer.text="生氣"
            }
            Toast.makeText(this, "回答正確!", Toast.LENGTH_SHORT).show();
            txvIsRight.text="正確"
        }
        else {
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.wrong)
            mper.start()
            Toast.makeText(this, "回答錯誤!", Toast.LENGTH_SHORT).show();
            txvIsRight.text="錯誤!請再看一次故事"

        }

    }
    fun StartPlay(v: View){
        mper.reset()
        intent=getIntent()
        val playNo= intent.getIntExtra("音檔",2)
        if(v.id.equals(R.id.imgplay)){
            when(playNo){
                2 -> mper = MediaPlayer.create(this, R.raw.gamepage2)
                2_1 -> mper = MediaPlayer.create(this, R.raw.gamepage2_1)
                4 -> mper = MediaPlayer.create(this, R.raw.gamepage4)
                4_1 -> mper = MediaPlayer.create(this, R.raw.gamepage4_1)
                5   -> mper = MediaPlayer.create(this, R.raw.gamepage5)
                5_1 -> mper = MediaPlayer.create(this, R.raw.gamepage5_1)
                6 -> mper = MediaPlayer.create(this, R.raw.gamepage6)
                8 -> mper = MediaPlayer.create(this, R.raw.gamepage8)
                8_1  -> mper = MediaPlayer.create(this, R.raw.gamepage8_1)
                22  -> mper = MediaPlayer.create(this, R.raw.game2page2)
                22_1  -> mper = MediaPlayer.create(this, R.raw.game2page2_1)
                24  -> mper = MediaPlayer.create(this, R.raw.game2page4)
                24_1  -> mper = MediaPlayer.create(this, R.raw.game2page4_1)
                25  -> mper = MediaPlayer.create(this, R.raw.game2page5)
                26 -> mper = MediaPlayer.create(this, R.raw.game2page6)
                28 -> mper = MediaPlayer.create(this, R.raw.game2page8)
                28_1 -> mper = MediaPlayer.create(this, R.raw.game2page8_1)
                29 -> mper = MediaPlayer.create(this, R.raw.game2page9)
            }
            //mper = MediaPlayer.create(this, R.raw.gamepage2)
            mper.start()
        }else if(v.id.equals(R.id.imgplay3)){
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
/*override fun onClick(v: View) {
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
                intent.putExtra("編號",2)
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

 */