import Day6.DIRECTION.*

object Day6 {

    data class Position(val x: Int, val y: Int, var hasObstacle: Boolean)
    enum class DIRECTION(val vector: Pair<Int,Int>) {
        UP(0 to -1),
        DOWN(0 to 1),
        LEFT(-1 to 0),
        RIGHT(1 to 0),
    }
    val directionMap = mapOf(
        '^' to UP,
        'v' to DOWN,
        '>' to RIGHT,
        '<' to LEFT,
    )

    fun part1() {

        val input = loadLines(6)

        var currentDirection = UP
        var currentPosition = Position(0,0, false,)

        val visitedPositions = mutableSetOf<Pair<Int,Int>>()

        val map: List<List<Position>> = input.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                val isStartingPosition = directionMap.contains(c)
                val pos = Position(x, y, c == '#')
                if (isStartingPosition) {
                    currentDirection = directionMap.getValue(c)
                    currentPosition = pos
                }
                pos
            }
        }

        var isOutOfBounds = false

        while (!isOutOfBounds) {
            val pos = currentPosition.x to currentPosition.y
            visitedPositions.add(pos.copy())
            val destination = (pos.first + currentDirection.vector.first) to (pos.second + currentDirection.vector.second)
            val destPosition = map.getOrNull(destination.second)?.getOrNull(destination.first)
            if (destPosition != null) {
                if (destPosition.hasObstacle) {
                    currentDirection = turnRight(currentDirection)
                } else {
                    currentPosition = destPosition
                }
            } else {
                isOutOfBounds = true
            }
        }

        println(visitedPositions.size)
    }

    fun part2() {

        val input = loadLines(6)

        var currentDirection = UP
        var currentPosition = Position(0,0, false,)

        val map: List<List<Position>> = input.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                val isStartingPosition = directionMap.contains(c)
                val pos = Position(x, y, c == '#')
                if (isStartingPosition) {
                    currentDirection = directionMap.getValue(c)
                    currentPosition = pos
                }
                pos
            }
        }

        val possibles = map.flatten().filterNot { it.hasObstacle }

        var totals = 0

        possibles.forEach {
            map[it.y][it.x].hasObstacle = true
            it.hasObstacle = true
            if (checkLoop(currentPosition, currentDirection, map)) {
                totals++
            }
            map[it.y][it.x].hasObstacle = false
        }

        println(totals)

    }

    private fun checkLoop(
        initialPosition: Position,
        initialDirection: DIRECTION,
        map: List<List<Position>>
    ): Boolean {
        var currentPosition = initialPosition
        var currentDirection = initialDirection
        var isOutOfBounds = false
        val visitedPositions = mutableSetOf<Pair<Position, DIRECTION>>()

        while (!isOutOfBounds) {
            val pos = currentPosition.x to currentPosition.y
            val alreadyVisited = !visitedPositions.add(currentPosition to currentDirection)
            if (alreadyVisited) return true
            val destination =
                (pos.first + currentDirection.vector.first) to (pos.second + currentDirection.vector.second)
            val destPosition = map.getOrNull(destination.second)?.getOrNull(destination.first)
            if (destPosition != null) {
                if (destPosition.hasObstacle) {
                    currentDirection = turnRight(currentDirection)
                } else {
                    currentPosition = destPosition
                }
            } else {
                isOutOfBounds = true
            }
        }

        return false
    }

    private fun turnRight(currentDIRECTION: DIRECTION): DIRECTION {
        return when (currentDIRECTION) {
            UP -> RIGHT
            DOWN -> LEFT
            LEFT -> UP
            RIGHT -> DOWN
        }
    }

}