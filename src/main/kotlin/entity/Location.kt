package entity

import java.util.*

data class Location(
    val date: Date = Date(),
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0
) {
    val undefined get() = x == 0.0 && y == 0.0 && z == 0.0
}
