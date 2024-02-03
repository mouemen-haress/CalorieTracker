package com.example.tracker_data.repository

import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.repository.TrackerRepository
import com.moemen.tracker_data.local.TrackerDao
import com.moemen.tracker_data.mapper.toTrackableFood
import com.moemen.tracker_data.mapper.toTrackedFood
import com.moemen.tracker_data.mapper.toTrackedFoodEntity
import com.moemen.tracker_domain.model.TrackableFood
import com.moemen.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepoImp(
    private val dao: TrackerDao,
    private val api: OpenFoodApi

) : TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            var searchDto = api.searchFood(query = query, page = page, pageSize = pageSize)

            Result.success(
                searchDto.products.filter {
                    val calculatedCalories =
                        it.nutriments.carbohydrates100g * 4f +
                                it.nutriments.proteins100g * 4f +
                                it.nutriments.fat100g * 9f
                    val lowerBound = calculatedCalories * 0.99f
                    val upperBound = calculatedCalories * 1.01f
                    it.nutriments.energyKcal100g in (lowerBound..upperBound)
                }.mapNotNull {
                    it.toTrackableFood()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        ).map {
            it.map {
                it.toTrackedFood()
            }
        }
    }
}