package com.froren.aoc24.day10

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 10,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val heightMap = getHeights(lines)
    val width = heightMap.first().size
    val height = heightMap.size

    var score = 0

    for (y in 0..<height) {
        val arr = heightMap[y]
        for (x in 0..<width) {
            if (arr[x] == 0)
                score += getTrailScore(x,y,heightMap)
        }
    }

    return score
}

fun getTrailScore(x: Int, y: Int, heightMap: Array<Array<Int>>): Int {
    val width = heightMap.first().size
    val height = heightMap.size

    val peaks = mutableSetOf<Pair<Int,Int>>()

    fun continueTrail(x: Int, y: Int, lastHeight: Int) {
        if (x !in 0..<width || y !in 0..<height)
            return

        val currHeight = heightMap[y][x]

        if (currHeight != lastHeight + 1)
            return

        if (currHeight == 9) {
            peaks.add(Pair(x,y))
            return
        }

        continueTrail(x,y+1, currHeight)
        continueTrail(x,y-1, currHeight)
        continueTrail(x+1,y, currHeight)
        continueTrail(x-1,y, currHeight)
    }

    continueTrail(x,y,-1)

    return peaks.size
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val heightMap = getHeights(lines)
    val width = heightMap.first().size
    val height = heightMap.size

    var score = 0

    for (y in 0..<height) {
        val arr = heightMap[y]
        for (x in 0..<width) {
            if (arr[x] == 0)
                score += getTrailRating(x,y,heightMap)
        }
    }

    return score
}

fun getTrailRating(x: Int, y: Int, heightMap: Array<Array<Int>>): Int {
    val width = heightMap.first().size
    val height = heightMap.size

    var rating = 0

    fun continueTrail(x: Int, y: Int, lastHeight: Int) {
        if (x !in 0..<width || y !in 0..<height)
            return

        val currHeight = heightMap[y][x]

        if (currHeight != lastHeight + 1)
            return

        if (currHeight == 9) {
            rating++
            return
        }

        continueTrail(x,y+1, currHeight)
        continueTrail(x,y-1, currHeight)
        continueTrail(x+1,y, currHeight)
        continueTrail(x-1,y, currHeight)
    }

    continueTrail(x,y,-1)

    return rating
}

fun getHeights(lines: Sequence<String>) =
    lines.map {
        Array(it.length) { i ->  it[i].toString().toInt() }
    }
    .toList()
    .toTypedArray()
