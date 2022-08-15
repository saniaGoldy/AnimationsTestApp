package com.example.animationstestapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SharedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_view)
        findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.coat_of_arms)
        findViewById<Button>(R.id.sharedButton).setOnClickListener {
            onBackPressed()
        }
    }
}