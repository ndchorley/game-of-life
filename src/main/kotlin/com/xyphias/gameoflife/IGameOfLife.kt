package com.xyphias.gameoflife

interface IGameOfLife {
    fun newGrid(grid: Grid)
    fun nextGrid(): Grid
}