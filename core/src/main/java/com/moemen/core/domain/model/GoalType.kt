package com.moemen.core.domain.model

sealed class GoalType(val name: String) {
    object LoseWeight : GoalType("Lose_Weight")
    object KeepWeight : GoalType("Keep_Weight")
    object GainWeight : GoalType("Gain_Weight")

    companion object {
        fun fromString(name: String): GoalType {
            return when (name) {
                "Lose_Weight" -> LoseWeight
                "Keep_Weight" -> KeepWeight
                "Gain_Weight" -> GainWeight
                else -> LoseWeight
            }
        }
    }

}