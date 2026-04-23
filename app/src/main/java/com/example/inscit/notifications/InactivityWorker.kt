package com.example.inscit.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class InactivityWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val (title, message) = getRandomMotivationalMessage()
        NotificationHelper.showNotification(applicationContext, title, message)
        return Result.success()
    }

    private fun getRandomMotivationalMessage(): Pair<String, String> {
        val messages = listOf(
            "Yo Explorer! 🚀" to "Your brain cells are literally begging for a quiz. Don't let them down!",
            "Brain Lag? 🧠" to "Is your IQ feeling lonely? Come back and give it some company with a quick science session!",
            "Newton is Crying 🍎" to "Gravity is still working, but are you? Let's drop some knowledge!",
            "Science called... 📞" to "It said it misses you. Don't be that person who ghosts Science. Not cool.",
            "Level Up? 🆙" to "Those XP points aren't going to earn themselves. Stop procrastinating!",
            "Procrastination is Mid 📉" to "Don't be mid. Be a pro Scientist. Let's get it!",
            "E = mc... what? ⚡" to "Einstein didn't invent physics for you to just scroll social media all day. Quiz time!",
            "Oxygen is Free 💨" to "But knowledge is priceless. Breathe in some science right now!",
            "Don't be a Neutron ⚛️" to "Stay positive, but also stay active! Your streak is waiting for you.",
            "Absolute Zero? 🧊" to "That's how much progress you're making right now. Let's heat things up!",
            "Rizz or Science? 🤔" to "Why not both? High IQ is the ultimate rizz. Back to the lab, mate!",
            "Your Brain is Buffering ⏳" to "Finish the quiz to clear the cache. Don't let your brain lag like a bad internet connection!",
            "Science > Sleeping 😴" to "Wakey wakey! The laws of thermodynamics don't take naps, and neither should your curiosity!",
            "Main Character Energy ✨" to "The main character always knows their science. Don't be an NPC, take a quiz!",
            "Glow Up (Mental) 🌟" to "Work on that mental aesthetic. Science is the best filter for a sharp mind!"
        )
        return messages[Random.nextInt(messages.size)]
    }
}
