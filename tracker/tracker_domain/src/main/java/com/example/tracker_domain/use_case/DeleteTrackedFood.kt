package com.moemen.tracker_domain.use_case

import com.example.tracker_domain.repository.TrackerRepository
import com.moemen.tracker_domain.model.TrackedFood

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}