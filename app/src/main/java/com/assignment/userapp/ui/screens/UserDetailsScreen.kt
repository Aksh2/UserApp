package com.assignment.userapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assignment.userapp.Constants.ADDRESS
import com.assignment.userapp.Constants.COUNTRY
import com.assignment.userapp.Constants.EMAIL
import com.assignment.userapp.Constants.NA
import com.assignment.userapp.Constants.PHONE
import com.assignment.userapp.Constants.STATE
import com.assignment.userapp.Constants.USERNAME
import com.assignment.userapp.Constants.ZIP
import com.assignment.userapp.data.model.ErrorMessages.ERROR_USER_NOT_FOUND
import com.assignment.userapp.data.model.User
import com.assignment.userapp.data.model.UserUiState
import com.assignment.userapp.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    viewModel: UserViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.userListState.collectAsStateWithLifecycle()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("User Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is UserUiState.Loading -> {
                    LoadingView()
                }

                is UserUiState.Success -> {
                    val user = state.user.find { it.id == viewModel.selectedId }
                    if (user != null) {
                        UserDetailContent(user)
                    } else {
                        ErrorView(ERROR_USER_NOT_FOUND) { onBackClick }
                    }
                }

                is UserUiState.Error -> {
                    ErrorView(ERROR_USER_NOT_FOUND) { onBackClick }
                }
            }

        }
    }
}

@Composable
fun UserDetailContent(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //User Image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.photo)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "${user.name} photo",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Text(
            text = user.name ?: NA,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = user.company ?: NA,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        HorizontalDivider()

        UserDetail(user)

    }

}


@Composable
fun UserDetail(
    user: User,
) {
    UserDetailRow(USERNAME, value = user.username ?: NA)
    UserDetailRow(EMAIL, value = user.email ?: NA)
    UserDetailRow(PHONE, value = user.phone ?: NA)
    UserDetailRow(ADDRESS, value = user.address ?: NA)
    UserDetailRow(ZIP, value = user.zip ?: NA)
    UserDetailRow(STATE, value = user.state ?: NA)
    UserDetailRow(COUNTRY, value = user.country ?: NA)
}

@Composable
fun UserDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant

        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End

        )
    }
}

