package com.example.weathertest.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weathertest.DETAILS_SPACER_HEIGHT
import com.example.weathertest.DETAIL_BAR_SPACER_WIDTH
import com.example.weathertest.DEGRESS
import com.example.weathertest.DETAILS_TOP_SPACER_HEIGHT
import com.example.weathertest.HTTPS
import com.example.weathertest.R
import com.example.weathertest.data.Weather

@Composable
fun SelectedWeatherContent(selectedWeather: Weather?, isLoading: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {
        if (isLoading) {
            LoadingProgress()
        } else if (selectedWeather == null) {
            NoCitySelectedWeatherContent()
        } else {
            WeatherContent(selectedWeather)
        }
    }
}

@Composable
fun NoCitySelectedWeatherContent() {
    Box(contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(200.dp))
            Text(
                stringResource(R.string.no_city_selected),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(stringResource(R.string.please_search_for_a_city),
                fontSize = 14.sp)
        }
    }
}

@Composable
fun WeatherContent(weather: Weather) {
    val currentWeather = weather.current
    val cityName = weather.location.name
    val temp = currentWeather.tempF.toString()
    val icon = currentWeather.condition.icon
    val humidity = currentWeather.humidity.toString()
    val uvIndex = currentWeather.uv.toString()
    val feelsLike = currentWeather.feelslikeF.toString()
    WeatherDetails(cityName, temp, icon, humidity, uvIndex, feelsLike)
}

@Composable
fun WeatherDetails(
    cityName: String,
    tempF: String,
    icon: String,
    humidity: String,
    uvIndex: String,
    feelsLike: String
) {
    Spacer(Modifier.height(DETAILS_TOP_SPACER_HEIGHT))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            model = "$HTTPS$icon",
            contentDescription = null,
            modifier = Modifier.size(240.dp)
        )

        Spacer(Modifier.height(DETAILS_SPACER_HEIGHT))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(cityName, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(R.drawable.near_me_24dp),
                contentDescription = null
            )
        }

        Spacer(Modifier.height(DETAILS_SPACER_HEIGHT))

        Text(tempF + DEGRESS, fontSize = 56.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(DETAILS_SPACER_HEIGHT))

        WeatherDetailBar("$humidity%", uvIndex, feelsLike)
    }
}

@Composable
fun WeatherDetailBar(
    humidity: String,
    uvIndex: String,
    feelsLike: String
) {
    Box(
        Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
            .alpha(.6f)
    ) {
        Row {
            WeatherDetailBarWidget(
                stringResource(R.string.humidity),
                humidity
            )

            Spacer(modifier = Modifier.width(DETAIL_BAR_SPACER_WIDTH))

            WeatherDetailBarWidget(
                stringResource(R.string.uv),
                uvIndex
            )

            Spacer(modifier = Modifier.width(DETAIL_BAR_SPACER_WIDTH))

            WeatherDetailBarWidget(
                stringResource(R.string.feels_like),
                feelsLike
            )
        }
    }
}

@Composable
fun WeatherDetailBarWidget(
    topText: String,
    bottomText: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(topText, color = Color.DarkGray, fontSize = 14.sp)
        Text(bottomText, color = Color.DarkGray, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}


@Composable
@Preview
fun PreviewWeatherDetails() {
    WeatherDetails(
        "New York",
        "50",
        "",
        humidity = "60",
        uvIndex = "5",
        feelsLike = "46"

    )
}

@Composable
@Preview
fun PreviewWeatherDetailBar() {
    WeatherDetailBar(
        humidity = "70%",
        uvIndex = "4.2",
        feelsLike = "32"
    )
}

@Composable
@Preview
fun PreviewWeatherDetailBarWidget() {
    WeatherDetailBarWidget(
        "Humidity",
        "5"
    )
}
