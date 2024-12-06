object Day5 {

    data class Rule(val before: Int, val after: Int)

    fun part1() {
        val rules = hashMapOf<Int, ArrayList<Rule>>()
        val input = loadLines(5)
        var total = 0
        input.forEach {
            if (it.contains("|")) {
                val split = it.split("|")
                val currentRule = rules.getOrDefault(split[0].toInt(), arrayListOf())
                currentRule.add(Rule(split[0].toInt(), split[1].toInt()))
                rules.put(split[0].toInt(), currentRule )
            } else if (it.contains(",")) {
                val split = it.split(",").map { it.toInt() }
                val valid = checkLineValid(split, rules)
                if (valid) {
                    total += split[split.lastIndex/2]
                }
            }
        }
        println(total)
    }

    fun part2() {
        val rules = hashMapOf<Int, ArrayList<Rule>>()
        val input = loadLines(5)
        var total = 0
        input.forEach {
            if (it.contains("|")) {
                val split = it.split("|")
                val currentRule = rules.getOrDefault(split[0].toInt(), arrayListOf())
                currentRule.add(Rule(split[0].toInt(), split[1].toInt()))
                rules.put(split[0].toInt(), currentRule )
            } else if (it.contains(",")) {
                val split = ArrayList(it.split(",").map { it.toInt() })
                val initiallyValid = checkLineValid(split, rules)
                if (!initiallyValid) {
                    val ordering = ArrayList<Int>()
                    while (split.isNotEmpty()) {
                       val toInsert = split.removeFirst()
                       val befores = rules.values.filter { it.filter { it.after == toInsert } }
                    }

                }
            }
        }
        println(total)
    }

    private fun checkLineValid(
        split: List<Int>,
        rules: HashMap<Int, ArrayList<Rule>>
    ): Boolean {
        split.forEachIndexed { index, i ->
            rules.getValue(i).forEach { rule ->
                val indexOfAfter = split.indexOf(rule.after)
                if (indexOfAfter != -1 && indexOfAfter <= index) return false
            }
        }

        return true
    }
}