package dev.hyuwah.githubuserexplorer.presentation.utils

import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.pow

object NumberFormatter {

    /**
     * Format 1200 to 1.2k, 2500000 to 2.5m
     * but return original value if less than 1000
     * @param value number to format
     */
    fun formatWithSuffix(value: Int): String {
        val valueFloat = value.toFloat()
        if (value < 1000) return value.toString()
        val exp = floor(ln(valueFloat) / ln(1000f)).toInt()
        val truncatedValue = valueFloat / 1000f.pow(exp)
        val suffix = Units.values()[exp - 1].suffix
        return String.format("%.1f%s", truncatedValue, suffix)
    }

    enum class Units(val suffix: String) {
        K("k"),
        M("m"),
        B("b")
    }
}