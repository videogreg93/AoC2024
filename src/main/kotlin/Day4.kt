object Day4 {

    enum class DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_RIGHT,
        UP_LEFT,
        DOWN_RIGHT,
        DOWN_LEFT
    }

    val values = hashMapOf<DIRECTION, String>()

    fun part1() {
        val input = loadLines(4)
        var total = 0

        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char == 'X') {
                    (0..3).forEach {
                        values[DIRECTION.RIGHT] =
                            values.getOrDefault(DIRECTION.RIGHT, "") + line.getOrNull(charIndex + it)
                        values[DIRECTION.LEFT] =
                            values.getOrDefault(DIRECTION.LEFT, "") + line.getOrNull(charIndex - it)
                        values[DIRECTION.UP] = values.getOrDefault(DIRECTION.UP, "") + input.getOrNull(lineIndex - it)
                            ?.getOrNull(charIndex)
                        values[DIRECTION.DOWN] =
                            values.getOrDefault(DIRECTION.DOWN, "") + input.getOrNull(lineIndex + it)
                                ?.getOrNull(charIndex)
                        values[DIRECTION.UP_RIGHT] =
                            values.getOrDefault(DIRECTION.UP_RIGHT, "") + input.getOrNull(lineIndex - it)
                                ?.getOrNull(charIndex + it)
                        values[DIRECTION.UP_LEFT] =
                            values.getOrDefault(DIRECTION.UP_LEFT, "") + input.getOrNull(lineIndex - it)
                                ?.getOrNull(charIndex - it)
                        values[DIRECTION.DOWN_LEFT] =
                            values.getOrDefault(DIRECTION.DOWN_LEFT, "") + input.getOrNull(lineIndex + it)
                                ?.getOrNull(charIndex - it)
                        values[DIRECTION.DOWN_RIGHT] =
                            values.getOrDefault(DIRECTION.DOWN_RIGHT, "") + input.getOrNull(lineIndex + it)
                                ?.getOrNull(charIndex + it)
                    }
                }
                total += values.values.filter { it.equals("XMAS", true) }.size
                values.clear()
            }
        }

        println(total)
    }

    fun part2() {
        val input = loadLines(4)
        var total = 0
        val validInputs = listOf("MAS", "SAM")

        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char == 'A') {
                    var diag1 = ""
                    var diag2 = ""
                    (0..2).forEach {
                        diag1 += input.getOrNull(lineIndex - 1 + it)?.getOrNull(charIndex - 1 + it)
                        diag2 += input.getOrNull(lineIndex + 1 - it)?.getOrNull(charIndex - 1 + it)
                    }
                    val success = listOf(diag1, diag2).all {
                        validInputs.contains(it)
                    }
                    if (success) total++
                }

            }
        }

        println(total)
    }
}