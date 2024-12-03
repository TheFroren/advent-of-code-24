package com.froren.aoc24.day02

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day02testInput.txt"

private const val SAFE_REPORTS = 2
private const val SAFE_DAMPENED_REPORTS = 4

class Day02Test {

    @Test
    fun `Part one solves correctly` () {
        val safeReports = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(SAFE_REPORTS, safeReports)
    }

    @Test
    fun `Part two solves correctly` () {
        val safeDampenedReports = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(SAFE_DAMPENED_REPORTS, safeDampenedReports)
    }

}