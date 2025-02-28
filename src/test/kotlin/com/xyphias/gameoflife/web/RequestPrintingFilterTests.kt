package com.xyphias.gameoflife.web

import org.http4k.core.*
import org.http4k.core.Method.GET
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class RequestPrintingFilterTests {
    @Test
    fun `it prints incoming requests`() {
        fun handle(request: Request) = Response(Status.OK)
        var output = ""
        fun printLine(line: String) {
            output += "$line\n"
        }
        val clock = Clock.fixed(Instant.EPOCH, ZoneId.of("UTC"))
        val filter: Filter = RequestPrintingFilter(clock, ::printLine)
        val app = filter.then(::handle)
        
        app(Request(GET, "/foo"))
        
        expectThat(output).isEqualTo("1970-01-01T00:00:00Z GET /foo\n")
    }
}
