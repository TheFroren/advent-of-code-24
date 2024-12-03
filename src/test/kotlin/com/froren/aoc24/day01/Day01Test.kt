package com.froren.aoc24.day01

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day01testInput.txt"

private const val TEST_DISTANCE_SUM = 11
private const val TEST_COUNT_SUM = 31

class Day01Test {

    @Test
    fun `Part one solves correctly` () {
        val distanceSum = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(TEST_DISTANCE_SUM, distanceSum)
    }

    @Test
    fun `Part two solves correctly` () {
        val countSum = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(TEST_COUNT_SUM, countSum)
    }

}