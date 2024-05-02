package com.example.tamaclass

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val barker =findViewById<ImageView>(R.id.barking)
        val barks:  MediaPlayer = MediaPlayer.create(this, R.raw.bark)
        if (barker.visibility == View.VISIBLE){
            barks.start()
        }
        Handler().postDelayed({
            // Start your app main activity
            startActivity(Intent(this, MainActivity::class.java))

            // Close this activity
            finish()
        }, 6000) // wait for 3 seconds
    }
}