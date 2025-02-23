package com.xyphias.gameoflife

import org.http4k.core.HttpHandler
import org.http4k.core.Method.POST
import org.http4k.core.Request

class HttpGameOfLife(private val gameOfLifeApp: HttpHandler) : GameOfLife {
    override fun newGrid(grid: Grid) {
        val request = Request(POST, "/new")
        val requestWithBody = gridLens.inject(grid, request)
        
        gameOfLifeApp(requestWithBody)
    }

    override fun nextGrid(): Grid {
        val response = gameOfLifeApp(Request(POST, "/next"))
        val grid = gridLens.extract(response)
        
        return grid
    }
}
