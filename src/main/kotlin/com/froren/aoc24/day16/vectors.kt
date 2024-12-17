package com.froren.aoc24.day16

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

enum class Dir(x: Int, y: Int) {
    UP(0,-1),
    RIGHT(1,0),
    DOWN(0,1),
    LEFT(-1,0);

    val v = Vector(x,y)

    fun turnCW(amount: Int) =
        Dir.entries[(this.ordinal + amount + 4) % 4]
}
