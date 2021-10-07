package com.example.project

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_page2.*
import kotlinx.android.synthetic.main.activity_game_page9.*


class GamePage9 : AppCompatActivity(),View.OnClickListener {
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page9)
        imghome.setOnClickListener(this)
        mper = MediaPlayer()
        mper = MediaPlayer.create(this, R.raw.end)
        mper.start()
        //btnEnd.setOnClickListener(this)
    }
    override fun onBackPressed() {
        Toast.makeText(this, "不支援此功能!", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        if(v.id.equals(R.id.imghome)){
            intent = Intent(this@GamePage9, MainActivity::class.java)
            startActivity(intent)

        }
    }

}