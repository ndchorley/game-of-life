package com.xyphias.gameoflife

class GameOfLife {
    private var grid: Grid? = null
    
    fun newGrid(grid: Grid) {
        this.grid = grid
    }

    fun nextGrid(): Grid {
        return grid!!.next()
    }
}
