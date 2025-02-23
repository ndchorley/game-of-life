package com.xyphias.gameoflife

import com.xyphias.gameoflife.web.GameOfLifeApp

class HttpGameOfLifeTests : GameOfLifeContract {
    override val gameOfLife: GameOfLife = HttpGameOfLife(GameOfLifeApp())
}
