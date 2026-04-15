package com.example.inscit.xp

enum class Rank(val label: String, val icon: String, val threshold: Int) {
    BEGINNER("Beginner", "🌱", 0),
    EXPLORER("Explorer", "🚀", 150),
    SCIENTIST("Scientist", "🧪", 300),
    GENIUS("Genius", "🧠", 600),
    MASTER("Master","♟️", 1000),
    HERO("Hero", "🦸🏼‍♂️", 1500),
    LEGEND("Legend", "👑", 2000);

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
