package com.froren.aoc24.day04

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH_PT1 = "/day04testInput.txt"
private const val RESOURCE_PATH_PT2 = "/day04pt2testInput.txt"

private const val TOTAL_XMAS = 18
private const val TOTAL_X_MAS = 9

class Day04Test {

    @Test
    fun `Part one solves correctly` () {
        val totalXmas = loadResource(RESOURCE_PATH_PT1)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(TOTAL_XMAS, totalXmas)
    }

    @Test
    fun `Part two solves correctly` () {
        val totalXMas = loadResource(RESOURCE_PATH_PT2)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(TOTAL_X_MAS, totalXMas)
    }

}