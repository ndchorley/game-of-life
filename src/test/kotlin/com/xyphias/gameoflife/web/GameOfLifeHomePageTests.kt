package com.xyphias.gameoflife.web

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.testing.ApprovalTest
import org.http4k.testing.Approver
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ApprovalTest::class)
class GameOfLifeHomePageTests {
    @Test
    fun `it displays a page`(approver: Approver) {
        val app = GameOfLifeApp()
        
        val response = app(Request(GET, "/"))
        
        approver.assertApproved(response)
    }
}
