package com.froren.aoc24.day08

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
}

operator fun Area.contains(vec: Vector) =
    vec.x in xBounds && vec.y in yBounds
