import kotlin.math.abs
import kotlin.math.sign

object Day2 {

    fun part1() {

        fun verifyLine(line: List<Int>, totalErrors: Int): Boolean {
            var sign: Int? = null
            line.dropLast(1).forEachIndexed { index, value ->
                val delta = value - line[index + 1]
                if (sign == null) {
                    sign = delta.sign
                }
                val valid = delta.sign == sign && abs(delta) >= 1 && abs(delta) <= 3
                if (!valid) {
                    return if (totalErrors == 0) {
                        val line1 = ArrayList(line).apply {
                            removeAt(index)
                        }
                        val line2 = ArrayList(line).apply {
                            removeAt(index + 1)
                        }
                        val line3 = if (index == 0) line2 else ArrayList(line).apply {
                            removeAt(index - 1)
                        }
                        verifyLine(line1.toList(), 1) || verifyLine(line2.toList(), 1) || verifyLine(line3.toList(), 1)
                    } else {
                        false
                    }
                }
            }
            return true
        }

        val input = loadLines(2)

        val answer = input.filter { line ->
            verifyLine(line.split(" ").map { it.toInt() }, 0)
        }.size

        println(answer)
    }
}