package com.example.inscit.xp

enum class Rank(val label: String, val icon: String, val threshold: Int) {
    BEGINNER(label = "Beginner", icon = "🌱", threshold = 0),
    EXPLORER(label = "Explorer", icon = "🚀", threshold = 150),
    SCIENTIST(label = "Scientist", icon = "🧪", threshold = 300),
    GENIUS(label = "Genius", icon = "🧠", threshold = 600),
    MASTER(label = "Master",icon = "♟️", threshold = 1000),
    HERO(label = "Hero", icon = "🦸🏼‍♂️", threshold = 1500),
    LEGEND(label = "Legend", icon = "👑", threshold = 2000);

    companion object {
        fun fromXp(xp: Int): Rank {
            return Rank.entries.findLast { xp >= it.threshold } ?: BEGINNER
        }
    }
}

object XpManager {
    fun calculateLevel(xp: Int): Int {
        return (xp / 100) + 1
    }
}
