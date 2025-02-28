package com.xyphias.gameoflife.web

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.strikt.bodyString
import org.http4k.strikt.header
import org.http4k.strikt.status
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.startsWith

class StaticAssetsTests {
    @Test
    fun `it serves JavaScript`() {
        val app = GameOfLifeApp()

        val response = app(Request(GET, "/static/game-of-life.js"))

        expectThat(response) {
            status.isEqualTo(OK)
            header("content-type").isEqualTo("application/javascript")
            bodyString.startsWith("const gridSideLengthPixels = 500;")
        }
    }
}
