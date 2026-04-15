package com.example.inscit.xp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf

class PendingXpBuffer {
    private val _pendingXp = mutableIntStateOf(0)
    val pendingXp: Int get() = _pendingXp.intValue

    fun add(amount: Int) {
        _pendingXp.intValue += amount
    }

    fun clear() {
        _pendingXp.intValue = 0
    }
}
