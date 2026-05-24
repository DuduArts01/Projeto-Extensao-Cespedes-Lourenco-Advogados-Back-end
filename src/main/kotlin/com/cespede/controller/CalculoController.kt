package com.cespede.controller

import com.cespede.model.CalculationRequest
import com.cespede.service.CalculadoraPenalService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val calculadoraService = CalculadoraPenalService()

    routing {
        // Updated route to /calculate to match international API standards
        post("/calculate") {
            try {
                // Captures the JSON sent by the front-end and transforms it into our CalculationRequest class
                val request = call.receive<CalculationRequest>()
                
                // Passes the data to the service to perform the updated calculation logic
                val response = calculadoraService.calculatePenalty(request)
                
                // Returns the structured JSON response with Status 200 OK
                call.respond(HttpStatusCode.OK, response)
            } catch (e: Exception) {
                // Captures errors gracefully if the JSON is malformed or properties are missing
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to (e.message ?: "Invalid data")))
            }
        }
    }
}