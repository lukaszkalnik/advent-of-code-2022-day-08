import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) { readUtf8() }.dropLast(1)
    val matrix = input.split("\n").map { line -> line.asIterable().map { it.toString().toInt() } }
    val matrixSize = matrix.size

    // all trees on the edges are visible
    val visibleOnEdges = 4 * (matrixSize - 1)

    val visibleInside = (1..<matrixSize - 1).flatMap { rowNumber ->
        (1..<matrixSize - 1).map { columnNumber ->
            val tree = matrix[rowNumber][columnNumber]
            val row = matrix[rowNumber]
            val column = matrix.map { it[columnNumber] }

            // Map the tree to its visibility. A tree is visible if it is visible at least from 1 direction (left,
            // right, top, bottom). For a tree to be visible from a direction, all trees between it and the edge must be
            // lower than this tree.
            row.take(columnNumber).all { it < tree } ||
                    row.takeLast(matrixSize - 1 - columnNumber).all { it < tree } ||
                    column.take(rowNumber).all { it < tree } ||
                    column.takeLast(matrixSize - 1 - rowNumber).all { it < tree }
        }
    }.count { it }

    println(visibleOnEdges + visibleInside)
}
