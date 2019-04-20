package com.wind.imagecrop.fragment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wind.imagecrop.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fram,GalleryFragment())
            .commit()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragments = supportFragmentManager.fragments
        for (f in fragments) {
            if (f is GalleryFragment) {
                f.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
