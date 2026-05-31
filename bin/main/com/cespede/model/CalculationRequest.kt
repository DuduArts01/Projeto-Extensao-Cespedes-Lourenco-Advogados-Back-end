/* Inputs */

package com.cespede.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculationRequest(
    val penaltyYears: Int,
    val penaltyMonths: Int,
    val penaltyDays: Int,
    val baseDate: String,
    val detractionDays: Int,
    val crimeType: String, // "COMMON", "VIOLENT", "HEINOUS", "HEINOUS_DEATH"
    val inmateStatus: String, // "PRIMARY", "RECIDIVIST"
    val initialRegime: String, // "CLOSED", "SEMI_OPEN", "OPEN"
    val daysWorked: Int,
    val studyHours: Int,
    val booksRead: Int
)