package com.froren.aoc24.day06

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day06testInput.txt"

private const val PATH_POINTS = 41
private const val LOOPS = 6

class Day06Test {

    @Test
    fun `Part one solves correctly` () {
        val pathPoints = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(PATH_POINTS, pathPoints)
    }

    @Test
    fun `Part two solves correctly` () {
        val loops = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(LOOPS, loops)
    }

}