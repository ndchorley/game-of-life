package com.xyphias.gameoflife
 
class Grid(val liveCells: Set<Cell>, val sideLength: Int = 3) {
    fun next(): Grid {
        val nextLiveCells =
            liveCells.thoseWithTwoOrThreeLiveNeighbours() + cellsThatComeToLife()

        return Grid(nextLiveCells)
    }

    private fun Set<Cell>.thoseWithTwoOrThreeLiveNeighbours(): Set<Cell> = 
        this.
            filter { cell -> 
                val neighbours = neighboursOf(cell)
                
                neighbours.count() == 2 || neighbours.count() == 3
            }
            .toSet()

    private fun Set<Cell>.withNeighbourCount(count: Int): Set<Cell> =
        this
            .filter { cell -> neighboursOf(cell).size == count }
            .toSet()

    private fun neighboursOf(cell: Cell): Set<Cell> =
        cell
            .potentialNeighbours()
            .thoseThatAreLiving()
            .toSet()

    private fun Set<Cell>.thoseThatAreLiving(): Set<Cell> =
        this
            .filter { potentialNeighbour -> liveCells.contains(potentialNeighbour) }
            .toSet()

    private fun cellsThatComeToLife(): Set<Cell> =
        allCells()
            .thatAreNotAlive()
            .withExactlyThreeLiveNeighbours()

    private fun allCells(): Set<Cell> =
        (1 until sideLength).flatMap { x ->
            (1 until sideLength).map { y ->
                Cell(x, y)
            }
        }.toSet()

    private fun Set<Cell>.withExactlyThreeLiveNeighbours(): Set<Cell> =
        withNeighbourCount(3)

    private fun Set<Cell>.thatAreNotAlive(): Set<Cell> =
        this.filterNot { cell -> liveCells.contains(cell) }.toSet()
}
