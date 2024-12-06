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
                val splitCopy = ArrayList(split)
                val initiallyValid = checkLineValid(split, rules)
                var loopBreak = 0
                if (!initiallyValid) {
                    var invalidRule = getInvalidRule(split, rules)
                    while (invalidRule != null) {
                        val nextIndex = split.indexOf(invalidRule.before) - 1
                        val tmp = split[nextIndex]
                        split[nextIndex] = split[nextIndex + 1]
                        split[nextIndex + 1] = tmp
                        invalidRule = getInvalidRule(split, rules)
                        loopBreak++
                        if (loopBreak > 1000) error("Too many loops ${input.indexOf(it)}")
                    }

                    total += split[split.lastIndex/2]

                }
            }
        }
        println(total)
    }

    private fun getInvalidRule(
        split: List<Int>,
        rules: HashMap<Int, ArrayList<Rule>>
    ): Rule? {
        split.forEachIndexed { index, i ->
            rules.getOrDefault(i, emptyList()).forEach { rule ->
                val indexOfAfter = split.indexOf(rule.after)
                if (indexOfAfter != -1 && indexOfAfter <= index) return rule
            }
        }

        return null
    }

    private fun checkLineValid(
        split: List<Int>,
        rules: HashMap<Int, ArrayList<Rule>>
    ): Boolean {
        split.forEachIndexed { index, i ->
            rules.getOrDefault(i, emptyList()).forEach { rule ->
                val indexOfAfter = split.indexOf(rule.after)
                if (indexOfAfter != -1 && indexOfAfter <= index) return false
            }
        }

        return true
    }
}