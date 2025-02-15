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
                val neighbours = liveNeighboursOf(cell)
                
                neighbours.count() == 2 || neighbours.count() == 3
            }
            .toSet()

    private fun liveNeighboursOf(cell: Cell): Set<Cell> =
        cell
            .allNeighbours()
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
        this
            .filter { cell -> liveNeighboursOf(cell).count() == 3 }
            .toSet()

    private fun Set<Cell>.thatAreNotAlive(): Set<Cell> =
        this.filterNot { cell -> liveCells.contains(cell) }.toSet()
}
