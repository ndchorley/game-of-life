package com.xyphias.gameoflife.web

import org.http4k.core.then
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.time.Clock

class Server(port: Int) {
    private val httpServer =
        RequestPrintingFilter(Clock.systemUTC(), ::println)
            .then(GameOfLifeApp())
            .asServer(Jetty(port))

    fun start() =
        httpServer.start()
}
