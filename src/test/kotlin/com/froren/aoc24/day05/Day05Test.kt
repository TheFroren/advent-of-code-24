package com.froren.aoc24.day05

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day05testInput.txt"

const val MIDDLE_SUM = 143
const val MIDDLE_SUM_FIXED = 123

class Day05Test {

    @Test
    fun `Part one solves correctly` () {
        val middleSum = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(MIDDLE_SUM, middleSum)
    }

    @Test
    fun `Part two solves correctly` () {
        val middleSumFixed = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(MIDDLE_SUM_FIXED, middleSumFixed)
    }

}