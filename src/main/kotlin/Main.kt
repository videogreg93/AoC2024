import java.io.File


fun main(args: Array<String>) {
    Day5.part2()
}

fun loadLines(day: Int): List<String> {
    return File("./res/day$day.txt").readLines()
}