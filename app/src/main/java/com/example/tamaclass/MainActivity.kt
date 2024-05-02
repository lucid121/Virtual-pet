package com.example.tamaclass

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.media.MediaPlayer
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import pl.droidsonroids.gif.GifImageView
private lateinit var gifImageView: GifImageView
private lateinit var gifImageHandler: Image

class MainActivity : AppCompatActivity() {

    private lateinit var happinesst: TextView
    private lateinit var healthTextView: TextView
    private val tamagotchiManager = TamagotchiManager()
    private lateinit var feedImageHandler: Image
    private lateinit var cleanImageHandler: Image
    private lateinit var playImageHandler: Image
    private lateinit var gifImageView: ImageView
    private lateinit var gifImageHandler: Image

    private var updateIntervalMillis = 3000L // Changing variables every 3 seconds
    private val handler = Handler(Looper.getMainLooper())
    private val updateRunnable = object : Runnable {
        override fun run() {
            //Dfecreasing happiness and health
            tamagotchiManager.decreaseStat("happiness")
            tamagotchiManager.decreaseStat("health")
            updateTextViews()


            if (tamagotchiManager.happiness <= 0)// Sees if happiness has reached zero
            {

                val runImageView = findViewById<ImageView>(R.id.run)// Shows run image with the message
                runImageView.visibility = View.VISIBLE
                happinesst.text = "Your dog left you"
            } else {
                val runImageView = findViewById<ImageView>(R.id.run)
                runImageView.visibility = View.INVISIBLE


            }




            if (tamagotchiManager.health <= 0) // Checks if health reaches zero
            {

                val sleepImageView = findViewById<ImageView>(R.id.sleeo) // Shows the sleep image
                sleepImageView.visibility = View.VISIBLE
                healthTextView.text = "Your dog died"
            } else {
                val sleepImageView = findViewById<ImageView>(R.id.sleeo)
                sleepImageView.visibility = View.INVISIBLE

            }


            handler.postDelayed(this, updateIntervalMillis)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(Looper.getMainLooper())


        happinesst = findViewById(R.id.HAT)
        healthTextView = findViewById(R.id.HET)
        val feedButton: Button = findViewById(R.id.feed)
        val cleanButton: Button = findViewById(R.id.clean)
        val playButton: Button = findViewById(R.id.play)

        val runImageView = findViewById<ImageView>(R.id.run)






        val feedImageView = findViewById<ImageView>(R.id.feedi).apply { visibility = View.INVISIBLE }
        val cleanImageView = findViewById<ImageView>(R.id.cleani).apply { visibility = View.INVISIBLE }
        val playImageView = findViewById<ImageView>(R.id.playi).apply { visibility = View.INVISIBLE }
        gifImageView = findViewById(R.id.imageView)
        val galaxysound:  MediaPlayer = MediaPlayer.create(this, R.raw.galaxymusic)
        if (gifImageView.visibility == View.VISIBLE){
            galaxysound.start()
        }






        feedImageHandler = Image(feedImageView)
        cleanImageHandler = Image(cleanImageView)
        playImageHandler = Image(playImageView)
        gifImageHandler = Image(gifImageView)
        val soundPlayer = SoundPlayer(this)

        cleanButton.setOnClickListener {
            tamagotchiManager.increaseStat("happiness")
            updateTextViews()
            cleanImageHandler.cleanImage()
            soundPlayer.playSound(R.raw.clean)
        }
        feedButton.setOnClickListener {
            tamagotchiManager.increaseStat("health")
            updateTextViews()
            feedImageHandler.feedImage()
            soundPlayer.playSound(R.raw.feed)
        }
        playButton.setOnClickListener {
            tamagotchiManager.increaseStat("happiness")
            updateTextViews()
            playImageHandler.playImage()
            soundPlayer.playSound(R.raw.play)
        }
        gifImageHandler.showGif(R.drawable.galaxy)

        // Start the timer
        handler.post(updateRunnable)
    }



    private fun updateTextViews() {
        happinesst.text = tamagotchiManager.getHappiness()
        healthTextView.text = tamagotchiManager.getHealth()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop the timer when the activity is destroyed
        handler.removeCallbacks(updateRunnable)
    }
}
