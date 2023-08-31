import okio.FileSystem
import okio.Path.Companion.toPath
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.toList
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) { readUtf8() }.dropLast(1)
    val matrix = input.split("\n").map { line -> line.asIterable().map { it.toString().toInt() } }
    val matrix2d = mk.ndarray(matrix)

    val matrixSize = matrix.size

    val time = measureTimeMillis {
        val maxScenicScore = (1..<matrixSize - 1).flatMap { rowNumber ->
            (1..<matrixSize - 1).map { columnNumber ->
                val tree = matrix[rowNumber][columnNumber]

                if (tree == 0) {
                    1
                } else {
                    val leftRow = matrix2d[rowNumber, 0..<columnNumber].toList()
                    val leftDistance = leftRow.takeLastWhileIncluding { it < tree }.size

                    val rightRow = matrix2d[rowNumber, columnNumber + 1..<matrixSize].toList()
                    val rightDistance = rightRow.takeWhileIncluding { it < tree }.size

                    val topColumn = matrix2d[0..<rowNumber, columnNumber].toList()
                    val topDistance = topColumn.takeLastWhileIncluding { it < tree }.size

                    val bottomColumn = matrix2d[rowNumber + 1..<matrixSize, columnNumber].toList()
                    val bottomDistance = bottomColumn.takeWhileIncluding { it < tree }.size

                    leftDistance * rightDistance * topDistance * bottomDistance
                }
            }
        }.max()

        println(maxScenicScore)
    }
    println("Time: $time ms")
}

inline fun <T> Iterable<T>.takeWhileIncluding(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (!predicate(item)) break
    }
    return list
}

inline fun <T> List<T>.takeLastWhileIncluding(predicate: (T) -> Boolean): List<T> {
    if (isEmpty()) return emptyList()
    val iterator = listIterator(size)

    while (iterator.hasPrevious()) {
        if (!predicate(iterator.previous())) {
            val expectedSize = size - iterator.nextIndex()
            if (expectedSize == 0) return emptyList()
            return ArrayList<T>(expectedSize).apply {
                while (iterator.hasNext())
                    add(iterator.next())
            }
        }
    }

    return toList()
}