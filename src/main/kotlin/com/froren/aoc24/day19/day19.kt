package com.froren.aoc24.day19

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 19,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val (available, patterns) = getPatterns(lines)

    return patterns.count {
        canBeMade(it, available)
    }
}

fun canBeMade(pattern: String, available: List<String>): Boolean {
    var patternIndex = 0
    val parts = mutableListOf(0)

    while (true) {
        if (parts.last() >= available.size) {
            parts.removeLast()

            if (parts.isEmpty())
                break

            patternIndex -= available[parts[parts.lastIndex]].length

            parts[parts.lastIndex] += 1
            continue
        }

        val tested = available[parts.last()]
        val endIndex = patternIndex + tested.length - 1



        if (
            endIndex in pattern.indices
            && pattern.subSequence(patternIndex ..endIndex).contentEquals(tested)
        ) {
            parts.add(0)
            patternIndex += tested.length

            if (patternIndex == pattern.length)
                return true
            else
                continue
        }

        parts[parts.lastIndex] += 1
    }

    return false
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val (available, patterns) = getPatterns(lines)

    return patterns.sumOf {
        println(it)
        combinations(it, available)
    }
}

fun combinations(pattern: String, available: List<String>): Int {
    var patternIndex = 0
    val parts = mutableListOf(0)

    var matches = 0

    while (true) {
        println("$patternIndex, $parts")

        if (parts.last() >= available.size) {
            parts.removeLast()

            if (parts.isEmpty())
                break

            patternIndex -= available[parts[parts.lastIndex]].length

            parts[parts.lastIndex] += 1
            continue
        }

        val tested = available[parts.last()]
        val endIndex = patternIndex + tested.length - 1

        if (
            endIndex in pattern.indices
            && pattern.subSequence(patternIndex ..endIndex).contentEquals(tested)
        ) {
            parts.add(0)
            patternIndex += tested.length

            if (patternIndex == pattern.length)
                matches += 1

            continue
        }

        parts[parts.lastIndex] += 1
    }

    return matches
}


fun getPatterns(lines:Sequence<String>): Pair<List<String>, List<String>> {
    var firstPart = true
    lateinit var available: List<String>
    val patterns = mutableListOf<String>()

    lines.forEach {
        if (it.isEmpty()) {
            firstPart = false
            return@forEach
        }

        if (firstPart) {
            available = it.split(", ")
        } else {
            patterns += it
        }
    }

    return available to patterns
}