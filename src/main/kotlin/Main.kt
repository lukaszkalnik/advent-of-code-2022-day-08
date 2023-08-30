import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("test_input.txt".toPath()) { readUtf8() }.dropLast(1)
    val matrix = input.split("\n").map { line -> line.asIterable().map { it.toString().toInt() } }
    matrix.forEach { row ->
        println(row)
    }
}