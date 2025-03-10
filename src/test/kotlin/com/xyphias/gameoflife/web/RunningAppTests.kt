package com.xyphias.gameoflife.web

import com.xyphias.gameoflife.GameOfLife
import com.xyphias.gameoflife.GameOfLifeContract
import org.http4k.client.JavaHttpClient
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.junit.jupiter.api.BeforeAll

class RunningAppTests : GameOfLifeContract {
    companion object {
        val port = 9000

        @BeforeAll
        @JvmStatic
        fun startApp() {
            Server(port).start()
        }
    }
    
    private val client =
        ClientFilters.SetBaseUriFrom(Uri.of("http://localhost:$port"))
            .then(JavaHttpClient())
    
    override val gameOfLife: GameOfLife = HttpGameOfLife(client)
}

