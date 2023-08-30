import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) { readUtf8() }.dropLast(1)
    val matrix = input.split("\n").map { line -> line.asIterable().map { it.toString().toInt() } }
    val matrixSize = matrix.size

    var totalVisible = 4 * (matrixSize - 1)

    (1..<matrixSize - 1).map { rowNumber ->
        (1..<matrixSize - 1).map { columnNumber ->
            val tree = matrix[rowNumber][columnNumber]
            val row = matrix[rowNumber]
            val column = matrix.map { it[columnNumber] }
            val visible = row.take(columnNumber).all { it < tree } ||
                    row.takeLast(matrixSize - 1 - columnNumber).all { it < tree } ||
                    column.take(rowNumber).all { it < tree } ||
                    column.takeLast(matrixSize - 1 - rowNumber).all { it < tree }
            if (visible) ++totalVisible
        }
    }

    println(totalVisible)
}
