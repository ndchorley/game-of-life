package com.xyphias.gameoflife

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains

class GameTest {
    @Test
    fun `a live cell with two neighbours lives`() {
        val aCell = Cell(x = 1, y = 0)

        val grid = Grid(
            liveCells = setOf(
                aCell, 
                Cell(aCell.x - 1, aCell.y),
                Cell(aCell.x + 1, aCell.y)
            )
        )
        
        expectThat(grid.next().liveCells).contains(aCell)
    }
}
