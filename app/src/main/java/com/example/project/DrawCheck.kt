package com.example.project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.ml.Drawface
import kotlinx.android.synthetic.main.activity_draw_check.*
import org.tensorflow.lite.support.image.TensorImage


class DrawCheck : AppCompatActivity() {

    var ans = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_check)
        btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                handv.path.reset()
                handv.invalidate()
            }
        })
        btnCheck.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {

                val b = Bitmap.createBitmap(handv.measuredWidth, handv.measuredHeight,
                    Bitmap.Config.ARGB_8888)
                val c = Canvas(b)
                handv.draw(c)
                classifyDrawing(b)
                if (ans.equals("1")){
                    intent = getIntent()
                    var no = intent.getIntExtra("頁數",0)
                    if(no ==2) intent = Intent(this@DrawCheck, GamePage3::class.java)
                    if(no ==2_1) intent = Intent(this@DrawCheck, GamePage3_1::class.java)//+
                    else if(no==4)intent = Intent(this@DrawCheck, GamePage5::class.java)
                    else if(no==5)intent = Intent(this@DrawCheck, GamePage6::class.java)
                    else if(no==6)intent = Intent(this@DrawCheck, GamePage7::class.java)
                    else if(no==8)intent = Intent(this@DrawCheck, GamePage9::class.java)

                    startActivity(intent)
                    finish()
                }
                else if(ans.equals("0")){

                }
            }
        })

        handv.setOnTouchListener(object:View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent): Boolean {
                var xPos = event.getX()
                var yPos = event.getY()
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> handv.path.moveTo(xPos, yPos)
                    MotionEvent.ACTION_MOVE -> handv.path.lineTo(xPos, yPos)
                    MotionEvent.ACTION_UP -> {
                        //將handv轉成Bitmap

                    }
                }
                handv.invalidate()
                return true
            }
        })

    }
    fun classifyDrawing(bitmap : Bitmap) {
        intent = getIntent()
        var face = intent.getStringExtra("表情")

        val model = Drawface.newInstance(this)

        // Creates inputs for reference.
        val image = TensorImage.fromBitmap(bitmap)

        // Runs model inference and gets result.
        val outputs = model.process(image)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score } // 排序，高匹配率優先
            }.take(1)  //取最高的1個

        var Result:String = ""

        when (outputs[0].label) {
            "smile" -> Result = "開心"
            "angry" -> Result = "生氣"
            "sad" -> Result = "難過"
        }
        if(Result.equals(face)) ans = "1"
        else ans = "0"

        Result += ": " + String.format("%.1f%%", outputs[0].score * 100.0f)


        // Releases model resources if no longer used.
        model.close()
        Toast.makeText(this, Result, Toast.LENGTH_SHORT).show()

    }



}