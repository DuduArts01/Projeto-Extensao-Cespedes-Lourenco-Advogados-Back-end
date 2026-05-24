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
        // Main route for penalty calculation
        post("/calculate") {
            try {
                // Receive and parse the request body
                val request = call.receive<CalculationRequest>()
                
                // --- Input Validation Layer ---
                val errorMessage = when {
                    request.penaltyMonths !in 0..11 -> "Months must be between 0 and 11."
                    request.penaltyDays !in 0..29 -> "Days must be between 0 and 29."
                    (request.penaltyYears == 0 && request.penaltyMonths == 0 && request.penaltyDays == 0) -> "Total sentence duration cannot be zero."
                    request.penaltyYears < 0 -> "Years cannot be negative."
                    else -> null
                }

                // If validation fails, return 400 Bad Request and stop execution
                if (errorMessage != null) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to errorMessage))
                    return@post
                }
                // --- End of Validation ---

                // Perform calculation if data is valid
                val response = calculadoraService.calculatePenalty(request)
                
                // Return result as 200 OK
                call.respond(HttpStatusCode.OK, response)
            } catch (e: Exception) {
                // Return error if JSON structure is invalid or an internal error occurs
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid JSON format or internal error: ${e.message}"))
            }
        }
    }
}