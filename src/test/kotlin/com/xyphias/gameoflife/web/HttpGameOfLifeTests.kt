package com.xyphias.gameoflife.web

import com.xyphias.gameoflife.GameOfLife
import com.xyphias.gameoflife.GameOfLifeContract

class HttpGameOfLifeTests : GameOfLifeContract {
    override val gameOfLife: GameOfLife = HttpGameOfLife(GameOfLifeApp())
}
