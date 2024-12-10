package com.froren.aoc24.day09

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day09testInput.txt"

private const val CHECKSUM = 1928
private const val CHECKSUM_NO_FRAGMENT = 2858

class Day09Test {

    @Test
    fun `Part one solves correctly` () {
        val checksum = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(CHECKSUM.toLong(), checksum)
    }

    @Test
    fun `Part two solves correctly` () {
        val checksumNoFragment = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(CHECKSUM_NO_FRAGMENT.toLong(), checksumNoFragment)
    }

}