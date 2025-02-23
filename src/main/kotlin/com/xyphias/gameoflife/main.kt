package com.xyphias.gameoflife

import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    GameOfLifeApp()
        .asServer(Jetty(port = 8080))
        .start()
}
