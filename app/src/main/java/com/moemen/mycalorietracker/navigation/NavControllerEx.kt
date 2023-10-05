package com.moemen.mycalorietracker.navigation

import androidx.navigation.NavController
import com.moemen.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}