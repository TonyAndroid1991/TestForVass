package com.y4kuzabanzai.testforvass

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class MainActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageView: ImageView = findViewById(R.id.homeImage)

        Glide.with(this)
            .load("https://www.publicdomainpictures.net/pictures/10000/nahled/thinking-monkey-11282237747K8xB.jpg")
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(imageView)

    }
}