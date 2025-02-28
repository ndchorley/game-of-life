package com.xyphias.gameoflife

import com.xyphias.gameoflife.web.GameOfLifeApp
import com.xyphias.gameoflife.web.RequestPrintingFilter
import org.http4k.core.then
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.time.Clock

fun main() {
    val app = 
        RequestPrintingFilter(Clock.systemUTC(), ::println)
            .then(GameOfLifeApp())
    
    app
        .asServer(Jetty(port = 8080))
        .start()
}
