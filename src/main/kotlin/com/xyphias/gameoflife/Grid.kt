package com.xyphias.gameoflife
 
class Grid(val liveCells: Set<Cell>) {
    fun next(): Grid {
        val survivors = 
            liveCells
                .withNeighbourCount(2)
                .plus(liveCells.withNeighbourCount(3))
        
        return Grid(survivors)
    }

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
}
