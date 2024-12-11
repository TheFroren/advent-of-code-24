package com.froren.aoc24.day11

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals


private const val RESOURCE_PATH = "/day11testInput.txt"

private const val STONES = 55312

class Day11Test {

    @Test
    fun `Part one solves correctly` () {
        val stones = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(STONES.toLong(), stones)
    }

}