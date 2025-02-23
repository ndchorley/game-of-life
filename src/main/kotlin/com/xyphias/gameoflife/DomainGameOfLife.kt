package com.xyphias.gameoflife

class DomainGameOfLife : IGameOfLife {
    private var grid: Grid? = null
    
    override fun newGrid(grid: Grid) {
        this.grid = grid
    }

    override fun nextGrid(): Grid {
        grid = grid!!.next()
        
        return grid!!
    }
}
