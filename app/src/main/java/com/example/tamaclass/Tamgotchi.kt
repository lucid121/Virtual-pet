package com.example.tamaclass

import android.view.View

class TamagotchiManager {
    var happiness = 99
    var health = 99
    private val maxStat = 99
    private val minStat = 0

    fun increaseStat(stat: String) {
        when (stat) {
            "happiness" -> happiness = (happiness + 5).coerceAtMost(maxStat)
            "health" -> health = (health + 5).coerceAtMost(maxStat)
        }
    }

    fun decreaseStat(stat: String) {
        when (stat) {
            "happiness" -> happiness = (happiness - 5).coerceAtLeast(minStat)
            "health" -> health = (health - 5).coerceAtLeast(minStat)
        }
    }

    fun getHappiness(): String = "Happiness: $happiness"
    fun getHealth(): String = "Health: $health"
}
