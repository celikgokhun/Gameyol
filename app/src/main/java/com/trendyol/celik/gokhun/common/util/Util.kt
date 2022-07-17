package com.trendyol.celik.gokhun.common.util

import android.content.Intent
import android.net.Uri
import com.trendyol.celik.gokhun.R
import com.trendyol.celik.gokhun.data.gamedetail.source.remote.model.Publisher
import com.trendyol.celik.gokhun.data.gamelisting.source.remote.model.Genre

object Constants {
    const val BASE_URL = "https://api.rawg.io/api/"
    const val API_KEY = "f6c5c10dcd914fc4aa2b69b347ff02e7"
}

fun formatDate(rawDate: String): String {

    val day = rawDate.split("-")[2]
    var month = rawDate.split("-")[1]
    val year = rawDate.split("-")[0]

    when (month) {
        "01" -> {
            month = "Jan"
        }
        "02" -> {
            month = "Feb"
        }
        "03" -> {
            month = "Mar"
        }
        "04" -> {
            month = "Apr"
        }
        "05" -> {
            month = "May"
        }
        "06" -> {
            month = "Jun"
        }
        "07" -> {
            month = "Jul"
        }
        "08" -> {
            month = "Aug"
        }
        "09" -> {
            month = "Sep"
        }
        "10" -> {
            month = "Okt"
        }
        "11" -> {
            month = "Nov"
        }
        "12" -> {
            month = "Dec"
        }
        else -> {
            month = "Error"
        }
    }

    return "$month $day, $year"
}

fun elementExtractorGenre(genreList: List<Genre?>?): String{
    var allGenres =""
    for (item in genreList!!){
        allGenres = allGenres + " " + item?.name +", "
    }
    return allGenres.dropLast(2)
}

fun elementExtractorPublisher(publisherList: List<Publisher?>?): String{
    var allPublishers =""
    for (item in publisherList!!){
        allPublishers = allPublishers + " " + item?.name +", "
    }
    return allPublishers.dropLast(2)
}

fun formatPlayTime(hour: Int?): String{
    var result = ""
    if(hour.toString().toInt() < 1) {
        result = hour.toString() +" hour"
    }else{
        result = hour.toString() +" hours"
    }
    return result
}

fun formatMetaCritic(metaCritic: Int?): Pair<Int, Int> {
    var backgroundResource = 0
    var textColor = 0

    if (metaCritic in 1..49){
        backgroundResource = R.drawable.red_mc_bg
        textColor = R.color.red

    }
    if (metaCritic in 50..75){
        backgroundResource = R.drawable.yellow_mc_bg
        textColor = R.color.yellow
    }
    if (metaCritic in 75..101){
        backgroundResource = R.drawable.green_mc_bg
        textColor = R.color.green
    }

    return backgroundResource to textColor

}

fun goToUrl(url:String): Intent?{
    return  Intent(Intent.ACTION_VIEW, Uri.parse(url))
}