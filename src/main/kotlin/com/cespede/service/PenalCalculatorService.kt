package com.cespede.service

import com.cespede.model.CalculationRequest
import com.cespede.model.CalculationResponse
import com.cespede.model.MetricsResponse
import com.cespede.model.ScheduleResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.ceil

class CalculadoraPenalService {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun calculatePenalty(request: CalculationRequest): CalculationResponse {
        // Convert total penalty to days (1 year = 365 days, 1 month = 30 days)
        val totalPenaltyDays = (request.penaltyYears * 365) + (request.penaltyMonths * 30) + request.penaltyDays
        
        // Calculate remitted days (Discounts: work/3 | study/12 | reading*4)
        val remittedFromWork = request.daysWorked / 3
        val remittedFromStudy = request.studyHours / 12
        val remittedFromReading = request.booksRead * 4
        
        val totalRemittedDays = remittedFromWork + remittedFromStudy + remittedFromReading + request.detractionDays

        // Remaining penalty days
        val remainingPenaltyDays = if (totalPenaltyDays - totalRemittedDays > 0) totalPenaltyDays - totalRemittedDays else 0

        val baseDateLocalDate = LocalDate.parse(request.baseDate, formatter)

        // Identify progression percentage (Brazilian Law - Art. 112 LEP)
        val progressionPercentage = when {
            request.crimeType == "COMMON" && request.inmateStatus == "PRIMARY" -> 0.16
            request.crimeType == "COMMON" && request.inmateStatus == "RECIDIVIST" -> 0.20
            request.crimeType == "VIOLENT" && request.inmateStatus == "PRIMARY" -> 0.25
            request.crimeType == "VIOLENT" && request.inmateStatus == "RECIDIVIST" -> 0.30
            request.crimeType == "HEINOUS" && request.inmateStatus == "PRIMARY" -> 0.40
            request.crimeType == "HEINOUS" && request.inmateStatus == "RECIDIVIST" -> 0.60
            request.crimeType == "HEINOUS_DEATH" && request.inmateStatus == "PRIMARY" -> 0.50
            request.crimeType == "HEINOUS_DEATH" && request.inmateStatus == "RECIDIVIST" -> 0.70
            else -> 0.16
        }

        // Calculate conditional release fractions (Art. 83 Penal Code)
        var isConditionalReleaseForbidden = false
        val conditionalReleasePercentage = when {
            request.crimeType == "HEINOUS_DEATH" -> {
                isConditionalReleaseForbidden = true
                0.0
            }
            request.crimeType == "HEINOUS" -> 2.0 / 3.0
            request.inmateStatus == "RECIDIVIST" -> 0.50
            else -> 1.0 / 3.0
        }

        // Date calculations
        val daysToSemiOpen = ceil(totalPenaltyDays * progressionPercentage).toLong()
        val daysToOpen = ceil(totalPenaltyDays * (progressionPercentage * 2)).toLong() 

        val semiOpenDate = baseDateLocalDate.plusDays(daysToSemiOpen - totalRemittedDays)
        val openDate = baseDateLocalDate.plusDays(daysToOpen - totalRemittedDays)
        val penaltyEndDate = baseDateLocalDate.plusDays(remainingPenaltyDays.toLong())

        val conditionalReleaseStr = if (isConditionalReleaseForbidden) {
            null
        } else {
            val daysToConditionalRelease = ceil(totalPenaltyDays * conditionalReleasePercentage).toLong()
            baseDateLocalDate.plusDays(daysToConditionalRelease - totalRemittedDays).toString()
        }

        // Returning response
        return CalculationResponse(
            metrics = MetricsResponse(
                totalPenaltyDays = totalPenaltyDays,
                totalRemittedDays = totalRemittedDays,
                remainingPenaltyDays = remainingPenaltyDays
            ),
            schedule = ScheduleResponse(
                closedRegimeStartDate = baseDateLocalDate.toString(),
                semiOpenEligibilityDate = semiOpenDate.toString(),
                openEligibilityDate = openDate.toString(),
                conditionalReleaseDate = conditionalReleaseStr,
                penaltyEndDate = penaltyEndDate.toString()
            ),
            notes = if (isConditionalReleaseForbidden) {
                "Progressions calculated. Conditional release is FORBIDDEN by law for heinous crimes with death outcome."
            } else {
                "Calculation successfully performed in compliance with the Anti-Crime Package (Law No. 13.964/2019)."
            }
        )
    }
}