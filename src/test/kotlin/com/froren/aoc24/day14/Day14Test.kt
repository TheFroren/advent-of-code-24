package com.froren.aoc24.day14

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day14testInput.txt"

private const val SAFETY = 12

class Day14Test {

    @Test
    fun `Part one solves correctly` () {
        val safety = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(SAFETY, safety)
    }
}