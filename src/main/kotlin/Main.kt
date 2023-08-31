import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("test_input.txt".toPath()) { readUtf8() }.dropLast(1)
    val matrix = input.split("\n").map { line -> line.asIterable().map { it.toString().toInt() } }
    val matrixSize = matrix.size

    val time = measureTimeMillis {
        val scenicScore = (1..<matrixSize - 1).map { rowNumber ->
            (1..<matrixSize - 1).map { columnNumber ->
                val tree = matrix[rowNumber][columnNumber]

                if (tree == 0) {
                    1
                } else {
                    val row = matrix[rowNumber]

                    val leftRow = row.take(columnNumber)
                    val leftDistance = leftRow.takeLastWhileIncluding { it < tree }.size

                    val rightRow = row.takeLast(matrixSize - 1 - columnNumber)
                    val rightDistance = rightRow.takeWhileIncluding { it < tree }.size

                    val column = matrix.map { it[columnNumber] }
                    val topColumn = column.take(rowNumber)
                    val topDistance = topColumn.takeLastWhileIncluding { it < tree }.size

                    val bottomColumn = column.takeLast(matrixSize - 1 - rowNumber)
                    val bottomDistance = bottomColumn.takeWhileIncluding { it < tree }.size

                    leftDistance * rightDistance * topDistance * bottomDistance
                }
            }
        }

        scenicScore.forEach {
            println(it)
        }
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