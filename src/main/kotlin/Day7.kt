object Day7 {


    private fun operationPossible(target: Long, numbers: List<Long>, currentTotal: Long): Boolean {
        if (currentTotal > target) return false

        return if (numbers.isEmpty()) {
            currentTotal == target
        } else {
            val x = numbers.first()
            val concat = (currentTotal.toString() + x.toString()).toLong()
            listOf(
                operationPossible(target, numbers.drop(1), currentTotal + x),
                operationPossible(target, numbers.drop(1), currentTotal * x),
                operationPossible(target, numbers.drop(1), concat),
            ).any {
                it
            }
        }
    }

    fun part1() {
        val input = loadLines(7)

        var total = 0L

        input.forEach { line ->
            val split = line.split(": ")
            val targetValue = split.first().toLong()
            val numbers = split.last().split(" ").map { it.toLong() }
            if (operationPossible(targetValue, numbers.drop(1), numbers.first())) {
                total += targetValue
            }
        }

        println(total)
    }
}