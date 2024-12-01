import java.io.File


fun main(args: Array<String>) {
    Day1.part2()
}

fun loadLines(day: Int): List<String> {
    return File("./res/day$day.txt").readLines()
}