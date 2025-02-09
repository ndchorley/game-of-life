package com.xyphias.gameoflife

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.doesNotContain

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

    @Test
    fun `a live cell with three neighbours lives`() {
        val aCell = Cell(x = 1, y = 0)

        val grid = Grid(
            liveCells = setOf(
                aCell,
                Cell(aCell.x - 1, aCell.y),
                Cell(aCell.x + 1, aCell.y),
                Cell(aCell.x + 1, aCell.y + 1)
            )
        )

        expectThat(grid.next().liveCells).contains(aCell)
    }

    @Test
    fun `a live cell with fewer than two neighbours dies`() {
        val aCell = Cell(x = 1, y = 0)

        val grid = Grid(liveCells = setOf(aCell))

        expectThat(grid.next().liveCells).doesNotContain(aCell)
    }
}
