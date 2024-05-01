package com.example.tamaclass

import android.content.Context
import android.media.MediaPlayer

class SoundPlayer(private val context: Context)//Making the class for sound
{
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(soundResourceId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, soundResourceId).apply {
            start()
            setOnCompletionListener {
                it.release()
            }
        }
    }
}