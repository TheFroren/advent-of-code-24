package com.froren.aoc24.bootstrap

import java.io.File

const val RESULTS_DIR = "results"
fun getOutputFile(file: String): File {
    File(RESULTS_DIR).mkdirs()
    return File(RESULTS_DIR, file)
}