package com.xyphias.gameoflife

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.doesNotContain
import strikt.assertions.isEqualTo

interface GameOfLifeContract {
    val gameOfLife: GameOfLife

    @Test
    fun `a live cell with two neighbours lives`() {
        val aCell = Cell(x = 1, y = 0)
        val grid = Grid(
            liveCells = 
                setOf(
                    aCell, 
                    Cell(aCell.x - 1, aCell.y),
                    Cell(aCell.x + 1, aCell.y)
                ),
            sideLength = 3
        )
        gameOfLife.newGrid(grid)
        
        val nextGrid = gameOfLife.nextGrid()
        
        expectThat(nextGrid.liveCells).contains(aCell)
    }

    @Test
    fun `a live cell with three neighbours lives`() {
        val aCell = Cell(x = 1, y = 0)
        val grid = Grid(
            liveCells = 
                setOf(
                    aCell,
                    Cell(aCell.x - 1, aCell.y),
                    Cell(aCell.x + 1, aCell.y),
                    Cell(aCell.x + 1, aCell.y + 1)
                ),
            sideLength = 3
        )
        gameOfLife.newGrid(grid)

        val nextGrid = gameOfLife.nextGrid()
        
        expectThat(nextGrid.liveCells).contains(aCell)
    }

    @Test
    fun `a live cell with fewer than two neighbours dies`() {
        val aCell = Cell(x = 1, y = 0)
        val grid = Grid(liveCells = setOf(aCell), sideLength = 3)
        gameOfLife.newGrid(grid)

        val nextGrid = gameOfLife.nextGrid()
        
        expectThat(nextGrid.liveCells).doesNotContain(aCell)
    }

    @Test
    fun `a live cell with more than three neighbours dies`() {
        val aCell = Cell(x = 1, y = 0)
        val grid = Grid(
            liveCells = 
                setOf(
                    aCell,
                    Cell(aCell.x - 1, aCell.y),
                    Cell(aCell.x + 1, aCell.y),
                    Cell(aCell.x + 1, aCell.y + 1),
                    Cell(aCell.x + 1, aCell.y - 1)
                ),
            sideLength = 3
        )
        gameOfLife.newGrid(grid)

        val nextGrid = gameOfLife.nextGrid()
        
        expectThat(nextGrid.liveCells).doesNotContain(aCell)
    }

    @Test
    fun `a dead cell with exactly three live neighbours comes to life`() {
        val theDeadCell = Cell(x = 1, y = 1)
        val neighboursOfTheDeadCell = 
            setOf(
                Cell(theDeadCell.x, theDeadCell.y - 1),
                Cell(theDeadCell.x + 1, theDeadCell.y),
                Cell(theDeadCell.x + 1, theDeadCell.y - 1)
            )
        val grid = Grid(sideLength = 3, liveCells = neighboursOfTheDeadCell)
        gameOfLife.newGrid(grid)

        val nextGrid = gameOfLife.nextGrid()
        
        expectThat(nextGrid.liveCells).contains(theDeadCell)
    }

    @Test
    fun `a blinker oscillates with period 2`() {
        val aRowOfCells =
            setOf(Cell(x = 1, y = 2), Cell(x = 2, y = 2), Cell(x = 3, y = 2))
        gameOfLife.newGrid(Grid(aRowOfCells, sideLength = 5))
        
        val firstGeneration = gameOfLife.nextGrid()
        
        val aColumnOfCells =
            setOf(Cell(x = 2, y = 1), Cell(x = 2, y = 2), Cell(x = 2, y = 3))
        expectThat(firstGeneration.liveCells).isEqualTo(aColumnOfCells)
        
        val secondGeneration = gameOfLife.nextGrid()
        
        expectThat(secondGeneration.liveCells).isEqualTo(aRowOfCells)
    }
}