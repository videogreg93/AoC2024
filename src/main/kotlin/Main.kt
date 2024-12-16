import java.io.File


fun main(args: Array<String>) {
    Day7.part1()
}

fun loadLines(day: Int): List<String> {
    return File("./res/day$day.txt").readLines()
}