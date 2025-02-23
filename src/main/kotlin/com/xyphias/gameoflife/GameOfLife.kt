package com.xyphias.gameoflife

interface GameOfLife {
    fun newGrid(grid: Grid)
    fun nextGrid(): Grid
}