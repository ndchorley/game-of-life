package com.xyphias.gameoflife

data class Cell(val x: Int, val y: Int) {
    fun potentialNeighbours(): Set<Cell> = 
        setOf(
            Cell(x - 1, y),
            Cell(x + 1, y),
            Cell(x, y - 1),
            Cell(x, y + 1),
            Cell(x - 1, y - 1),
            Cell(x + 1, y - 1),
            Cell(x - 1, y + 1),
            Cell(x + 1, y + 1)
        )
}
