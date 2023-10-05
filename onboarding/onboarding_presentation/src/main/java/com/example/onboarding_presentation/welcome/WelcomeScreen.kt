package com.example.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.onboarding_presentation.components.ActionButton
import com.moemen.core.navigation.Route
import com.moemen.core.util.UiEvent
import com.moemen.onboarding_presentation.R
import com.mouemen.core_ui.LocalSpacing


@Composable
fun welcomeScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = com.moemen.core.R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = com.moemen.core.R.string.next),
            onclick = { onNavigate(UiEvent.Navigate(Route.GENDER)) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )


    }
}

