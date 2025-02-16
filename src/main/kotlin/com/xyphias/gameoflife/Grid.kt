package com.xyphias.gameoflife
 
class Grid(val liveCells: Set<Cell>, val sideLength: Int) {
    fun next(): Grid {
        val nextLiveCells =
            liveCells.thoseWithTwoOrThreeLiveNeighbours() + cellsThatComeToLife()

        return Grid(nextLiveCells, sideLength)
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
            .thoseThatAreLive()
            .toSet()

    private fun Set<Cell>.thoseThatAreLive(): Set<Cell> =
        this
            .filter { cell -> liveCells.contains(cell) }
            .toSet()

    private fun cellsThatComeToLife(): Set<Cell> =
        allCells()
            .thoseThatAreDead()
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

    private fun Set<Cell>.thoseThatAreDead(): Set<Cell> =
        this.filterNot { cell -> liveCells.contains(cell) }.toSet()
}
