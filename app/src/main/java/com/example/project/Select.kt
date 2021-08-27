package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_draw_check.*
import kotlinx.android.synthetic.main.activity_game_page2.*
import kotlinx.android.synthetic.main.activity_select.*

class Select : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        intent = getIntent()
        var no = intent.getIntExtra("編號",0)
        var emotion = intent.getStringExtra("表情")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        btndraw.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                intent = Intent(this@Select,DrawCheck::class.java)
                intent.putExtra("表情",emotion)
                intent.putExtra("編號",no)
                startActivity(intent)
            }
        })
        btnscan.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                intent = Intent(this@Select,FaceScan::class.java)
                intent.putExtra("表情",emotion)
                intent.putExtra("編號",no)
                startActivity(intent)
            }
        })
    }
}