package com.wind.imagecrop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.myhexaville.smartimagepicker.ImagePicker
import com.wind.imagecrop.R
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() {

    companion object {
        private val TAG = "FragmentExample"
    }

    private lateinit var logo: ImageView
    private lateinit var imgBack: ImageView
    private var imagePicker: ImagePicker? = null
    private var imagePicker1: ImagePicker? = null
    var ischeck: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gallery, container, false)
        initView(v)
        return v
    }

    private fun initView(v: View?) {
        logo = v!!.logo
        imgBack = v.imgBack

        v.btn_logo.setOnClickListener {
            ischeck=1
            showAll()
        }

        v.back_img_btn.setOnClickListener {
            ischeck=2
            showAll1()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(ischeck==1) {
            imagePicker!!.handleActivityResult(resultCode, requestCode, data)
        }
        if(ischeck==2) {
            imagePicker1!!.handleActivityResult(resultCode, requestCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(ischeck==1) {
            imagePicker!!.handlePermission(requestCode, grantResults)
        }
        if(ischeck==2) {
            imagePicker1!!.handlePermission(requestCode, grantResults)
        }
    }

    fun showAll() {
        refreshImagePicker()
        imagePicker!!.choosePicture(true)
    }

     fun showAll1() {
        refreshImagePicker1()
        imagePicker1!!.choosePicture(true)
    }


    private fun refreshImagePicker() {
        imagePicker = ImagePicker(activity, this) { imageUri ->
            Log.d(TAG, "refreshImagePicker: $imageUri")
            Log.d(TAG, "File Name: " + imagePicker!!.imageFile.name)
            Log.d(TAG, "File path: " + imagePicker!!.imageFile)
            logo.setImageURI(imageUri)
        }.setWithImageCrop(1,1)
    }
    private fun refreshImagePicker1() {
        imagePicker1 = ImagePicker(activity, this) { imageUri1 ->
            Log.d(TAG, "refreshImagePicker1: $imageUri1")
            Log.d(TAG, "File Name1: " + imagePicker1!!.imageFile.name)
            Log.d(TAG, "File path1: " + imagePicker1!!.imageFile)
            imgBack.setImageURI(imageUri1)
        }.setWithImageCrop(1,1)
    }

    /*fun chooseFromGallery() {
      refreshImagePicker()
      imagePicker!!.choosePicture(false)
  }

  fun openCamera() {
      refreshImagePicker()
      imagePicker!!.openCamera()
  }*/

}