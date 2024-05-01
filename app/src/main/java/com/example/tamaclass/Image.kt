package com.example.tamaclass

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import pl.droidsonroids.gif.GifImageView

class Image(private val imageView: ImageView) {

    private val handler = Handler()
    private val displayDuration = 20000L
    private var gifShowingRunnable: Runnable? = null

    fun showGif(gifResourceId: Int) {
        imageView.setImageResource(gifResourceId)
        imageView.visibility = View.VISIBLE
        cancelGifShowing()
        gifShowingRunnable = Runnable { hideGif() }
        handler.postDelayed(gifShowingRunnable!!, displayDuration)
    }

    private fun hideGif() {
        imageView.setImageResource(0)
        imageView.visibility = View.GONE
    }

    fun cancelGifShowing() {
        gifShowingRunnable?.let { handler.removeCallbacks(it) }
        gifShowingRunnable = null
    }
    fun feedImage() {
        imageView.apply {
            setImageResource(R.drawable.feed) // replace with your feed image resource
            visibility = View.VISIBLE
            postDelayed({ visibility = View.GONE }, 5000)
            cancelGifShowing()
        }
    }

    fun cleanImage() {
        imageView.apply {
            setImageResource(R.drawable.clean) // replace with your clean image resource
            visibility = View.VISIBLE
            postDelayed({ visibility = View.GONE }, 5000)
            cancelGifShowing()
        }
    }

    fun playImage() {
        imageView.apply {
            setImageResource(R.drawable.play) // replace with your play image resource
            visibility = View.VISIBLE
            postDelayed({ visibility = View.GONE }, 5000)
            cancelGifShowing()
        }



    }
}


