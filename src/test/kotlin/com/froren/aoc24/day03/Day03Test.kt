package com.froren.aoc24.day03

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH_PT1 = "/day03testInput.txt"
private const val RESOURCE_PATH_PT2 = "/day03pt2testInput.txt"

private const val TOTAL_MUL = 161
private const val TOTAL_DO_MUL = 48

class Day03Test {

    @Test
    fun `Part one solves correctly` () {
        val totalMul = loadResource(RESOURCE_PATH_PT1)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(TOTAL_MUL, totalMul)
    }

    @Test
    fun `Part two solves correctly` () {
        val totalDoMul = loadResource(RESOURCE_PATH_PT2)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(TOTAL_DO_MUL, totalDoMul)
    }

}