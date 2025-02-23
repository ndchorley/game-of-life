package com.xyphias.gameoflife

class HttpGameOfLifeTests : GameOfLifeContract {
    override val gameOfLife: GameOfLife = HttpGameOfLife(GameOfLifeApp())
}
