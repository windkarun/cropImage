package com.wind.imagecrop

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.myhexaville.smartimagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_main.*


class ActivityExample : AppCompatActivity() {
    private var imagePicker: ImagePicker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            showAll()
        }

           btn1.setOnClickListener {
               val file = imagePicker!!.imageFile
               Log.d(TAG, "refreshImagePicker: $file")
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker!!.handleActivityResult(resultCode, requestCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        imagePicker!!.handlePermission(requestCode, grantResults)
    }

    private fun showAll() {
        refreshImagePicker()
        imagePicker!!.choosePicture(true)
    }

    private fun chooseFromGallery() {
        refreshImagePicker()
        imagePicker!!.choosePicture(false)
    }

    private fun openCamera() {
        refreshImagePicker()
        imagePicker!!.openCamera()
    }

    private fun refreshImagePicker() {
        imagePicker = ImagePicker(
            this, null)
        { imageUri ->
            Log.d(TAG, "refreshImagePicker: $imageUri")
            Log.d(TAG, "File Name: " + imagePicker!!.imageFile.name)
            Log.d(TAG, "File path: " + imagePicker!!.imageFile)
            iv.setImageURI(imageUri)
        }.setWithImageCrop(1,1)


    }


    companion object {
        private val TAG = "ActivityExample"
    }
}