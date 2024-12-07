package com.froren.aoc24.day07

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day07testInput.txt"

private const val CALIBRATION_RESULT = 3749
private const val CALIBRATION_RESULT_CONCAT = 11387

class Day07Test {

    @Test
    fun `Part one solves correctly` () {
        val calibrationResult = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(CALIBRATION_RESULT.toLong(), calibrationResult)
    }

    @Test
    fun `Part two solves correctly` () {
        val calibrationResultConcat = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(CALIBRATION_RESULT_CONCAT.toLong(), calibrationResultConcat)
    }

}