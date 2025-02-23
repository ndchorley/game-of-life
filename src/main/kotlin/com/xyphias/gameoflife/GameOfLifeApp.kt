package com.xyphias.gameoflife

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Moshi.auto
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.template.ThymeleafTemplates

val gridLens = Body.auto<Grid>().toLens()

class GameOfLifeApp : HttpHandler {
    private val game = DomainGameOfLife()
    
    private val routes = routes(
        "/" bind GET to ::homePage,
        "/new" bind POST to ::newGrid,
        "/next" bind POST to ::nextGrid
    )

    override fun invoke(request: Request): Response = routes(request)

    private fun homePage(request: Request): Response {
        val view = templateRenderer(HomePageView)
        
        return Response(OK).body(view)
    }
    
    private fun newGrid(request: Request): Response {
        val grid = gridLens.extract(request)
        
        game.newGrid(grid)
        
        return Response(OK)
    }

    private fun nextGrid(request: Request): Response {
        val grid = game.nextGrid()
        
        val response = Response(OK)
        val responseWithGrid = gridLens.inject(grid, response)
        
        return responseWithGrid
    }
    
    private val templateRenderer = ThymeleafTemplates().CachingClasspath()
}
