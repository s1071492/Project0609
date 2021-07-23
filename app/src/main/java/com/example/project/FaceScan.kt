package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Size
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_face_scan.*
import kotlinx.android.synthetic.main.activity_main.*
import org.tensorflow.lite.support.image.TensorImage
import com.example.project.ml.Model01
import kotlinx.android.synthetic.main.activity_select.*

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import 	android.os.*
import java.util.*


// Listener for the result of the ImageAnalyzer
typealias ImageProxyListener = (bmp: Bitmap) -> Unit

class FaceScan : AppCompatActivity(),PermissionListener {
    private lateinit var cameraExecutor: ExecutorService
    var ans = "0"

    private class ImageAnalyzer(ctx: Context, private val listener: ImageProxyListener) :
        ImageAnalysis.Analyzer {
        /**
         * Convert Image Proxy to Bitmap
         */
        private val yuvToRgbConverter = ScanAI(ctx)
        private lateinit var bitmapBuffer: Bitmap
        private lateinit var rotationMatrix: Matrix

        override fun analyze(imageProxy: ImageProxy) {
            // Convert Image to Bitmap
            val bmp:Bitmap? = toBitmap(imageProxy)

            if (bmp != null) {
                listener(bmp)
            }
            // Close the image,this tells CameraX to feed the next image to the analyzer
            imageProxy.close()
        }

        @SuppressLint("UnsafeExperimentalUsageError")
        private fun toBitmap(imageProxy: ImageProxy): Bitmap? {
            val image = imageProxy.image ?: return null
            // Initialise Buffer
            if (!::bitmapBuffer.isInitialized) {
                // The image rotation and RGB image buffer are initialized only once
                rotationMatrix = Matrix()
                rotationMatrix.postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
                bitmapBuffer = Bitmap.createBitmap(
                    imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
                )
            }

            // Pass image to an image analyser
            yuvToRgbConverter.yuvToRgb(image, bitmapBuffer)

            // Create the Bitmap in the correct orientation
            return Bitmap.createBitmap(bitmapBuffer, 0, 0,
                bitmapBuffer.width, bitmapBuffer.height, rotationMatrix, false
            )
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_scan)


        // Request camera permissions
        Dexter.withContext(this)
            .withPermission(android.Manifest.permission.CAMERA)
            .withListener(this)
            .check()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }
    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        startCamera()


    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        if (p0!!.isPermanentlyDenied) {
            Toast.makeText(this, "您永久拒絕拍照權限", Toast.LENGTH_SHORT).show()
            var it: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            var uri: Uri = Uri.fromParts("package", getPackageName(), null)
            it.setData(uri)
            startActivity(it)
        }
        else{
            Toast.makeText(this, "您拒絕拍照權限，無法使用本App",
                Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        p1?.continuePermissionRequest()
    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.createSurfaceProvider())
                }


            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            //val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA  //自拍

            // Set up the image analysis use case which will process frames in real time
            val imageAnalyzer = ImageAnalysis.Builder()
                .setTargetResolution(Size(224, 224))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            /*
            imageAnalyzer.setAnalyzer(cameraExecutor,  { image ->
                txv.text = image.imageInfo.rotationDegrees.toString()
            })
             */

            //分析... 藉由ImageAnalyzer，將ImageProxy轉為Bitmap
            imageAnalyzer.setAnalyzer(cameraExecutor, FaceScan.ImageAnalyzer(this) { bitmap ->
                val model = Model01.newInstance(this)
                // Creates inputs for reference.
                val image = TensorImage.fromBitmap(bitmap)
                // Runs model inference and gets result.
                //val outputs = model.process(image)
                //val probability = outputs.probabilityAsCategoryList
                //txv.text = probability.toString()

                val outputs = model.process(image)
                    .probabilityAsCategoryList.apply {
                        sortByDescending { it.score } // 排序，高匹配率優先
                    }.take(1)
                var Result: String = ""
                intent = getIntent()
                var face = intent.getStringExtra("表情")
                for (output in outputs) {
                    when (output.label) {
                        "smile" -> Result = "開心"
                        "angry" -> Result = "生氣"
                        "sad" -> Result = "難過"

                    }
                    if(Result.equals(face)) ans = "1"
                    else ans = "0"
                    Result += ": " + String.format("%.1f%%", output.score * 100.0f) + ";  "
                     if (ans.equals("1")){
                         Handler(Looper.getMainLooper()).postDelayed({
                         intent = getIntent()
                        var no = intent.getIntExtra("頁數",0)
                        if(no ==2) intent = Intent(this@FaceScan, GamePage3::class.java)
                        if(no ==2_1) intent = Intent(this@FaceScan, GamePage3_1::class.java)//+
                        else if(no==4)intent = Intent(this@FaceScan, GamePage5::class.java)
                        else if(no==4_1)intent = Intent(this@FaceScan, GamePage5_1::class.java)//+
                        else if(no==5)intent = Intent(this@FaceScan, GamePage6::class.java)
                        else if(no==5_1)intent = Intent(this@FaceScan, GamePage6::class.java)//+
                        else if(no==6)intent = Intent(this@FaceScan, GamePage7::class.java)
                        else if(no==8)intent = Intent(this@FaceScan, GamePage9::class.java)
                        else if(no==8_1)intent = Intent(this@FaceScan, GamePage9::class.java)//+

                        startActivity(intent)
                        finish()
                         }, 5000)
                    }

                }
                txv.text = Result

                // Releases model resources if no longer used.
                model.close()
            })

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer)

            } catch(exc: Exception) {
                Toast.makeText(this, "Use case binding failed: ${exc.message}",
                    Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(this))

    }


}