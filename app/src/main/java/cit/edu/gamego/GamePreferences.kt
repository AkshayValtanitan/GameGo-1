package cit.edu.gamego

import android.content.Context
import android.content.SharedPreferences
import cit.edu.gamego.data.Game
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GamePreferences {
    private const val PREF_NAME = "game_prefs"
    private const val FAVORITES_KEY = "favorite_games"

    fun saveFavorites(context: Context, favorites: List<Game>) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(favorites)
        editor.putString(FAVORITES_KEY, json)
        editor.apply()
    }

    fun loadFavorites(context: Context): MutableList<Game> {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(FAVORITES_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Game>>() {}.type
            Gson().fromJson(json, type)
        } else {
            mutableListOf()
        }
    }
}
