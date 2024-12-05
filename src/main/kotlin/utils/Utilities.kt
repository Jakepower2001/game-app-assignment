package ie.setu.utils

import ie.setu.models.Game
import ie.setu.models.Savefile

object Utilities {

    /**
     * Will return a string of a list of Game objects which are already formatted
     */


    @JvmStatic
    fun formatListString(games: List<Game>): String {
        val sbd = StringBuilder()
        for (game in games) {
            sbd.append(game.toString()).append("\n")
        }
        return sbd.toString().trim()
    }

}