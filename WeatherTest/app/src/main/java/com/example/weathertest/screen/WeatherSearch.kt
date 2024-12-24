package com.example.weathertest.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weathertest.DEGRESS
import com.example.weathertest.R
import com.example.weathertest.data.Weather


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchBar(
    searchedWeather: Weather?,
    searchedCity: String?,
    isLoading: Boolean,
    error: String?,
    onFetchWeather: (String) -> Unit,
    onWeatherSelected: (Weather) -> Unit
) {
    var city by rememberSaveable { mutableStateOf("") }
    if (searchedCity != null) {
        city = searchedCity
    }
    var expanded by rememberSaveable { mutableStateOf(false) }

    val searchBarPadding by animateDpAsState(
        targetValue = if(expanded) 0.dp else 16.dp,
        label = "Search bar padding"
    )

    SearchBar(
        modifier = Modifier.padding(horizontal = searchBarPadding),
        inputField = {
            SearchBarDefaults.InputField(
                query = city,
                onQueryChange = { city = it },
                onSearch = { onFetchWeather(it) },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text(stringResource(R.string.search_location)) },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {

        Spacer(Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                LoadingProgress()
            } else if (error != null || searchedWeather == null) {
                EmptyWeatherResultsContent(error)
            } else {
                val cityName = searchedWeather.location.name
                val temp = searchedWeather.current.tempF
                val icon = searchedWeather.current.condition.icon
                WeatherResult(
                    cityName = cityName,
                    tempF = temp.toString(),
                    icon = icon,
                    onClick = {
                        expanded = false
                        onWeatherSelected(searchedWeather)
                    }
                )
            }
        }
    }
}


@Composable
fun WeatherResult(
    cityName: String,
    tempF: String,
    icon: String,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .padding(vertical = 20.dp, horizontal = 16.dp)
            .clickable {
                onClick()
            }
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 18.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(cityName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(2.dp))
                Text(tempF + DEGRESS, fontSize = 42.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.weight(1f))

            AsyncImage(
                model = "https://$icon",
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
        }
    }
}

@Composable
fun LoadingProgress() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
    }
}

@Composable
fun EmptyWeatherResultsContent(error: String? = null) {
    // Empty screen here based on design
    // TODO ask about error screen for the error response for user
    // ErrorContent(error)
}

@Composable
fun ErrorContent(error: String?) {
    val errorString = if (error != null) {
        stringResource(R.string.oops_there_was_an_error, error)
    } else {
        stringResource(R.string.oops_there_was_an_error_generic)
    }
    Text(
        errorString,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}


@Preview
@Composable
fun PreviewWeatherResult() {
    WeatherResult(
        "New York",
        "72",
        "",
        onClick = {}
    )
}
