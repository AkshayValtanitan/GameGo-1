package cit.edu.gamego.data

import cit.edu.gamego.R
import java.io.Serializable

data class Game(
    var name: String = "",
    var date: String = "",
    var rating: Double = 0.0,
    var photo: Int = R.drawable.aaa,
    val gameTrailer: String = ""
) : Serializable

// todo add platform compatablilit, rating ,genres ,theme,gamemodes