object Day3 {

    fun part1() {
        val input = loadLines(3)
        val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
        val digitRegex = "\\d{1,3}".toRegex()
        var total = 0
        input.forEach {
            val matches = regex.findAll(it)
            matches.forEach { match ->
                val numbers = digitRegex.findAll(match.value).toList()
                total += (numbers[0].value.toInt() * numbers[1].value.toInt())
            }
        }

        println(total)
    }

    data class MyMatch(val value: String, val index: Int)

    fun part2() {
        val input = loadLines(3).reduce { acc, s ->
            acc + s
        }
        val regex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()
        //val regexToRemove = "don't\\(\\).*mul\\(\\d{1,3},\\d{1,3}\\).*do\\(\\)".toRegex()
        val digitRegex = "\\d{1,3}".toRegex()
        var total = 0
       // val sanitized = input.replace(regexToRemove, "")
        var enabled = true
        val matches = regex.findAll(input)
        matches.forEach { match ->
            when (match.value) {
                "don't()" -> enabled = false
                "do()" -> enabled = true
                else -> {
                    if (enabled) {
                        val numbers = digitRegex.findAll(match.value).toList()
                        total += (numbers[0].value.toInt() * numbers[1].value.toInt())
                    }
                }
            }
        }

        println(total)
    }
}