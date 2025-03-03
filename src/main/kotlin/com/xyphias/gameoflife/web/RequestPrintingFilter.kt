package com.xyphias.gameoflife.web

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import java.time.Clock

class RequestPrintingFilter(
    private val clock: Clock, private val printLine: (String) -> Unit
) : Filter {
    override fun invoke(next: HttpHandler): HttpHandler = {
        request -> 
            printLine("${clock.instant()} ${request.method} ${request.uri}")
        
            next(request)
    }
}
