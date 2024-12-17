package com.froren.aoc24.day16

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day16testInput.txt"

private const val SCORE = 7036
private const val OPTIMAL_NODES = 45

class Day16Test {

    @Test
    fun `Part one solves correctly` () {
        val score = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(SCORE, score)
    }

    @Test
    fun `Part two solves correctly` () {
        val optimalNodes = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(OPTIMAL_NODES, optimalNodes)
    }
}