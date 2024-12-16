package com.froren.aoc24.day14

data class Vector(
    val x: Int,
    val y: Int,
)

operator fun Vector.plus(other: Vector) =
    Vector(x+other.x, y+other.y)

operator fun Vector.minus(other: Vector) =
    Vector(x-other.x, y-other.y)

data class Area(
    val xBounds: IntRange,
    val yBounds: IntRange,
) {
    constructor(width: Int, height: Int):
        this(0..<width, 0..<height)

    val width = xBounds.last - xBounds.first + 1
    val height = yBounds.last - yBounds.first + 1
}

operator fun Area.contains(vec: Vector) =
    vec.x in xBounds && vec.y in yBounds

fun Area.wrap(vec: Vector): Vector {
    if (vec in this)
        return vec

    var x = vec.x
    var y = vec.y

    if (vec.x < xBounds.first)
        x = xBounds.last - (xBounds.first - vec.x - 1)

    if (vec.x > xBounds.last)
        x = xBounds.first + (vec.x - xBounds.last - 1)

    if (vec.y < yBounds.first)
        y = yBounds.last - (yBounds.first - vec.y - 1)

    if (vec.y > yBounds.last)
        y = yBounds.first + (vec.y - yBounds.last - 1)

    return Vector(x, y)
}
