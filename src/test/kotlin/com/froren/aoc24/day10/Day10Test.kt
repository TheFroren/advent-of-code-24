package com.froren.aoc24.day10

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals


private const val RESOURCE_PATH = "/day10testInput.txt"

private const val TRAILHEAD_SCORES = 36
private const val TRAILHEAD_RATINGS = 81

class Day10Test {

    @Test
    fun `Part one solves correctly` () {
        val trailheadScores = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(TRAILHEAD_SCORES, trailheadScores)
    }

    @Test
    fun `Part two solves correctly` () {
        val trailheadRatings = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(TRAILHEAD_RATINGS, trailheadRatings)
    }

}