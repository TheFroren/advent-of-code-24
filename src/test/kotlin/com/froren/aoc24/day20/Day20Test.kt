package com.froren.aoc24.day20

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test

private const val RESOURCE_PATH = "/day20testInput.txt"

class Day20Test {

    @Test
    fun `Part one runs` () {
        loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)
    }
}