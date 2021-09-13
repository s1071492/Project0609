package com.example.project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.ml.Drawfinal
import kotlinx.android.synthetic.main.activity_draw_check.*
import org.tensorflow.lite.support.image.TensorImage


class DrawCheck : AppCompatActivity() {

    var ans = "0"
    lateinit var mper: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_check)
        mper = MediaPlayer()
        btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                handv.path.reset()
                handv.invalidate()
            }
        })
        btnCheck.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                var practice = intent.getIntExtra("練習",0)
                val b = Bitmap.createBitmap(handv.measuredWidth, handv.measuredHeight,
                    Bitmap.Config.ARGB_8888)
                val c = Canvas(b)
                handv.draw(c)
                classifyDrawing(b)
                if(practice == 0) {
                    if (ans.equals("1")) {
                        intent = getIntent()
                        var no = intent.getIntExtra("編號", 0)
                        CheckAns(ans)
                        if (no == 2) {
                            intent = Intent(this@DrawCheck, GamePage1::class.java)
                            intent.putExtra("編號", 3)
                            intent.putExtra("音檔", 3)
                        } else if (no == 2_1) {
                            intent = Intent(this@DrawCheck, GamePage1::class.java)
                            intent.putExtra("編號", 3_1)
                            intent.putExtra("音檔", 3)
                        } else

                            if (no == 4) {
                                intent = Intent(this@DrawCheck, GamePage2::class.java)
                                intent.putExtra("編號", 5)
                                intent.putExtra("表情", 2)
                                intent.putExtra("音檔", 5)
                            } else
                                if (no == 4_1) {
                                    intent = Intent(this@DrawCheck, GamePage2::class.java)
                                    intent.putExtra("編號", 5_1)
                                    intent.putExtra("表情", 3)
                                    intent.putExtra("音檔", 5)

                                } else
                                //+
                                    if (no == 5) {
                                        intent = Intent(this@DrawCheck, GamePage2::class.java)
                                        intent.putExtra("編號", 6)
                                        intent.putExtra("表情", 3)
                                        intent.putExtra("音檔", 6)
                                    } else
                                        if (no == 5_1) {
                                            intent = Intent(this@DrawCheck, GamePage2::class.java)
                                            intent.putExtra("編號", 6)
                                            intent.putExtra("表情", 3)
                                            intent.putExtra("音檔", 6)
                                        } else
                                            if (no == 6) {
                                                intent =
                                                    Intent(this@DrawCheck, GamePage1::class.java)
                                                intent.putExtra("編號", 7)
                                                intent.putExtra("音檔", 7)
                                            } else
                                                if (no == 8) {
                                                    intent = Intent(
                                                        this@DrawCheck,
                                                        GamePage2::class.java
                                                    )
                                                    intent.putExtra("編號", 9)
                                                } else
                                                    if (no == 8_1) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage2::class.java
                                                        )
                                                        intent.putExtra("編號", 9)
                                                    } else if (no == 22) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage1::class.java
                                                        )
                                                        intent.putExtra("編號", 23)
                                                        intent.putExtra("音檔", 23)
                                                    } else if (no == 22_1) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage1::class.java
                                                        )
                                                        intent.putExtra("編號", 23)
                                                        intent.putExtra("音檔", 23)
                                                    } else if (no == 24) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage2::class.java
                                                        )
                                                        intent.putExtra("編號", 25)
                                                        intent.putExtra("表情", 3)
                                                        intent.putExtra("音檔", 2)
                                                    } else if (no == 24_1) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage2::class.java
                                                        )
                                                        intent.putExtra("編號", 25)
                                                        intent.putExtra("表情", 3)
                                                        intent.putExtra("音檔", 2)
                                                    } else if (no == 25) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage2::class.java
                                                        )
                                                        intent.putExtra("編號", 26)
                                                        intent.putExtra("表情", 1)
                                                        intent.putExtra("音檔", 2)
                                                    } else if (no == 26) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage1::class.java
                                                        )
                                                        intent.putExtra("編號", 27)
                                                        intent.putExtra("音檔", 27)
                                                    } else if (no == 28) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage2::class.java
                                                        )
                                                        intent.putExtra("編號", 29)
                                                        intent.putExtra("表情", 1)
                                                        intent.putExtra("音檔", 2)
                                                    } else if (no == 28_1) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage2::class.java
                                                        )
                                                        intent.putExtra("編號", 25)
                                                        intent.putExtra("表情", 1)
                                                        intent.putExtra("音檔", 2)
                                                    } else if (no == 210) {
                                                        intent = Intent(
                                                            this@DrawCheck,
                                                            GamePage9::class.java
                                                        )
                                                    }

                        //+

                        startActivity(intent)
                        finish()
                    } else if (ans.equals("0")) {

                    }
                }
                else if(practice == 1){}
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
        var emotion = intent.getStringExtra("表情")
        var practice = intent.getIntExtra("練習",0)
        val model = Drawfinal.newInstance(this)

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
        if(Result.equals(emotion)) ans = "1"
        else ans = "0"

        Result += ": " + String.format("%.1f%%", outputs[0].score * 100.0f)


        // Releases model resources if no longer used.
        model.close()
        if(practice == 1) {
            Toast.makeText(this, Result, Toast.LENGTH_SHORT).show()
        }

    }
    fun CheckAns(ans : String){
        if(ans.equals("1")){
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.correct)
            mper.start()
        }
        else{
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.wrong)
            mper.start()
        }
    }



}