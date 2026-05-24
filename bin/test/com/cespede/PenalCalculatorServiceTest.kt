package com.cespede.service

import com.cespede.model.CalculationRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CalculadoraPenalServiceTest {

    // Instantiating the real calculation service with the updated English method
    private val calculadora = CalculadoraPenalService()

    @Test
    fun `should calculate progression correctly for common crime and primary inmate`() {
        // Test Scenario (Simulating a sentence of 4 full years)
        val request = CalculationRequest(
            penaltyYears = 4,
            penaltyMonths = 0,
            penaltyDays = 0,
            baseDate = "2026-05-24", // Current date
            detractionDays = 0,
            crimeType = "COMMON",
            inmateStatus = "PRIMARY",
            daysWorked = 0,
            studyHours = 0,
            booksRead = 0
        )

        // Execution of the updated English method
        val response = calculadora.calculatePenalty(request)

        // Checks (JUnit verifies if the math matches using the new English model)
        // Total sentence in days: 4 * 365 = 1460 days.
        assertEquals(1460, response.metrics.totalPenaltyDays)
        assertEquals(0, response.metrics.totalRemittedDays)
        assertEquals(1460, response.metrics.remainingPenaltyDays)

        // Requirement for the Semi-Open Regime: 16% of 1460 = 233.6 -> Rounded up to 234 days.
        // Adding 234 days from 2026-05-24, the correct date should be 2027-01-13.
        assertEquals("2027-01-13", response.schedule.semiOpenEligibilityDate)

        // End of sentence: Adding 1460 days from 2026-05-24, it should equal 2030-05-23.
        assertEquals("2030-05-23", response.schedule.penaltyEndDate)
        
        // Parole should not be null for this common crime.
        assertNotNull(response.schedule.conditionalReleaseDate)
    }
}