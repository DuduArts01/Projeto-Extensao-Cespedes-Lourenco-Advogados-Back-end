/* Outputs */

package com.cespede.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculationResponse(
    val metrics: MetricsResponse,
    val schedule: ScheduleResponse,
    val notes: String
)

@Serializable
data class MetricsResponse(
    val totalPenaltyDays: Int,
    val totalRemittedDays: Int,
    val remainingPenaltyDays: Int
)

@Serializable
data class ScheduleResponse(
    val closedRegimeStartDate: String?,       // null se regime inicial for SEMI_OPEN ou OPEN
    val semiOpenEligibilityDate: String?,     // null se regime inicial for SEMI_OPEN ou OPEN
    val openEligibilityDate: String?,         // null se regime inicial for OPEN
    val conditionalReleaseDate: String?,      // Could be null
    val penaltyEndDate: String
)