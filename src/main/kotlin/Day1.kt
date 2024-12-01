import java.util.Collections
import kotlin.math.abs

object Day1 {

    fun part1() {
        val list1 = ArrayList<Int>()
        val list2 = ArrayList<Int>()
        val input = loadLines(1).map {
            it.split("   ").let {
                list1.add(it[0].toInt())
                list2.add(it[1].toInt())
            }
        }
        list1.sort()
        list2.sort()
        var total = 0
        list1.zip(list2) { x, y ->
            total += abs(x - y)
        }

        println(total)
    }

    fun part2() {
        val list1 = ArrayList<Int>()
        val list2 = ArrayList<Int>()
        val input = loadLines(1).map {
            it.split("   ").let {
                list1.add(it[0].toInt())
                list2.add(it[1].toInt())
            }
        }
        val rightList = list2.groupBy { it }
        var total = 0
        list1.forEach {
            total += it * rightList.getOrDefault(it, emptyList()).size
        }

        println(total)

    }
}